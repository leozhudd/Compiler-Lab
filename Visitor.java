import org.antlr.v4.runtime.misc.Pair;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Visitor extends SysYBaseVisitor<String> {
    private Stack<Map<String,Variable>>assignStack = new Stack<>();
    private Stack<Pair<String,String>>loopLabels = new Stack<>(); // <条件判断label,退出label>
    private int regId = 1; // 从1开始
    private boolean globalFlag = true; // 指示全局变量定义
    private boolean arrayFlag = false; // 指示数组声明

    // compUnit: decl* funcDef;
    @Override
    public String visitCompUnit(SysYParser.CompUnitContext ctx) {
        /*
        先压一个栈，代表全局变量的符号表
        此处的decl模块都是全局变量定义，需一直保持globalFlag=true
        在访问funcDef进入主函数时，再置globalFlag=false
         */
        Map<String,Variable> assignMap = new HashMap<>(); // 声明全局变量的符号表
        assignStack.push(assignMap); // 压入符号表栈
        for(SysYParser.DeclContext e: ctx.decl()) {
            visit(e);
        }
        globalFlag = false;
        return visit(ctx.funcDef());
    }

    // constDef: Ident ('[' constExp ']' )* '=' constInitVal
    // TODO: 全局数组的 ConstInitVal/InitVal 中的 ConstExp/Exp 必须是编译时可求值的常量表达式。
    // TODO: 局部常量数组的 ConstInitVal 中的 ConstExp 必须是编译时可求值的表达式。
    String nowInitArray = "";
    @Override
    public String visitConstDef(SysYParser.ConstDefContext ctx) {
        String name = ctx.Ident().getText();
        /*
        需要判断全局变量，特殊处理
        */
        Map<String,Variable> assignMap = assignStack.peek(); // 取出符号表
        if(assignMap.containsKey(name)) { // 如果符号表中已经有这个名字，报错退出
            System.exit(2);
        }
        int dimension = ctx.constExp().size(); // 维度（0为常量，大于0为常量数组）

        if(dimension == 0) { // 如果是常量声明（不是数组）
            String value = visit(ctx.constInitVal());
            if(!globalFlag) { // 局部变量，需要alloca
                String ptr_reg = "%r" + regId++;
                System.out.println("    " + ptr_reg + " = alloca i32");
                System.out.println("    store i32 " + value + ", i32* " + ptr_reg);
                assignMap.put(name, new Variable(name, ptr_reg, true, true));
            }
            else { // 全局变量，不需要alloca
                System.out.println("@" + name + " = dso_local global i32 " + value);
                assignMap.put(name, new Variable(name, "@" + name, true, true, Integer.parseInt(value)));
            }
        }
        else { // TODO: 数组声明
            nowInitArray = name;
            ArrayList<Integer>arrayDim = new ArrayList<>(); // 保存数组每一维度的长度
            if(!globalFlag) {
                // 局部数组
                String reg = "%r" + regId++;
                System.out.print("    " + reg + " = alloca ");
                globalFlag = true; // 只要globalFlag=true，就会只能访问【常量表达式】

                // 遍历每个维度的长度，例如a[4][5]的[4]和[5]
                StringBuilder arrayType = new StringBuilder();
                for(SysYParser.ConstExpContext e: ctx.constExp()) {
                    String len = visit(e); // TODO: 数组的各维长度必须是编译时可求值的【非负】【常量表达式】。
                    if(len.startsWith("-")) {
                        System.exit(-1);
                    }
                    arrayDim.add(Integer.parseInt(len));
                    arrayType.append("[").append(len).append(" x ");
                }
                arrayType.append("i32");
                for(int i=0;i<dimension;i++) {
                    arrayType.append("]");
                }
                System.out.println(arrayType);
                globalFlag = false; // 还原flag
                assignMap.put(name, new Variable(name, reg, true, arrayDim, arrayType.toString()));

                // memset需要使用i32*指针，这里转换一下
                String memset_reg = "%r_for_memset" + regId++;
                System.out.print("    " + memset_reg + " = getelementptr " + arrayType + ", " + arrayType + "* " + reg);
                for(int i=0;i<=arrayDim.size();i++) {
                    System.out.print(", i32 0");
                }
                System.out.println();
                int size = 4;
                for (Integer i : arrayDim) {
                    size *= i;
                }
                System.out.println("    call void @memset(i32* " + memset_reg + ", i32 0, i32 " + size + ")");

                arrayFlag = true;
                visit(ctx.constInitVal());
                arrayFlag = false;
            }
            else {
                // 全局数组（不需要memset，声明时直接赋值）
                String reg = "@" + name;
                System.out.print(reg + " = dso_local constant ");

                StringBuilder arrayType = new StringBuilder();
                for(SysYParser.ConstExpContext e: ctx.constExp()) {
                    String len = visit(e); // TODO: 数组的各维长度必须是编译时可求值的【非负】【常量表达式】。
                    if(len.startsWith("-")) {
                        System.exit(-1);
                    }
                    arrayDim.add(Integer.parseInt(len));
                    arrayType.append("[").append(len).append(" x ");
                }
                arrayType.append("i32");
                for(int i=0;i<dimension;i++) {
                    arrayType.append("]");
                }
                // System.out.print(arrayType);
                assignMap.put(name, new Variable(name, reg, true, arrayDim, arrayType.toString()));

                arrayFlag = true;
                globalArrayInitVals.clear();
                visit(ctx.constInitVal());
                arrayFlag = false;

                construct(arrayDim, arrayType.toString(), 0, 0);
                System.out.println();
                // 把获得的初始化值格式化输出【globalArrayInitVals】
            }
        }
        return null;
    }

    private void construct(ArrayList<Integer> arrayDim, String type, int nowDim, int totalIndex) {
        /**
         * 递归构建任意多维全局数组定义表达式（等号右边的部分）
         * 1维数组：[3 x i32] [i32 1, i32 2, i32 3]
         * 2维数组：[2 x [2 x i32]] [[2 x i32] [i32 1, i32 2], [2 x i32] [i32 3, i32 0]]
         */
        if(nowDim == arrayDim.size()) { // 已经是最后一维了，输出"i32 num"
            // System.out.print(" <i32 " + totalIndex + "> ");
            // 此时的totalIndex是这个元素在数组中的位置唯一编号
            if (globalArrayInitVals.containsKey(totalIndex)) {
                System.out.print("i32 " + globalArrayInitVals.get(totalIndex));
            }
            else {
                System.out.print("i32 0"); // 没有提供初始值，默认为0
            }
        }
        else {
            String nextType = type.substring(5, type.length() - 1);
            System.out.print(type + " [");
            for (int i = 0; i < arrayDim.get(nowDim); i++) {
                construct(arrayDim, nextType, nowDim + 1, totalIndex * arrayDim.get(nowDim) + i);
                if (i != arrayDim.get(nowDim) - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
        }
    }

    // constInitVal: constExp
    //    | '{' (constInitVal (',' constInitVal)*)? '}' // 支持数组
    Stack<Integer>dimIndex = new Stack<>(); // 当前元素在数组中的index
    HashMap<Integer, String> globalArrayInitVals = new HashMap<>();
    @Override
    public String visitConstInitVal(SysYParser.ConstInitValContext ctx) {
        if(!arrayFlag) { // 普通常量的初始值
            return visit(ctx.constExp());
        }
        else if(!globalFlag) { // 局部数组的初始值
            Variable array = assignStack.peek().get(nowInitArray);
            if(ctx.constExp() != null) { // constExp
                String value_reg = visit(ctx.constExp());
                String ret_reg = "%r" + regId++;
                System.out.print("    " + ret_reg + " = getelementptr ");
                System.out.print(array.arrayType + ", ");
                System.out.print(array.arrayType + "* " + array.reg + ", i32 0");
                for (Integer idx : dimIndex) {
                    System.out.print(", i32 " + idx);
                }
                System.out.println();
                System.out.println("    store i32 " + value_reg + ", i32* " + ret_reg);
                // %1 = getelementptr [5 x [4 x i32]], [5 x [4 x i32]]* @a, i32 0, i32 2, i32 3
            }
            else { // {constInitVal, constInitVal, ...}
                for(int i=0;i<ctx.constInitVal().size();i++) { // 遍历下一维度
                    SysYParser.ConstInitValContext e = ctx.constInitVal(i);
                    dimIndex.push(i);
                    if(dimIndex.size() > array.arrayDim.size()) {
                        // 初始值的维度超过了数组声明的维度
                        System.exit(-1);
                    }
                    visit(e);
                    dimIndex.pop();
                }
            }
            return null;
        }
        else { //TODO: 全局数组的初始值
            // 初值必须是常量表达式
            Variable array = assignStack.peek().get(nowInitArray);
            if(ctx.constExp() != null) { // constExp
                String value = visit(ctx.constExp());
                int totalIndex = 0;
                for(int i=0;i<dimIndex.size();i++) { // [2,3] [1,2] 1 4(543 210)
                    totalIndex = totalIndex * array.arrayDim.get(i) + dimIndex.get(i);
                }
                globalArrayInitVals.put(totalIndex, value); // 把初始化的每个元素的值放进list，备用
            }
            else { // {constInitVal, constInitVal, ...}
                for(int i=0;i<ctx.constInitVal().size();i++) { // 遍历下一维度
                    SysYParser.ConstInitValContext e = ctx.constInitVal(i);
                    dimIndex.push(i);
                    if(dimIndex.size() > array.arrayDim.size()) {
                        // 初始值的维度超过了数组声明的维度
                        System.exit(-1);
                    }
                    visit(e);
                    dimIndex.pop();
                }
            }
            return null;
        }
    }

    // varDef: Ident | Ident '=' initVal;
    @Override
    public String visitVarDef(SysYParser.VarDefContext ctx) {
        String name = ctx.Ident().getText();
        Map<String,Variable> assignMap = assignStack.peek(); // 取出符号表
        if(assignMap.containsKey(name)) { // 如果符号表中已经有这个名字，报错退出
            System.exit(3);
        }
        int dimension = ctx.constExp().size(); // 维度

        if(dimension == 0) { // 如果是常量声明（不是数组）
            if (!globalFlag) { // 局部变量，需要alloca
                String ptr_reg = "%r" + regId++;
                System.out.println("    " + ptr_reg + " = alloca i32");
                if (ctx.initVal() != null) { // 有指定初值
                    String value = visit(ctx.initVal());
                    System.out.println("    store i32 " + value + ", i32* " + ptr_reg);
                    assignMap.put(name, new Variable(name, ptr_reg, false, true));
                } else { // 没有指定初值
                    assignMap.put(name, new Variable(name, ptr_reg, false, false));
                }
            } else { // 全局变量，不需要alloca
                String initVal = (ctx.initVal() != null) ? visit(ctx.initVal()) : "0"; // 根据有没有给初始值，没给的话赋值为0
                System.out.println("@" + name + " = dso_local global i32 " + initVal);
                assignMap.put(name, new Variable(name, "@" + name, false, true, Integer.parseInt(initVal)));
            }
        }
        else { // 数组声明
            nowInitArray = name;
            ArrayList<Integer>arrayDim = new ArrayList<>(); // 保存数组每一维度的长度
            if(!globalFlag) {
                // 局部数组
                String reg = "%r" + regId++;
                System.out.print("    " + reg + " = alloca ");
                globalFlag = true; // 只要globalFlag=true，就会只能访问【常量表达式】

                // 遍历每个维度的长度，例如a[4][5]的[4]和[5]
                StringBuilder arrayType = new StringBuilder();
                for(SysYParser.ConstExpContext e: ctx.constExp()) {
                    String len = visit(e); // TODO: 数组的各维长度必须是编译时可求值的【非负】【常量表达式】。
                    if(len.startsWith("-")) {
                        System.exit(-1);
                    }
                    arrayDim.add(Integer.parseInt(len));
                    arrayType.append("[").append(len).append(" x ");
                    // System.out.print("[" + len + " x ");
                    // 例如：System.out.println("%1 = alloca [2 x [2 x i32]]");
                }
                arrayType.append("i32");
                // System.out.print("i32");
                for(int i=0;i<dimension;i++) {
                    arrayType.append("]");
                    // System.out.print("]");
                }
                System.out.println(arrayType);
                globalFlag = false; // 还原flag
                assignMap.put(name, new Variable(name, reg, false, arrayDim, arrayType.toString()));

                // memset需要使用i32*指针，这里转换一下
                String memset_reg = "%r_for_memset" + regId++;
                System.out.print("    " + memset_reg + " = getelementptr " + arrayType + ", " + arrayType + "* " + reg);
                for(int i=0;i<=arrayDim.size();i++) {
                    System.out.print(", i32 0");
                }
                System.out.println();
                int size = 4;
                for (Integer i : arrayDim) {
                    size *= i;
                }
                System.out.println("    call void @memset(i32* " + memset_reg + ", i32 0, i32 " + size + ")");

                if (ctx.initVal() == null) { // 没有指定初值
                    return null;
                }

                arrayFlag = true;
                visit(ctx.initVal());
                arrayFlag = false;
            }
            else {
                // 全局数组（不需要memset，声明时直接赋值）
                String reg = "@" + name;
                System.out.print(reg + " = dso_local global ");

                StringBuilder arrayType = new StringBuilder();
                for(SysYParser.ConstExpContext e: ctx.constExp()) {
                    String len = visit(e); // TODO: 数组的各维长度必须是编译时可求值的【非负】【常量表达式】。
                    if(len.startsWith("-")) {
                        System.exit(-1);
                    }
                    arrayDim.add(Integer.parseInt(len));
                    arrayType.append("[").append(len).append(" x ");
                }
                arrayType.append("i32");
                for(int i=0;i<dimension;i++) {
                    arrayType.append("]");
                }
                // System.out.print(arrayType);
                assignMap.put(name, new Variable(name, reg, false, arrayDim, arrayType.toString()));

                if (ctx.initVal() == null) { // 没有指定初值
                    System.out.println(arrayType + " zeroinitializer");
                    return null;
                }
                arrayFlag = true;
                globalArrayInitVals.clear();
                visit(ctx.initVal());
                arrayFlag = false;

                construct(arrayDim, arrayType.toString(), 0, 0);
                System.out.println();
                // 把获得的初始化值格式化输出【globalArrayInitVals】
            }
        }

        return null;
    }

    @Override
    public String visitInitVal(SysYParser.InitValContext ctx) {
        if(!arrayFlag) { // 普通常量的初始值
            return visit(ctx.exp());
        }
        else if(!globalFlag){ // 局部数组的初始值
            Variable array = assignStack.peek().get(nowInitArray);
            if(ctx.exp() != null) { // exp
                String value_reg = visit(ctx.exp());
                String ret_reg = "%r" + regId++;
                System.out.print("    " + ret_reg + " = getelementptr ");
                System.out.print(array.arrayType + ", ");
                System.out.print(array.arrayType + "* " + array.reg + ", i32 0");
                for (Integer idx : dimIndex) {
                    System.out.print(", i32 " + idx);
                }
                System.out.println();
                System.out.println("    store i32 " + value_reg + ", i32* " + ret_reg);
                // %1 = getelementptr [5 x [4 x i32]], [5 x [4 x i32]]* @a, i32 0, i32 2, i32 3
            }
            else { // {constInitVal, constInitVal, ...}
                for(int i=0;i<ctx.initVal().size();i++) { // 遍历下一维度
                    SysYParser.InitValContext e = ctx.initVal(i);
                    dimIndex.push(i);
                    if(dimIndex.size() > array.arrayDim.size()) {
                        // 初始值的维度超过了数组声明的维度
                        System.exit(-1);
                    }
                    visit(e);
                    dimIndex.pop();
                }
            }
            return null;
        }
        else { //TODO: 全局数组的初始值
            // 初值必须是常量表达式
            Variable array = assignStack.peek().get(nowInitArray);
            if(ctx.exp() != null) { // constExp
                String value = visit(ctx.exp());
                int totalIndex = 0;
                for(int i=0;i<dimIndex.size();i++) { // [2,3] [1,2] 1 4(543 210)
                    totalIndex = totalIndex * array.arrayDim.get(i) + dimIndex.get(i);
                }
                globalArrayInitVals.put(totalIndex, value); // 把初始化的每个元素的值放进list，备用
            }
            else { // {constInitVal, constInitVal, ...}
                for(int i=0;i<ctx.initVal().size();i++) { // 遍历下一维度
                    SysYParser.InitValContext e = ctx.initVal(i);
                    dimIndex.push(i);
                    if(dimIndex.size() > array.arrayDim.size()) {
                        // 初始值的维度超过了数组声明的维度
                        System.exit(-1);
                    }
                    visit(e);
                    dimIndex.pop();
                }
            }
            return null;
        }
    }

    // funcDef: funcType Ident '(' ')' block;
    @Override
    public String visitFuncDef(SysYParser.FuncDefContext ctx) {
        System.out.println("declare void @memset(i32*, i32, i32)");
        System.out.println("declare i32 @getint()");
        System.out.println("declare void @putint(i32)");
        System.out.println("declare i32 @getch()");
        System.out.println("declare void @putch(i32)");
        System.out.print("define dso_local ");
        visit(ctx.funcType());
        String funcName = ctx.Ident().getText();
        System.out.print("@" + funcName + "()");
        System.out.println("{");
        visit(ctx.block());
        System.out.println("}");
        return null;
    }

    // funcType: INT;
    @Override
    public String visitFuncType(SysYParser.FuncTypeContext ctx) {
        System.out.print("i32 ");
        return null;
    }

    // block: '{' (blockItem)* '}';
    @Override
    public String visitBlock(SysYParser.BlockContext ctx) {
        /*
        基本块是一段顺序执行的的指令，控制流只能从一个基本块的第一条指令开始执行，
        从最后一条指令退出基本块，或是跳转到其他基本块（包括自己）的第一条指令，或是退出程序。
        基本块的最后一条指令必须是一个跳转指令或返回指令，且中间不会出现跳转和返回指令。
        */
        Map<String,Variable> assignMap = new HashMap<>(); // 声明这个block的符号表
        assignStack.push(assignMap); // 压入符号表栈
        visitChildren(ctx);
        assignStack.pop(); // 删除这个block的符号表
        return null;
    }

    //. stmt: lVal '=' exp ';'
    // | (exp)? ';'
    //. | 'return' exp ';'
    //. | block
    //. | IF '(' cond ')' stmt ('else' stmt)?
    // | WHILE '(' cond ')' stmt // while
    // | BREAK ';'
    // | CONTINUE ';'
    @Override
    public String visitStmt(SysYParser.StmtContext ctx) {
        if(ctx.RETURN() != null) { // Return
            System.out.println("    ret i32 " + visit(ctx.exp()));
        }
        else if(ctx.lVal() != null) { // Assign
            String name = ctx.lVal().Ident().getText();
            Variable val = null;
            // 遍历符号表栈，找到最内层的变量并取出
            for(int i=assignStack.size()-1;i>=0;i--) {
                Map<String,Variable> assignMap = assignStack.get(i);
                if(assignMap.containsKey(name)) {
                    val = assignMap.get(name);
                    break;
                }
            }
            if(val == null) System.exit(-1);
            if(ctx.lVal().exp().size() != 0) { // 给数组赋值
                if(val.isConst || val.arrayDim.size() != ctx.lVal().exp().size()) System.exit(-1); // 如果是常量数组或维度不匹配

                // getelemenptr之前先把所有exp算出来
                ArrayList<String>expResults = new ArrayList<>();
                for(SysYParser.ExpContext e: ctx.lVal().exp()) {
                    // TODO: error: expected metadata after comma[, i32 %r30    %r31 = load i32, i32* %r10]
                    expResults.add(visit(e));
                }

                String elm_reg = "%r" + regId++;
                System.out.print("    " + elm_reg + " = getelementptr ");
                System.out.print(val.arrayType + ", ");
                System.out.print(val.arrayType + "* " + val.reg + ", i32 0");
                for(String exp: expResults) {
                    System.out.print(", i32 " + exp);
                }
                System.out.println();
                String source_reg = visit(ctx.exp());
                System.out.println("    store i32 " + source_reg + ", i32* " + elm_reg);
            }
            else if(!val.isConst) {
                val.valInit = true;
                String source_reg = visit(ctx.exp());
                System.out.println("    store i32 " + source_reg + ", i32* " + val.reg);
            }
            else System.exit(4);
        }
        else if(ctx.block() != null) { // Block
            visit(ctx.block());
        }
        else if(ctx.IF() != null) { // IF-ELSE
            String cond_reg = visit(ctx.cond());
            String true_reg = "%r" + regId++;
            String false_reg = "%r" + regId++;
            String end_reg = "%r" + regId++;
            System.out.println("    br i1 " + cond_reg + ", label " + true_reg + ", label " + false_reg);

            // TRUE-BLOCK
            System.out.println(true_reg.substring(1) + ":");
            visit(ctx.stmt(0));
            System.out.println("    br label " + end_reg);

            // FALSE-BLOCK
            System.out.println(false_reg.substring(1) + ":");
            if(ctx.stmt(1) != null) {
                visit(ctx.stmt(1));
            }
            System.out.println("    br label " + end_reg);

            // END-BLOCK
            System.out.println(end_reg.substring(1) + ":");
        }
        else if(ctx.WHILE() != null) { // While
            String while_reg = "%r" + regId++;
            String do_reg = "%r" + regId++;
            String out_reg = "%r" + regId++;
            loopLabels.push(new Pair<>(while_reg, out_reg));
            System.out.println("    br label " + while_reg);

            // 条件判断部分
            System.out.println(while_reg.substring(1) + ":");
            String cond_reg = visit(ctx.cond());
            System.out.println("    br i1 " + cond_reg + ", label " + do_reg + ", label " + out_reg);

            // 循环体部分
            System.out.println(do_reg.substring(1) + ":");
            visit(ctx.stmt(0));
            System.out.println("    br label " + while_reg);

            // 结束部分
            System.out.println(out_reg.substring(1) + ":");
            loopLabels.pop();
        }
        else if(ctx.BREAK() != null) {
            System.out.println("    br label " + loopLabels.peek().b); // 跳转到退出
        }
        else if(ctx.CONTINUE() != null) {
            System.out.println("    br label " + loopLabels.peek().a); // 跳转到条件判断
        }
        else if(ctx.exp() != null){ // exp-only
            visit(ctx.exp());
        }
        return null;
    }

    @Override
    public String visitLVal(SysYParser.LValContext ctx) {
        String name = ctx.Ident().getText();
        Variable val = null;
        // 遍历符号表栈，找到最内层的变量并取出
        for(int i=assignStack.size()-1;i>=0;i--) {
            Map<String,Variable> assignMap = assignStack.get(i);
            if(assignMap.containsKey(name)) {
                val = assignMap.get(name);
                break;
            }
        }
        if(val == null) System.exit(-1);
        if(ctx.exp().size() != 0) { // 如果是数组
            if(val.arrayDim.size() != ctx.exp().size()) System.exit(-1); // 如果维度不匹配

            // getelemenptr之前先把所有exp算出来，否则add等指令会夹杂在getelem中间，导致出错
            ArrayList<String>expResults = new ArrayList<>();
            for(SysYParser.ExpContext e: ctx.exp()) {
                expResults.add(visit(e));
            }

            String elm_reg = "%r" + regId++;
            System.out.print("    " + elm_reg + " = getelementptr ");
            System.out.print(val.arrayType + ", ");
            System.out.print(val.arrayType + "* " + val.reg + ", i32 0");
            for(String exp: expResults) {
                System.out.print(", i32 " + exp);
            }

            System.out.println();
            String target_reg = "%r" + regId++;
            System.out.println("    " + target_reg + " = load i32, i32* " + elm_reg);
            return target_reg;
        }
        else if(val.valInit) {
            if(!globalFlag) {
                String target_reg = "%r" + regId++;
                System.out.println("    " + target_reg + " = load i32, i32* " + val.reg);
                return target_reg;
            }
            else { // 全局变量，表达式压缩
                if(val.isConst) {
                    return String.valueOf(val.value);
                }
                else {
                    /*
                     全局变/常量声明中指定的初值表达式必须是常量表达式；
                     如果调取了变量的值，报错退出！！！
                    */
                    System.exit(-1);
                }
            }
        }
        else System.exit(5);
        return null;
    }

    // exp: addExp;
    @Override
    public String visitExp(SysYParser.ExpContext ctx) {
        return visit(ctx.addExp());
    }

    // addExp: mulExp | addExp op=(ADD|SUB) mulExp;
    @Override
    public String visitAddExp(SysYParser.AddExpContext ctx) {
        if(ctx.addExp() != null) {
            String a = visit(ctx.addExp());
            String b = visit(ctx.mulExp());
            if(!globalFlag) { // 如果是通常情况的表达式，正常计算输出每一步的指令
                String reg = "%r" + regId++;
                if(ctx.op.getType() == SysYParser.ADD) {
                    System.out.println("    " + reg + " = add i32 " + a + ", " + b);
                }
                else {
                    System.out.println("    " + reg + " = sub i32 " + a + ", " + b);
                }
                return reg;
            }
            else { // 如果是全局变量定义时的表达式，需要压缩（即计算后只返回一个数）
                int res = Integer.parseInt(a) + Integer.parseInt(b);
                return String.valueOf(res);
            }

        }
        else return visit(ctx.mulExp());
    }

    // mulExp: unaryExp | mulExp op=(MUL|DIV|MOD) unaryExp;
    @Override
    public String visitMulExp(SysYParser.MulExpContext ctx) {
        if(ctx.mulExp() != null) { // 多项
            String a = visit(ctx.mulExp());
            String b = visit(ctx.unaryExp());
            if(!globalFlag) { // 如果是通常情况的表达式，正常计算输出每一步的指令
                String reg = "%r" + regId++;
                if (ctx.op.getType() == SysYParser.MUL) {
                    System.out.println("    " + reg + " = mul i32 " + a + ", " + b);
                } else if (ctx.op.getType() == SysYParser.DIV) {
                    System.out.println("    " + reg + " = sdiv i32 " + a + ", " + b);
                } else {
                    System.out.println("    " + reg + " = srem i32 " + a + ", " + b);
                }
                return reg;
            }
            else { // 如果是全局变量定义时的表达式，需要压缩（即计算后只返回一个数）
                int res = Integer.parseInt(a) * Integer.parseInt(b);
                return String.valueOf(res);
            }

        }
        else return visit(ctx.unaryExp());
    }

    // unaryExp: unaryExp: primaryExp
    //    | op=(ADD|SUB|NOT) unaryExp
    //    | Ident '(' (funcRParams)? ')';
    @Override
    public String visitUnaryExp(SysYParser.UnaryExpContext ctx) {
        if(ctx.primaryExp() != null) { // 是[primaryExp]
            return visit(ctx.primaryExp());
        }
        else if(ctx.Ident() != null) { // [func]
            String func = ctx.Ident().getText();
            if(func.equals("getint") && ctx.funcRParams() == null) {
                String reg = "%r" + regId++;
                System.out.println("    " + reg + " = call i32 @getint()");
                return reg;
            } else if (func.equals("putint") && ctx.funcRParams() != null) {
                System.out.println("    call void @putint(i32 " + visit(ctx.funcRParams()) + ")");
            } else if (func.equals("getch") && ctx.funcRParams() == null) {
                String reg = "%r" + regId++;
                System.out.println("    " + reg + " = call i32 @getch()");
                return reg;
            } else if (func.equals("putch") && ctx.funcRParams() != null) {
                System.out.println("    call void @putch(i32 " + visit(ctx.funcRParams()) + ")");
            } else {
                System.exit(6);
            }
            return null;
        }
        else { // 是[unaryExp]
            String unaryExp = visit(ctx.unaryExp());
            if(ctx.op.getType() == SysYParser.SUB) { // Op=[-]，计算0-unaryExp
                if(!globalFlag) {
                    String reg = "%r" + regId++;
                    System.out.println("    " + reg + " = sub i32 0, " + unaryExp);
                    return reg;
                }
                else { // 全局变量，表达式压缩
                    int res = (-1) * Integer.parseInt(unaryExp);
                    return String.valueOf(res);
                }
            }
            else if(ctx.op.getType() == SysYParser.NOT) {
                String reg1 = "%r" + regId++;
                String reg2 = "%r" + regId++;
                System.out.println("    " + reg1 + " = icmp eq i32 " + unaryExp + ", 0");
                System.out.println("    " + reg2 + " = zext i1 " + reg1 + " to i32");
                return reg2;
            }
            else return unaryExp; // Op=[+]，无需计算直接向上返回
        }
    }

/*
    @Override
    public String visitFuncRParams(SysYParser.FuncRParamsContext ctx) {
//        String param = "";
//        for(SysYParser.ExpContext exp: ctx.exp()){
//            param = param + visit(exp);
//        }
//        return param;
        return visit(ctx.exp(0));
    }
*/

    // primaryExp: '(' exp ')' | lVal | number;
    @Override
    public String visitPrimaryExp(SysYParser.PrimaryExpContext ctx) {
        if(ctx.exp() != null) { // exp特判
            return visit(ctx.exp());
        }
        else return visitChildren(ctx);
    }

    // number: DECIMAL_CONST | OCTAL_CONST | HEXADECIMAL_CONST;
    @Override
    public String visitNumber(SysYParser.NumberContext ctx) {
        if(ctx.DECIMAL_CONST() != null) {
            return ctx.getText();
        }
        else if(ctx.HEXADECIMAL_CONST() != null) {
            return hex2dec(ctx.getText());
        }
        else return oct2dec(ctx.getText());
    }

    // relExp: addExp
    //    | relExp op=('<'|'>'|'<='|'>=') addExp;
    @Override
    public String visitRelExp(SysYParser.RelExpContext ctx) {
        if(ctx.relExp() == null) {
//            String reg_i1 = "%r" + regId++;
//            System.out.println("    " + reg_i1 + " = icmp ne i32 " + ctx.addExp() + ", 0");
//            return reg_i1;
            return visit(ctx.addExp());
        }
        else {
            String reg_a = visit(ctx.relExp());
            String reg_b = visit(ctx.addExp());
            String op = ctx.op.getText();
            String reg = "%r" + regId++;
            String reg_i32 = "%r" + regId++;
            switch (op) {
                case "<=":
                    System.out.println("    " + reg + " = icmp sle i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
                case ">=":
                    System.out.println("    " + reg + " = icmp sge i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
                case ">":
                    System.out.println("    " + reg + " = icmp sgt i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
                case "<":
                    System.out.println("    " + reg + " = icmp slt i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
            }
            if_unary_flag = false;
            return reg_i32;
        }
    }

    @Override
    public String visitEqExp(SysYParser.EqExpContext ctx) {
        if(ctx.eqExp() == null) {
            return visit(ctx.relExp());
        }
        else {
            String reg_a = visit(ctx.eqExp());
            String reg_b = visit(ctx.relExp());
            String op = ctx.op.getText();
            String reg = "%r" + regId++;
            String reg_i32 = "%r" + regId++;
            switch (op) {
                case "==":
                    System.out.println("    " + reg + " = icmp eq i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
                case "!=":
                    System.out.println("    " + reg + " = icmp ne i32 " + reg_a + ", " + reg_b);
                    System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");
                    break;
            }
            if_unary_flag = false;
            return reg_i32;
        }
    }

    @Override
    public String visitLAndExp(SysYParser.LAndExpContext ctx) {
        if(ctx.lAndExp() == null) {
            return visit(ctx.eqExp());
        }
        else {
            // 把a和b从i32转换到i1，然后执行and，最后把结果转换回i32！
            String reg_a = visit(ctx.lAndExp());
            String reg_a_i1 = "%r" + regId++;
            System.out.println("    " + reg_a_i1 + " = icmp ne i32 " + reg_a + ", 0");

            String reg_b = visit(ctx.eqExp());
            String reg_b_i1 = "%r" + regId++;
            System.out.println("    " + reg_b_i1 + " = icmp ne i32 " + reg_b + ", 0");

            String reg = "%r" + regId++;
            System.out.println("    " + reg + " = and i1 " + reg_a_i1 + ", " + reg_b_i1);

            String reg_i32 = "%r" + regId++;
            System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");

            if_unary_flag = false;
            return reg_i32;
        }
    }
// TODO: 条件表达式短路求值【挑战实验】
    // lOrExp: lAndExp | lOrExp '||' lAndExp;
    @Override
    public String visitLOrExp(SysYParser.LOrExpContext ctx) {
        if(ctx.lOrExp() == null) {
            return visit(ctx.lAndExp());
        }
        else {
            // 把a和b从i32转换到i1，然后执行and，最后把结果转换回i32！
            String reg_a = visit(ctx.lOrExp());
            String reg_a_i1 = "%r" + regId++;
            System.out.println("    " + reg_a_i1 + " = icmp ne i32 " + reg_a + ", 0");

            String reg_b = visit(ctx.lAndExp());
            String reg_b_i1 = "%r" + regId++;
            System.out.println("    " + reg_b_i1 + " = icmp ne i32 " + reg_b + ", 0");

            String reg = "%r" + regId++;
            System.out.println("    " + reg + " = or i1 " + reg_a_i1 + ", " + reg_b_i1);

            String reg_i32 = "%r" + regId++;
            System.out.println("    " + reg_i32 + " = zext i1 " + reg + " to i32");

            if_unary_flag = false;
            return reg_i32;
        }
    }

    // 特判if条件表达式中只含UnaryExp的情况
    boolean if_unary_flag;
    @Override
    public String visitCond(SysYParser.CondContext ctx) {
        if_unary_flag = true;
        String src_reg = visit(ctx.lOrExp());
        if(if_unary_flag) {
            String reg = "%r" + regId++;
            System.out.println("    " + reg + " = icmp ne i32 " + src_reg + ", 0");
            return reg;
        }
        else {
            String reg_i1 = "%r" + regId++;
            System.out.println("    " + reg_i1 + " = icmp ne i32 " + src_reg + ", 0");
            return reg_i1;
        }
    }

    public static String hex2dec(String hex) {
        BigInteger dec = new BigInteger(hex.substring(2), 16);
        return String.valueOf(dec.intValue());
    }

    public static String oct2dec(String hex) {
        BigInteger dec = new BigInteger(hex, 8);
        return String.valueOf(dec.intValue());
    }

}


class Variable {
    String name;
    String reg;
    boolean isConst;
    boolean valInit;
    int value;
    ArrayList<Integer>arrayDim;
    String arrayType;

    public Variable(String name, String reg, boolean isConst, boolean valInit) {
        this.name = name;
        this.reg = reg;
        this.isConst = isConst;
        this.valInit = valInit;
    }

    public Variable(String name, String reg, boolean isConst, boolean valInit, int value) {
        this.name = name;
        this.reg = reg;
        this.isConst = isConst;
        this.valInit = valInit;
        this.value = value;
    }

    public Variable(String name, String reg, boolean isConst, ArrayList<Integer>arrayDim, String arrayType) {
        this.name = name;
        this.reg = reg;
        this.isConst = isConst;
        this.arrayDim = arrayDim;
        this.arrayType = arrayType;
    }

}
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Visitor extends SysYBaseVisitor<String> {
    private Map<String,Variable> assignMap = new HashMap<>();
    private int regId = 1; // 从1开始

    // compUnit: funcDef;
    @Override
    public String visitCompUnit(SysYParser.CompUnitContext ctx) {
        return visitChildren(ctx);
    }

    // constDef: Ident '=' constInitVal;
    @Override
    public String visitConstDef(SysYParser.ConstDefContext ctx) {
        String name = ctx.Ident().getText();
        if(assignMap.containsKey(name)) { // 如果符号表中已经有这个名字，报错退出
            System.exit(2);
        }
        String ptr_reg = "%r" + regId++;
        System.out.println("    " + ptr_reg + " = alloca i32");
        String value = visit(ctx.constInitVal());
        System.out.println("    store i32 " + value + ", i32* " + ptr_reg);
        assignMap.put(name, new Variable(name, ptr_reg, true, true));
        return null;
    }

    // varDef: Ident | Ident '=' initVal;
    @Override
    public String visitVarDef(SysYParser.VarDefContext ctx) {
        String name = ctx.Ident().getText();
        if(assignMap.containsKey(name)) {
            System.exit(3);
        }
        String ptr_reg = "%r" + regId++;
        System.out.println("    " + ptr_reg + " = alloca i32");
        if(ctx.initVal() != null) { // 有指定初值
            String value = visit(ctx.initVal());
            System.out.println("    store i32 " + value + ", i32* " + ptr_reg);
            assignMap.put(name, new Variable(name, ptr_reg, false, true));
        }
        else { // 没有指定初值
            assignMap.put(name, new Variable(name, ptr_reg, false, false));
        }
        return null;
    }

    // funcDef: funcType Ident '(' ')' block;
    @Override
    public String visitFuncDef(SysYParser.FuncDefContext ctx) {
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
        // TODO: 遇到新的Block，重置符号表，分配label寄存器。
        //  这个实验只涉及从IF开辟新Block（if->stmt->block）
        /*基本块是一段顺序执行的的指令，控制流只能从一个基本块的第一条指令开始执行，
        从最后一条指令退出基本块，或是跳转到其他基本块（包括自己）的第一条指令，或是退出程序。
        基本块的最后一条指令必须是一个跳转指令或返回指令，且中间不会出现跳转和返回指令。
        */
        visitChildren(ctx);
        return null;
    }

    // stmt: lVal '=' exp ';' | (exp)? ';' | 'return' exp ';' | block | 'if' '(' cond ')' stmt ('else' stmt)?
    @Override
    public String visitStmt(SysYParser.StmtContext ctx) {
        if(ctx.RETURN() != null) {
            System.out.println("    ret i32 " + visit(ctx.exp()));
        }
        else if(ctx.lVal() != null) {
            Variable val = assignMap.get(ctx.lVal().getText());
            if(val != null && !val.isConst) {
                val.valInit = true;
                String source_reg = visit(ctx.exp());
                System.out.println("    store i32 " + source_reg + ", i32* " + val.reg);
            }
            else System.exit(4);
        }
        else if(ctx.block() != null) {
            visit(ctx.block());
        }
        else if(ctx.cond() != null) {
            // TODO: if-stmt
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
        else {
            visit(ctx.exp());
        }
        return null;
    }

    @Override
    public String visitLVal(SysYParser.LValContext ctx) {
        Variable val = assignMap.get(ctx.Ident().getText());
        if(val != null && val.valInit) {
            String target_reg = "%r" + regId++;
            System.out.println("    " + target_reg + " = load i32, i32* " + val.reg);
            return target_reg;
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
            String reg = "%r" + regId++;
            if(ctx.op.getType() == SysYParser.ADD) {
                System.out.println("    " + reg + " = add i32 " + a + ", " + b);
            }
            else {
                System.out.println("    " + reg + " = sub i32 " + a + ", " + b);
            }
            return reg;
        }
        else return visit(ctx.mulExp());
    }

    // mulExp: unaryExp | mulExp op=(MUL|DIV|MOD) unaryExp;
    @Override
    public String visitMulExp(SysYParser.MulExpContext ctx) {
        if(ctx.mulExp() != null) { // 多项
            String a = visit(ctx.mulExp());
            String b = visit(ctx.unaryExp());
            String reg = "%r" + regId++;
            if(ctx.op.getType() == SysYParser.MUL) {
                System.out.println("    " + reg + " = mul i32 " + a + ", " + b);
            }
            else if(ctx.op.getType() == SysYParser.DIV) {
                System.out.println("    " + reg + " = sdiv i32 " + a + ", " + b);
            }
            else {
                System.out.println("    " + reg + " = srem i32 " + a + ", " + b);
            }
            return reg;
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
                String reg = "%r" + regId++;
                System.out.println("    " + reg + " = sub i32 0, " + unaryExp);
                return reg;
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

    // TODO：完成四种条件表达式的编写
    // relExp: addExp
    //    | relExp op=('<'|'>'|'<='|'>=') addExp;
    @Override
    public String visitRelExp(SysYParser.RelExpContext ctx) {
        if(ctx.relExp() == null) {
            return visit(ctx.addExp());
        }
        else {
            String reg_a = visit(ctx.relExp());
            String reg_b = visit(ctx.addExp());
            String op = ctx.op.getText();
            String reg = "%r" + regId++;
            switch (op) {
                case "<=":
                    System.out.println("    " + reg + " = icmp sle i32 " + reg_a + ", " + reg_b);
                    break;
                case ">=":
                    System.out.println("    " + reg + " = icmp sge i32 " + reg_a + ", " + reg_b);
                    break;
                case ">":
                    System.out.println("    " + reg + " = icmp sgt i32 " + reg_a + ", " + reg_b);
                    break;
                case "<":
                    System.out.println("    " + reg + " = icmp slt i32 " + reg_a + ", " + reg_b);
                    break;
            }
            if_unary_flag = false;
            return reg;
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
            switch (op) {
                case "==":
                    System.out.println("    " + reg + " = icmp eq i32 " + reg_a + ", " + reg_b);
                    break;
                case "!=":
                    System.out.println("    " + reg + " = icmp ne i32 " + reg_a + ", " + reg_b);
                    break;
            }
            if_unary_flag = false;
            return reg;
        }
    }

    @Override
    public String visitLAndExp(SysYParser.LAndExpContext ctx) {
        if(ctx.lAndExp() == null) {
            return visit(ctx.eqExp());
        }
        else {
            String reg_a = visit(ctx.lAndExp());
            String reg_b = visit(ctx.eqExp());
            String reg = "%r" + regId++;
            System.out.println("    " + reg + " = and ii " + reg_a + ", " + reg_b);
            if_unary_flag = false;
            return reg;
        }
    }
// TODO: 短路求值挑战实验
    // lOrExp: lAndExp | lOrExp '||' lAndExp;
    @Override
    public String visitLOrExp(SysYParser.LOrExpContext ctx) {
        if(ctx.lOrExp() == null) {
            return visit(ctx.lAndExp());
        }
        else {
            String reg_a = visit(ctx.lOrExp());
            String reg_b = visit(ctx.lAndExp());
            String reg = "%r" + regId++;
            System.out.println("    " + reg + " = or ii " + reg_a + ", " + reg_b);
            if_unary_flag = false;
            return reg;
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
        else return src_reg;
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

    public Variable(String name, String reg, boolean isConst, boolean valInit) {
        this.name = name;
        this.reg = reg;
        this.isConst = isConst;
        this.valInit = valInit;
    }
}
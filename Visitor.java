import org.antlr.v4.runtime.ParserRuleContext;

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
            System.exit(1);
        }
        String ptr_reg = "%" + regId++;
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
            System.exit(1);
        }
        String ptr_reg = "%" + regId++;
        System.out.println("    " + ptr_reg + " = alloca i32");
        if(ctx.initVal() != null) { // 有指定初值
            String value = visit(ctx.initVal());
            System.out.println("    store i32 " + value + ", i32* " + ptr_reg);
            assignMap.put(name, new Variable(name, ptr_reg, true, true));
        }
        else { // 没有指定初值
            assignMap.put(name, new Variable(name, ptr_reg, true, false));
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
        System.out.print("@" + ctx.Ident().getText() + "()");
        return visit(ctx.block());
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
        System.out.println("{");
        visitChildren(ctx);
        System.out.println("}");
        return null;
    }

    // stmt: lVal '=' exp ';' | (exp)? ';' | 'return' exp ';';
    // TODO: 变量赋值|表达式逗你玩|函数返回
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
            else System.exit(1);
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
            String target_reg = "%" + regId++;
            System.out.println("    " + target_reg + " = load i32, i32* " + val.reg);
            return target_reg;
        }
        else System.exit(1);
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
            String reg = "%" + regId++;
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
            String reg = "%" + regId++;
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
    //    | op=(ADD|SUB) unaryExp
    //    | Ident '(' (funcRParams)? ')';
    @Override
    public String visitUnaryExp(SysYParser.UnaryExpContext ctx) {
        if(ctx.primaryExp() != null) { // 是[primaryExp]
            return visit(ctx.primaryExp());
        }
        else if(ctx.Ident() != null) { // [func]
            String func = ctx.Ident().getText();
            if(func.equals("getint") && ctx.funcRParams() == null) {
                String reg = "%" + regId++;
                System.out.println("    " + reg + " = call i32 @getint()");
                return reg;
            } else if (func.equals("putint") && ctx.funcRParams() != null) {
                System.out.println("    call void @putint(i32 " + visit(ctx.funcRParams()) + ")");
            } else if (func.equals("getch") && ctx.funcRParams() == null) {
                String reg = "%" + regId++;
                System.out.println("    " + reg + " = call i32 @getch()");
                return reg;
            } else if (func.equals("putch") && ctx.funcRParams() != null) {
                System.out.println("    call void @putch(i32 " + visit(ctx.funcRParams()) + ")");
            } else {
                System.exit(1);
            }
            return null;
        }
        else { // 是[unaryExp]
            String unaryExp = visit(ctx.unaryExp());
            if(ctx.op.getType() == SysYParser.SUB) { // Op=[-]，计算0-unaryExp
                String reg = "%" + regId++;
                System.out.println("    " + reg + " = sub i32 0, " + unaryExp);
                return reg;
            }
            else return unaryExp; // Op=[+]，无需计算直接向上返回
        }
    }

//    @Override
//    public String visitFuncRParams(SysYParser.FuncRParamsContext ctx) {
////        String param = "";
////        for(SysYParser.ExpContext exp: ctx.exp()){
////            param = param + visit(exp);
////        }
////        return param;
//        return visit(ctx.exp(0));
//    }

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
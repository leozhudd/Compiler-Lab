import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigInteger;

public class Visitor extends SysYBaseVisitor<String> {
    // private static Map<String,Integer> assignMap = new HashMap<>();

    // compUnit: funcDef;
    @Override
    public String visitCompUnit(SysYParser.CompUnitContext ctx) {
        return visitChildren(ctx);
    }

    // funcDef: funcType ident L_PAREN R_PAREN block;
    @Override
    public String visitFuncDef(SysYParser.FuncDefContext ctx) {
        return "define dso_local " + visit(ctx.funcType()) + visit(ctx.ident()) + visit(ctx.block());
    }

    // funcType: INT;
    @Override
    public String visitFuncType(SysYParser.FuncTypeContext ctx) {
        return "i32 ";
    }

    // ident: MAIN;
    @Override
    public String visitIdent(SysYParser.IdentContext ctx) {
        return "@main()";
    }

    // block: L_BRACE stmt R_BRACE;
    @Override
    public String visitBlock(SysYParser.BlockContext ctx) {
        return "{\n" + visit(ctx.stmt()) + "\n}";
    }

    // stmt: RETURN exp SEMICOLON;
    @Override
    public String visitStmt(SysYParser.StmtContext ctx) {
        return "    ret i32 "+ visit(ctx.exp());
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
            int p1 = str2int(visit(ctx.addExp()));
            int p2 = str2int(visit(ctx.mulExp()));
            if(ctx.op.getType() == SysYParser.ADD) {
                return String.valueOf(p1+p2);
            }
            else return String.valueOf(p1-p2);
        }
        else return visit(ctx.mulExp());
    }

    // mulExp: unaryExp | mulExp op=(MUL|DIV|MOD) unaryExp;
    @Override
    public String visitMulExp(SysYParser.MulExpContext ctx) {
        if(ctx.mulExp() != null) {
            int p1 = str2int(visit(ctx.mulExp()));
            int p2 = str2int(visit(ctx.unaryExp()));
            if(ctx.op.getType() == SysYParser.MUL) {
                return String.valueOf(p1*p2);
            }
            else if(ctx.op.getType() == SysYParser.DIV) {
                return String.valueOf(p1/p2);
            }
            else return String.valueOf(p1%p2);
        }
        return visit(ctx.unaryExp());
    }

    // unaryExp: primaryExp | unaryOp unaryExp;
    @Override
    public String visitUnaryExp(SysYParser.UnaryExpContext ctx) {
        if(ctx.primaryExp() != null) {
            return visit(ctx.primaryExp());
        }
        else {
            return String.valueOf(str2int(visit(ctx.unaryOp())) * str2int(visit(ctx.unaryExp())));
        }
    }

    // primaryExp: L_PAREN exp R_PAREN | number;
    @Override
    public String visitPrimaryExp(SysYParser.PrimaryExpContext ctx) {
        if(ctx.L_PAREN() != null) {
            return visit(ctx.exp());
        }
        else {
            return visit(ctx.number());
        }
    }

    // unaryOp: ADD | SUB;
    @Override
    public String visitUnaryOp(SysYParser.UnaryOpContext ctx) {
        if(ctx.ADD() != null) {
            return "1";
        }
        else return "-1";
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
    public static int str2int(String s) {
        return Integer.parseInt(s);
    }
}


/*
变量赋值
@Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        assignMap.put(id, value);
        return value;
    }

@Override
    public Integer visitId(MathParser.IdContext ctx)
    {
        String id = ctx.ID().getText();
        if(assignMap.containsKey(id))
        {
            return assignMap.get(id);
        }
        return 0;
    }
 */
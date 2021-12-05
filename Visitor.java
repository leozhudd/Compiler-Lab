import java.math.BigInteger;

public class Visitor extends SysYBaseVisitor<String> {
    // private static Map<String,Integer> assignMap = new HashMap<>();
    private int regId = 1; // 从1开始

    // compUnit: funcDef;
    @Override
    public String visitCompUnit(SysYParser.CompUnitContext ctx) {
        return visitChildren(ctx);
    }

    // funcDef: funcType ident L_PAREN R_PAREN block;
    @Override
    public String visitFuncDef(SysYParser.FuncDefContext ctx) {
        System.out.print("define dso_local ");
        return visitChildren(ctx);
    }

    // funcType: INT;
    @Override
    public String visitFuncType(SysYParser.FuncTypeContext ctx) {
        System.out.print("i32 ");
        return null;
    }

    // ident: MAIN;
    @Override
    public String visitIdent(SysYParser.IdentContext ctx) {
        System.out.print("@main() ");
        return null;
    }

    // block: L_BRACE stmt R_BRACE;
    @Override
    public String visitBlock(SysYParser.BlockContext ctx) {
        System.out.println("{");
        visit(ctx.stmt());
        System.out.println("}");
        return null;
    }

    // stmt: RETURN exp SEMICOLON;
    @Override
    public String visitStmt(SysYParser.StmtContext ctx) {
        System.out.println("    ret i32 " + visit(ctx.exp()));
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
        if(ctx.mulExp() != null) {
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

    // unaryExp: primaryExp | unaryOp unaryExp;
    @Override
    public String visitUnaryExp(SysYParser.UnaryExpContext ctx) {
        if(ctx.primaryExp() != null) { // 是[primaryExp]
            return visit(ctx.primaryExp());
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
//    @Override
//    public String visitUnaryOp(SysYParser.UnaryOpContext ctx) {
//        if(ctx.ADD() != null) {
//            return "1";
//        }
//        else return "-1";
//    }

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
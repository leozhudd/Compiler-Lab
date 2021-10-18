import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigInteger;

public class Visitor extends SysYBaseVisitor<String> {

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

    // stmt: RETURN number SEMICOLON;
    @Override
    public String visitStmt(SysYParser.StmtContext ctx) {
        return "    ret i32 "+ visit(ctx.number());
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
        else if(ctx.OCTAL_CONST() != null) {
            return oct2dec(ctx.getText());
        }
        else {
            // TODO: 三种都不是怎么处理
            return "SOME ERROR!";
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

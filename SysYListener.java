// Generated from /Users/leozhudd/Desktop/大三上课程Doc/编译原理/Labs/Lab1/SysY.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SysYParser}.
 */
public interface SysYListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SysYParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompUnit(SysYParser.CompUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompUnit(SysYParser.CompUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(SysYParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(SysYParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(SysYParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(SysYParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(SysYParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(SysYParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SysYParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SysYParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(SysYParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(SysYParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(SysYParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(SysYParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#addExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(SysYParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#addExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(SysYParser.AddExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(SysYParser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#mulExp}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(SysYParser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(SysYParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(SysYParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(SysYParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(SysYParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(SysYParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(SysYParser.NumberContext ctx);
}
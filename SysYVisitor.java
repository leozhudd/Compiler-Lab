// Generated from /Users/leozhudd/Desktop/大三上课程Doc/编译原理/Labs/Lab1/SysY.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SysYParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SysYVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SysYParser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(SysYParser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(SysYParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(SysYParser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(SysYParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SysYParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(SysYParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(SysYParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(SysYParser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(SysYParser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(SysYParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(SysYParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SysYParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(SysYParser.NumberContext ctx);
}
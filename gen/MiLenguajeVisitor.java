// Generated from C:/Users/juanm/Desktop/LENGUAJES/SmallBasicTraductor/grammar\MiLenguaje.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiLenguajeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiLenguajeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MiLenguajeParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MiLenguajeParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MiLenguajeParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#stepForLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStepForLoop(MiLenguajeParser.StepForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#toForLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToForLoop(MiLenguajeParser.ToForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(MiLenguajeParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#forParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForParameters(MiLenguajeParser.ForParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#whileLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(MiLenguajeParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#conditionalParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(MiLenguajeParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(MiLenguajeParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#elseif_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseif_statement(MiLenguajeParser.Elseif_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#else_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_statement(MiLenguajeParser.Else_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#subroutine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine(MiLenguajeParser.SubroutineContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#subroutine_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(MiLenguajeParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#expRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpRule(MiLenguajeParser.ExpRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#expRuleParent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpRuleParent(MiLenguajeParser.ExpRuleParentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#expRuleMinus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpRuleMinus(MiLenguajeParser.ExpRuleMinusContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MiLenguajeParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#variable_dict}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_dict(MiLenguajeParser.Variable_dictContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#builtIn_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltIn_name(MiLenguajeParser.BuiltIn_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#builtIn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltIn(MiLenguajeParser.BuiltInContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(MiLenguajeParser.TagContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#gotorule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotorule(MiLenguajeParser.GotoruleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(MiLenguajeParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MiLenguajeParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(MiLenguajeParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(MiLenguajeParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(MiLenguajeParser.AssignContext ctx);
}
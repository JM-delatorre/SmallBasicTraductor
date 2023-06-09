// Generated from C:/Users/juanm/Desktop/LENGUAJES/SmallBasicTraductor/grammar\MiLenguaje.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiLenguajeParser}.
 */
public interface MiLenguajeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MiLenguajeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MiLenguajeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MiLenguajeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MiLenguajeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MiLenguajeParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MiLenguajeParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#stepForLoop}.
	 * @param ctx the parse tree
	 */
	void enterStepForLoop(MiLenguajeParser.StepForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#stepForLoop}.
	 * @param ctx the parse tree
	 */
	void exitStepForLoop(MiLenguajeParser.StepForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#toForLoop}.
	 * @param ctx the parse tree
	 */
	void enterToForLoop(MiLenguajeParser.ToForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#toForLoop}.
	 * @param ctx the parse tree
	 */
	void exitToForLoop(MiLenguajeParser.ToForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(MiLenguajeParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(MiLenguajeParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#forParameters}.
	 * @param ctx the parse tree
	 */
	void enterForParameters(MiLenguajeParser.ForParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#forParameters}.
	 * @param ctx the parse tree
	 */
	void exitForParameters(MiLenguajeParser.ForParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(MiLenguajeParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(MiLenguajeParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#conditionalParams}.
	 * @param ctx the parse tree
	 */
	void enterConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#conditionalParams}.
	 * @param ctx the parse tree
	 */
	void exitConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(MiLenguajeParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(MiLenguajeParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(MiLenguajeParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(MiLenguajeParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#elseif_statement}.
	 * @param ctx the parse tree
	 */
	void enterElseif_statement(MiLenguajeParser.Elseif_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#elseif_statement}.
	 * @param ctx the parse tree
	 */
	void exitElseif_statement(MiLenguajeParser.Elseif_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(MiLenguajeParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(MiLenguajeParser.Else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#subroutine}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine(MiLenguajeParser.SubroutineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#subroutine}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine(MiLenguajeParser.SubroutineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#subroutine_call}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#subroutine_call}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(MiLenguajeParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(MiLenguajeParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#expRule}.
	 * @param ctx the parse tree
	 */
	void enterExpRule(MiLenguajeParser.ExpRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#expRule}.
	 * @param ctx the parse tree
	 */
	void exitExpRule(MiLenguajeParser.ExpRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#expRuleParent}.
	 * @param ctx the parse tree
	 */
	void enterExpRuleParent(MiLenguajeParser.ExpRuleParentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#expRuleParent}.
	 * @param ctx the parse tree
	 */
	void exitExpRuleParent(MiLenguajeParser.ExpRuleParentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#expRuleMinus}.
	 * @param ctx the parse tree
	 */
	void enterExpRuleMinus(MiLenguajeParser.ExpRuleMinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#expRuleMinus}.
	 * @param ctx the parse tree
	 */
	void exitExpRuleMinus(MiLenguajeParser.ExpRuleMinusContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MiLenguajeParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MiLenguajeParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#variable_dict}.
	 * @param ctx the parse tree
	 */
	void enterVariable_dict(MiLenguajeParser.Variable_dictContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#variable_dict}.
	 * @param ctx the parse tree
	 */
	void exitVariable_dict(MiLenguajeParser.Variable_dictContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#builtIn_name}.
	 * @param ctx the parse tree
	 */
	void enterBuiltIn_name(MiLenguajeParser.BuiltIn_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#builtIn_name}.
	 * @param ctx the parse tree
	 */
	void exitBuiltIn_name(MiLenguajeParser.BuiltIn_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#builtIn}.
	 * @param ctx the parse tree
	 */
	void enterBuiltIn(MiLenguajeParser.BuiltInContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#builtIn}.
	 * @param ctx the parse tree
	 */
	void exitBuiltIn(MiLenguajeParser.BuiltInContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(MiLenguajeParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(MiLenguajeParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#gotorule}.
	 * @param ctx the parse tree
	 */
	void enterGotorule(MiLenguajeParser.GotoruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#gotorule}.
	 * @param ctx the parse tree
	 */
	void exitGotorule(MiLenguajeParser.GotoruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(MiLenguajeParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(MiLenguajeParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(MiLenguajeParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(MiLenguajeParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(MiLenguajeParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(MiLenguajeParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(MiLenguajeParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(MiLenguajeParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiLenguajeParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(MiLenguajeParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiLenguajeParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(MiLenguajeParser.AssignContext ctx);
}
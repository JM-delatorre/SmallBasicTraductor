import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;


public class SmallBasicToPython extends MiLenguajeBaseListener {
    private final StringBuilder codeBuilder = new StringBuilder();
    private int counterTabs = 0;
    private String builtinName = "";
    private String builtinFunction = "";
    private List<String> bulitinArguments = new ArrayList<String>();

    @Override public void enterProgram(MiLenguajeParser.ProgramContext ctx) { }

    @Override public void exitProgram(MiLenguajeParser.ProgramContext ctx) { }

    //can't define the ';' here, because java doesn't have ';' after the for statements for example
    @Override public void enterStatement(MiLenguajeParser.StatementContext ctx) {
        if (counterTabs > 0){
            for (int i=0; i<counterTabs; i++){
                codeBuilder.append("\t");
            }
        }
    }

    @Override public void exitStatement(MiLenguajeParser.StatementContext ctx) {
        codeBuilder.append("\n");
    }

    @Override public void enterAssignment(MiLenguajeParser.AssignmentContext ctx) {}

    @Override public void exitAssignment(MiLenguajeParser.AssignmentContext ctx) { }

    @Override public void enterStepForLoop(MiLenguajeParser.StepForLoopContext ctx) {
        codeBuilder.append(",");
    }

    @Override public void exitStepForLoop(MiLenguajeParser.StepForLoopContext ctx) {
    }

    @Override public void enterToForLoop(MiLenguajeParser.ToForLoopContext ctx) {
        codeBuilder.append(",1+");
    }

    @Override public void exitToForLoop(MiLenguajeParser.ToForLoopContext ctx) {
        codeBuilder.append("):\n");
    }

    @Override public void enterForParameters(MiLenguajeParser.ForParametersContext ctx) {
        codeBuilder.append(" in range(");
    }
    @Override public void exitForParameters(MiLenguajeParser.ForParametersContext ctx) { }
    @Override public void enterForLoop(MiLenguajeParser.ForLoopContext ctx) {
        counterTabs += 1;
        codeBuilder.append("for ");
    }

    @Override public void exitForLoop(MiLenguajeParser.ForLoopContext ctx) {
        counterTabs-=1;
    }

    @Override public void enterWhileLoop(MiLenguajeParser.WhileLoopContext ctx) {
        counterTabs+=1;
        codeBuilder.append("while (");
    }

    @Override public void exitWhileLoop(MiLenguajeParser.WhileLoopContext ctx) {
        counterTabs-=1;
    }

    @Override public void enterConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx) { }

    @Override public void exitConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx) {
        codeBuilder.append("):\n");
    }

    @Override public void enterIf_statement(MiLenguajeParser.If_statementContext ctx) {
        counterTabs +=1;
        codeBuilder.append("if (");
    }

    @Override public void exitIf_statement(MiLenguajeParser.If_statementContext ctx) {
        counterTabs-=1;
    }

    /*
    @Override public void enterElseif_statement(MiLenguajeParser.Elseif_statementContext ctx) {
        counterTabs +=1;
        codeBuilder.append("elif (");
    }

    @Override public void exitElseif_statement(MiLenguajeParser.Elseif_statementContext ctx) {
        codeBuilder.append("}\n");
    }

    @Override public void enterElse_statement(MiLenguajeParser.Else_statementContext ctx) {
        codeBuilder.append("} else {\n");
    }

    @Override public void exitElse_statement(MiLenguajeParser.Else_statementContext ctx) {
        codeBuilder.append("}\n");
    }*/

    @Override public void enterSubroutine(MiLenguajeParser.SubroutineContext ctx) {
        String subroutineName = ctx.ID().getText();
        codeBuilder.append("def "+subroutineName + "():\n");
        counterTabs+=1;
    }

    @Override public void exitSubroutine(MiLenguajeParser.SubroutineContext ctx) {
        counterTabs-=1;
    }

    @Override public void enterSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx) {
        String subroutineName = ctx.ID().getText();
        codeBuilder.append(subroutineName + "()\n");
    }

    @Override public void exitSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx) { }

    @Override public void enterArgument_list(MiLenguajeParser.Argument_listContext ctx) { }

    @Override public void exitArgument_list(MiLenguajeParser.Argument_listContext ctx) { }

    @Override public void enterExpRule(MiLenguajeParser.ExpRuleContext ctx) {

    }

    @Override public void exitExpRule(MiLenguajeParser.ExpRuleContext ctx) { }

    @Override public void enterVariable(MiLenguajeParser.VariableContext ctx) {
        if(!(this.bulitinArguments.size() > 0)){
            if (ctx.getText().equals("return")) {
                codeBuilder.append(ctx.getText() + "josuehp");
            }else {
                codeBuilder.append(ctx.getText());
            }
        }
    }

    @Override public void exitVariable(MiLenguajeParser.VariableContext ctx) { }

    @Override public void enterNumber(MiLenguajeParser.NumberContext ctx) {
        codeBuilder.append(ctx.getText());
    }

    @Override public void exitNumber(MiLenguajeParser.NumberContext ctx) {

    }

    @Override public void enterString(MiLenguajeParser.StringContext ctx) { }

    @Override public void exitString(MiLenguajeParser.StringContext ctx) { }

    @Override public void enterBuiltIn(MiLenguajeParser.BuiltInContext ctx) {
        this.builtinName = ctx.builtIn_name().getText();
        this.builtinFunction = ctx.ID().getSymbol().getText();
        List<MiLenguajeParser.ExpRuleContext> args = ctx.argument_list().expRule();
        for (int i = 0; i < args.size(); i++) {
            if (((args.get(i)).getText()).equals("return")) {
                this.bulitinArguments.add((args.get(i)).getText() + "josuehp");
            }else{
                this.bulitinArguments.add((args.get(i)).getText());
            }
        }
    }

    @Override public void exitBuiltIn(MiLenguajeParser.BuiltInContext ctx) {
        switch (this.builtinName) {
            case "Array":
                arrayFunction();
                break;
            case "Stack":
                stackFunction();
                break;
            case "Program":
                programFunction();
                break;
            case "TextWindow":
                textWindowFunction();
        }
        this.builtinName = "";
        this.builtinFunction = "";
        this.bulitinArguments = new ArrayList<String>();
    }

    public void arrayFunction(){
        switch(this.builtinFunction){
            case "ContainsIndex":
                break;
            case "ContainsValue":
                break;
            case "GetAllIndices":
                break;
            case "GetItemCount":
                this.codeBuilder.append("len("+this.bulitinArguments.get(0)+")");
                break;
            case "IsArray":
                break;
            case "RemoveValue":
                this.codeBuilder.append("del "+this.bulitinArguments.get(0)+"["+this.bulitinArguments.get(1)+"]");
                break;
        }
    }

    public void stackFunction(){
        switch(this.builtinFunction){

            case "PushValue":
                this.codeBuilder.append(this.bulitinArguments.get(0)+".push("+this.bulitinArguments.get(1)+")");
                break;
            case "PopValue":
                this.codeBuilder.append(this.bulitinArguments.get(0)+".pop()");
                break;
            case "GetCount":
                this.codeBuilder.append(this.bulitinArguments.get(0)+".size()");
                break;
        }
    }

    public void textWindowFunction(){
        switch (this.builtinFunction) {
            case "Write":
                this.codeBuilder.append("print(");
                if (this.bulitinArguments.size()==0)
                    this.codeBuilder.append(")");
                else {
                    this.codeBuilder.append(this.bulitinArguments.get(0));
                    if (this.bulitinArguments.size()>1) {
                        for (int i = 1; i < this.bulitinArguments.size(); i++) {
                            this.codeBuilder.append(", "+this.bulitinArguments.get(i));
                        }
                    }
                    this.codeBuilder.append(",end=\"\")");
                }
                break;
            case "WriteLine":
                this.codeBuilder.append("print(");
                if (this.bulitinArguments.size()==0)
                    this.codeBuilder.append(")");
                else {
                    this.codeBuilder.append(this.bulitinArguments.get(0));
                    if (this.bulitinArguments.size()>1) {
                        for (int i = 1; i < this.bulitinArguments.size(); i++) {
                            this.codeBuilder.append(", "+this.bulitinArguments.get(i));
                        }
                    }
                    this.codeBuilder.append(")");
                }
                break;
            case "Read":
                String variableName = this.bulitinArguments.get(0);
                this.codeBuilder.append(variableName + " = input()");
                break;
            case "ReadNumber":
                String variableName2 = this.bulitinArguments.get(0);
                this.codeBuilder.append(variableName2 + " = int(input())");
                break;
        }
    }

    public void programFunction(){
        switch (this.builtinFunction) {
            case "delay":
                this.codeBuilder.append("time.sleep("+Integer.valueOf(this.bulitinArguments.get(0))/1000+")");
                break;
            case "End":
                this.codeBuilder.append("quit()\n");
        }
    }

    @Override public void enterTag(MiLenguajeParser.TagContext ctx) { }

    @Override public void exitTag(MiLenguajeParser.TagContext ctx) { }

    @Override public void enterGotorule(MiLenguajeParser.GotoruleContext ctx) { }

    @Override public void exitGotorule(MiLenguajeParser.GotoruleContext ctx) { }

    @Override public void enterOp(MiLenguajeParser.OpContext ctx) {
        codeBuilder.append(ctx.getText());
    }

    @Override public void exitOp(MiLenguajeParser.OpContext ctx) {

    }
    @Override public void enterAssign(MiLenguajeParser.AssignContext ctx) {
        codeBuilder.append(" = ");
    }

    @Override public void exitAssign(MiLenguajeParser.AssignContext ctx) { }

    @Override public void enterEveryRule(ParserRuleContext ctx) { }

    @Override public void exitEveryRule(ParserRuleContext ctx) { }

    @Override public void visitTerminal(TerminalNode node) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) { }

    public String getCode() {
        return codeBuilder.toString();
    }
}

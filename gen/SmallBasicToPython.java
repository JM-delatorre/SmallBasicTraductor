import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;


public class SmallBasicToPython extends MiLenguajeBaseListener {
    private final StringBuilder codeBuilder = new StringBuilder();
    private int counterTabs = 0;

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
        String condition = ctx.expRule().getText();
        codeBuilder.append("} else if ("+condition+") {\n");
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
        if (ctx.getText().equals("return")) {
            codeBuilder.append(ctx.getText() + "josuehp");
        }else {
            codeBuilder.append(ctx.getText());
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

    @Override public void enterBuiltIn(MiLenguajeParser.BuiltInContext ctx) { }

    @Override public void exitBuiltIn(MiLenguajeParser.BuiltInContext ctx) {

        String fname = ctx.builtIn_name().getText();
        String subfunction = ctx.ID().getSymbol().getText();
        System.out.println(fname);
        System.out.println(subfunction);
        List<MiLenguajeParser.ExpRuleContext> args = ctx.argument_list().expRule();

        switch (fname) {
            case "Array":
                arrayFunction(args, subfunction);
                break;
            case "Stack":
                stackFunction(args, subfunction);
                break;
            case "Program":
                /*delay(milliSeconds)
                End()
                 */
                switch (subfunction) {
                    case "delay":
                        this.codeBuilder.append("time.sleep("+Integer.valueOf(args.get(0).getText())/1000+")");
                        break;
                    case "End":
                        this.codeBuilder.append("quit()\n");
                }
                String programName = args.get(0).getText();
                this.codeBuilder.append("public static void " + programName + "() {\n");
                break;
            case "TextWindow":
                switch (subfunction) {
                    case "Write":
                        this.codeBuilder.append("print(");
                        if (args.size()==0)
                            this.codeBuilder.append(")\n");
                        else {
                            this.codeBuilder.append(args.get(0).getText());
                            if (args.size()>1) {
                                for (int i = 1; i < args.size(); i++) {
                                    this.codeBuilder.append(", "+args.get(i).getText());
                                }
                            }
                            this.codeBuilder.append(",end=\"\")\n");
                        }
                        break;
                    case "WriteLine":
                        this.codeBuilder.append("print(");
                        if (args.size()==0)
                            this.codeBuilder.append(")\n");
                        else {
                            this.codeBuilder.append(args.get(0).getText());
                            if (args.size()>1) {
                                for (int i = 1; i < args.size(); i++) {
                                    this.codeBuilder.append(", "+args.get(i).getText());
                                }
                            }
                            this.codeBuilder.append(")\n");
                        }
                        break;
                    case "Read":
                        String variableName = args.get(0).getText();
                        this.codeBuilder.append(variableName + " = input()\n");
                        break;
                    case "ReadNumber":
                        String variableName2 = args.get(0).getText();
                        this.codeBuilder.append(variableName2 + " = int(input())\n");
                        break;
                }
                break;
        }
    }

    public void arrayFunction(List<MiLenguajeParser.ExpRuleContext> args, String nameFunction){
        /*ContainsIndex(array, index)
                ContainsValue(array, value)
                GetAllIndices(array)
                GetItemCount(array)
                IsArray(var)
                RemoveValue(array, index)
         */
        switch(nameFunction){
            case "ContainsIndex":
                break;
            case "ContainsValue":
                break;
            case "GetAllIndices":
                break;
            case "GetItemCount":
                this.codeBuilder.append("len("+args.get(0).getText()+")\n");
                break;
            case "IsArray":
                break;
            case "RemoveValue":
                this.codeBuilder.append("del "+args.get(0).getText()+"["+args.get(1).getText()+"]\n");
                break;
        }
    }

    public void stackFunction(List<MiLenguajeParser.ExpRuleContext> args, String nameFunction){
        switch(nameFunction){

            case "PushValue":
                this.codeBuilder.append(args.get(0).getText()+".push("+args.get(1).getText()+")\n");
                break;
            case "PopValue":
                this.codeBuilder.append(args.get(0).getText()+".pop()\n");
                break;
            case "GetCount":
                this.codeBuilder.append(args.get(0).getText()+".size()\n");
                break;
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

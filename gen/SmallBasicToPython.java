import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Triple;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.text.Normalizer;

import java.util.*;


public class SmallBasicToPython extends MiLenguajeBaseListener {
    private final StringBuilder codeBuilder = new StringBuilder();
    private int counterTabs = 0;

    //params for special cases
    //check if we are in a condition
    private boolean condition = false;

    //check if we are in a builtin
    private String builtinName = "";
    private String builtinFunction = "";
    private List<String> builtinArguments = new ArrayList<String>();

    //check if an import type, a definition signature exists
    private Set<String> addons = new HashSet<>();
    private int counterCharactersImport = 0 ;
    private int counterCharactersFunction = 0;

    //tags : list of tuples, with
    //A : name of the tag
    //B : isActive : bool
    //C : start : (nameStart || gotoStart)
    private Map<String, Object []> tags = new HashMap<>();
    private String activeTag ="";


    @Override public void enterProgram(MiLenguajeParser.ProgramContext ctx) { }

    @Override public void exitProgram(MiLenguajeParser.ProgramContext ctx) { }

    //can't define the ';' here, because java doesn't have ';' after the for statements for example
    @Override public void enterStatement(MiLenguajeParser.StatementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if (counterTabs > 0){
            for (int i=0; i<counterTabs; i++){
                codeBuilder.append("\t");
            }
        }
    }

    @Override public void exitStatement(MiLenguajeParser.StatementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        codeBuilder.append("\n");
    }

    @Override public void enterAssignment(MiLenguajeParser.AssignmentContext ctx) {}

    @Override public void exitAssignment(MiLenguajeParser.AssignmentContext ctx) { }

    @Override public void enterStepForLoop(MiLenguajeParser.StepForLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        codeBuilder.append(",");
    }

    @Override public void exitStepForLoop(MiLenguajeParser.StepForLoopContext ctx) {
    }

    @Override public void enterToForLoop(MiLenguajeParser.ToForLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        codeBuilder.append(",1+");
    }

    @Override public void exitToForLoop(MiLenguajeParser.ToForLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        codeBuilder.append("):\n");
    }

    @Override public void enterForParameters(MiLenguajeParser.ForParametersContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        codeBuilder.append(" in range(");
    }
    @Override public void exitForParameters(MiLenguajeParser.ForParametersContext ctx) { }
    @Override public void enterForLoop(MiLenguajeParser.ForLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs += 1;
        codeBuilder.append("for ");
    }

    @Override public void exitForLoop(MiLenguajeParser.ForLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs-=1;
    }

    @Override public void enterWhileLoop(MiLenguajeParser.WhileLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs+=1;
        codeBuilder.append("while (");
    }

    @Override public void exitWhileLoop(MiLenguajeParser.WhileLoopContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs-=1;
    }

    @Override public void enterConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        this.condition = true;
    }

    @Override public void exitConditionalParams(MiLenguajeParser.ConditionalParamsContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        this.condition = false;
        codeBuilder.append("):\n");
    }

    @Override public void enterIf(MiLenguajeParser.IfContext ctx) {
    }

    @Override public void exitIf(MiLenguajeParser.IfContext ctx) { }

    @Override public void enterIf_statement(MiLenguajeParser.If_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs +=1;
        codeBuilder.append("if (");
    }

    @Override public void exitIf_statement(MiLenguajeParser.If_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs-=1;
    }

    @Override public void enterElseif_statement(MiLenguajeParser.Elseif_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if (counterTabs == 0)
            codeBuilder.append("elif (");
        else{
            for (int i=0; i<counterTabs; i++)
                codeBuilder.append("\t");
            codeBuilder.append("elif (");
        }
        counterTabs +=1;
    }

    @Override public void exitElseif_statement(MiLenguajeParser.Elseif_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs -=1;
    }

    @Override public void enterElse_statement(MiLenguajeParser.Else_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if (counterTabs == 0)
            codeBuilder.append("else : \n");
        else{
            for (int i=0; i<counterTabs; i++)
                codeBuilder.append("\t");
            codeBuilder.append("else :\n");
        }
        counterTabs +=1;
    }

    @Override public void exitElse_statement(MiLenguajeParser.Else_statementContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs -=1;
    }

    @Override public void enterSubroutine(MiLenguajeParser.SubroutineContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        String subroutineName = this.flattenToAscii(ctx.ID().getSymbol().getText());
        codeBuilder.append("def "+subroutineName + "():\n");
        counterTabs+=1;
    }

    @Override public void exitSubroutine(MiLenguajeParser.SubroutineContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        counterTabs-=1;
    }

    @Override public void enterSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        String subroutineName = this.flattenToAscii(ctx.ID().getSymbol().getText());
        codeBuilder.append(subroutineName + "()\n");
    }

    @Override public void exitSubroutine_call(MiLenguajeParser.Subroutine_callContext ctx) { }

    @Override public void enterArgument_list(MiLenguajeParser.Argument_listContext ctx) { }

    @Override public void exitArgument_list(MiLenguajeParser.Argument_listContext ctx) { }

    @Override public void enterExpRule(MiLenguajeParser.ExpRuleContext ctx) {

    }

    @Override public void exitExpRule(MiLenguajeParser.ExpRuleContext ctx) { }

    @Override public void enterVariable(MiLenguajeParser.VariableContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0)){
            if (this.flattenToAscii(ctx.ID().getSymbol().getText()).equals("return")) {
                codeBuilder.append(flattenToAscii(ctx.ID().getSymbol().getText()) + "josuehp");
            }else {
                codeBuilder.append(flattenToAscii(ctx.ID().getSymbol().getText()));
            }
        }
    }

    @Override public void exitVariable(MiLenguajeParser.VariableContext ctx) { }

    @Override public void enterVariable_dict(MiLenguajeParser.Variable_dictContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0))
            codeBuilder.append("[");
    }

    @Override public void exitVariable_dict(MiLenguajeParser.Variable_dictContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0))
            codeBuilder.append("]");
    }

    @Override public void enterNumber(MiLenguajeParser.NumberContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0))
            codeBuilder.append(ctx.getText());
    }

    @Override public void exitNumber(MiLenguajeParser.NumberContext ctx) {

    }

    @Override public void enterString(MiLenguajeParser.StringContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0))
            codeBuilder.append(ctx.getText());
    }

    @Override public void exitString(MiLenguajeParser.StringContext ctx) { }

    @Override public void enterBuiltIn(MiLenguajeParser.BuiltInContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        this.builtinName = ctx.builtIn_name().getText();
        this.builtinFunction = ctx.ID().getSymbol().getText();
        List<MiLenguajeParser.ExpRuleContext> args = ctx.argument_list().expRule();
        for (int i = 0; i < args.size(); i++) {
            if (((args.get(i)).getText()).equals("return")) {
                this.builtinArguments.add((args.get(i)).getText() + "josuehp");
            }else{
                this.builtinArguments.add((args.get(i)).getText());
            }
        }

    }

    @Override public void exitBuiltIn(MiLenguajeParser.BuiltInContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
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
        this.builtinArguments = new ArrayList<String>();
    }

    public void arrayFunction(){
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        switch(this.builtinFunction){
            case "ContainsIndex":
                break;
            case "ContainsValue":
                //create a function that checks if a value is in a list
                //the function would then be called at the supposed line
                if (!this.addons.contains("contains_value")){
                    String stringFunction = "def contains_value("+this.builtinArguments.get(0)+", "+this.builtinArguments.get(1)+") :\n";
                    stringFunction += "\tif (value in list):\n" +"\t\treturn true\n" +"\telse :\n" +"\t\treturn false\n\n";

                    this.codeBuilder.insert(counterCharactersFunction+counterCharactersImport,stringFunction);
                    this.addons.add("contains_value");
                    this.counterCharactersFunction += stringFunction.length();
                }
                this.codeBuilder.append("contains_value("+this.builtinArguments.get(0)+", "+this.builtinArguments.get(1)+")");
                break;
            case "GetAllIndices":
                break;
            case "GetItemCount":
                this.codeBuilder.append("len("+this.builtinArguments.get(0)+")");
                break;
            case "IsArray":
                this.codeBuilder.append("type("+this.builtinArguments.get(0)+" is list");
                break;
            case "RemoveValue":
                this.codeBuilder.append("del "+this.builtinArguments.get(0)+"["+this.builtinArguments.get(1)+"]");
                break;
        }
    }

    public void stackFunction(){
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        switch(this.builtinFunction){

            case "PushValue":
                this.codeBuilder.append(this.builtinArguments.get(0)+".push("+this.builtinArguments.get(1)+")");
                break;
            case "PopValue":
                this.codeBuilder.append(this.builtinArguments.get(0)+".pop()");
                break;
            case "GetCount":
                this.codeBuilder.append(this.builtinArguments.get(0)+".size()");
                break;
        }
    }

    public void textWindowFunction(){
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        switch (this.builtinFunction) {
            case "Write":
                this.codeBuilder.append("print(");
                if (this.builtinArguments.size()==0)
                    this.codeBuilder.append(")");
                else {
                    this.codeBuilder.append(this.builtinArguments.get(0));
                    if (this.builtinArguments.size()>1) {
                        for (int i = 1; i < this.builtinArguments.size(); i++) {
                            this.codeBuilder.append(", "+this.builtinArguments.get(i));
                        }
                    }
                    this.codeBuilder.append(",end=\"\")");
                }
                break;
            case "WriteLine":
                this.codeBuilder.append("print(");
                if (this.builtinArguments.size()==0)
                    this.codeBuilder.append(")");
                else {
                    this.codeBuilder.append(this.builtinArguments.get(0));
                    if (this.builtinArguments.size()>1) {
                        for (int i = 1; i < this.builtinArguments.size(); i++) {
                            this.codeBuilder.append(", "+this.builtinArguments.get(i));
                        }
                    }
                    this.codeBuilder.append(")");
                }
                break;
            case "Read":
                String variableName = this.builtinArguments.get(0);
                this.codeBuilder.append(variableName + " = input()");
                break;
            case "ReadNumber":
                String variableName2 = this.builtinArguments.get(0);
                this.codeBuilder.append(variableName2 + " = int(input())");
                break;
        }
    }

    public void programFunction(){
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        switch (this.builtinFunction) {
            case "delay":
                if (!this.addons.contains("time")){
                    this.codeBuilder.insert(0,"import time\n\n");
                    this.addons.add("time");
                    this.counterCharactersImport += "import time\n\n".length();
                }
                this.codeBuilder.append("time.sleep("+Float.valueOf(this.builtinArguments.get(0))/1000+")");
                break;
            case "End":
                this.codeBuilder.append("quit()\n");
        }
    }

    @Override public void enterTag(MiLenguajeParser.TagContext ctx) {
        // case : the tag doesn't already exist, it starts with the tag name
        // creation of a function
        if (!this.tags.keySet().contains(this.flattenToAscii(ctx.ID().getSymbol().getText()))){
            Object[] pair = new Object[2];
            pair[0] = true;
            pair[1] = "beginName";
            this.activeTag = this.flattenToAscii(ctx.ID().getSymbol().getText());
            this.tags.put(this.flattenToAscii(ctx.ID().getSymbol().getText()), pair);
            this.counterTabs +=1;
            this.codeBuilder.append("\ndef "+this.flattenToAscii(ctx.ID().getSymbol().getText())+"() :\n");
        // case : the tag calling ends here (it started with goto) (trad : it was active, so pair[0] was equal to true)
        // end of ignoring the lines
        } else if (((Boolean) (this.tags.get(this.flattenToAscii(ctx.ID().getSymbol().getText()))[0]))){
            Object[] pair = new Object[2];
            pair[0] = false;
            pair[1] = "beginName";
            this.tags.put(this.flattenToAscii(ctx.ID().getSymbol().getText()), pair);
        // case : the tag calling begins here
        // calling a function (already defined previously)
        } else {
            Object[] pair = new Object[2];
            pair[0] = true;
            pair[1] = "beginName";
            this.activeTag = this.flattenToAscii(ctx.ID().getSymbol().getText());
            this.tags.put(this.flattenToAscii(ctx.ID().getSymbol().getText()), pair);
            this.counterTabs +=1;
        }
    }

    @Override public void exitTag(MiLenguajeParser.TagContext ctx) { }

    @Override public void enterGotorule(MiLenguajeParser.GotoruleContext ctx) {
        // case : the tag doesn't already exist, it starts with the goto
        // ignoring the following lines
        if (!this.tags.keySet().contains(this.flattenToAscii(ctx.ID().getSymbol().getText()))){
            Object[] pair = new Object[2];
            pair[0] = true;
            pair[1] = "beginGoto";
            this.activeTag = this.flattenToAscii(ctx.ID().getSymbol().getText());
            this.tags.put(this.flattenToAscii(ctx.ID().getSymbol().getText()), pair);
            // case : the tag calling ends here (it started with the name) (trad : it was active, so pair[0] was equal to true)
            // it is the case of a recursive loop
        } else if (((Boolean) (this.tags.get(ctx.ID().getSymbol().getText())[0]))){
            Object[] pair = new Object[2];
            pair[0] = false;
            pair[1] = "beginName";
            this.tags.put(ctx.ID().getSymbol().getText(), pair);
            this.codeBuilder.append(this.flattenToAscii(ctx.ID().getSymbol().getText())+"()\n");
            // calling the function after it was written
            this.codeBuilder.append(this.flattenToAscii(ctx.ID().getSymbol().getText())+"()\n\n");
            this.counterTabs--;
            // case : the tag calling begins here, with goto (it already existed)
            // ignoring the following lines
        } else {
            Object[] pair = new Object[2];
            pair[0] = true;
            pair[1] = "beginGoto";
            this.activeTag = this.flattenToAscii(ctx.ID().getSymbol().getText());
            this.tags.put(this.flattenToAscii(ctx.ID().getSymbol().getText()), pair);
        }
    }

    @Override public void exitGotorule(MiLenguajeParser.GotoruleContext ctx) { }

    @Override public void enterOp(MiLenguajeParser.OpContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if(!(this.builtinArguments.size() > 0)) {
            if (ctx.getText().equals("Or"))
                codeBuilder.append(" or ");
            else if (ctx.getText().equals("And"))
                codeBuilder.append(" and ");
            else if (this.condition == true && ctx.getText().equals("="))
                codeBuilder.append(" == ");
            else
                codeBuilder.append(" " + ctx.getText() + " ");
        }
    }

    @Override public void exitOp(MiLenguajeParser.OpContext ctx) {}

    @Override public void enterBoolean(MiLenguajeParser.BooleanContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
        if (ctx.TRUE() != null)
            codeBuilder.append("true");
        else
            codeBuilder.append("false");
    }

    @Override public void exitBoolean(MiLenguajeParser.BooleanContext ctx) { }

    @Override public void enterAssign(MiLenguajeParser.AssignContext ctx) {
        if ((!this.activeTag.equals("")) && ((Boolean) (this.tags.get(this.activeTag)[0])) && this.tags.get(this.activeTag)[1].equals("beginGoto"))
            return;
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

    //to manage accents
    public static String flattenToAscii(String string) {
        char[] out = new char[string.length()];
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        int j = 0;
        for (int i = 0, n = string.length(); i < n; ++i) {
            char c = string.charAt(i);
            if (c <= '\u007F') out[j++] = c;
        }
        return new String(out);
    }

    public String getCode() {
        return codeBuilder.toString();
    }
}

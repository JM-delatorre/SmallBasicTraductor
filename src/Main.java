import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            // crear un analizador léxico a partir de la entrada (archivo  o consola)
            MiLenguajeLexer lexer;
            if (args.length>0)
                lexer = new MiLenguajeLexer(CharStreams.fromFileName(args[0]));
            else
                lexer = new MiLenguajeLexer(CharStreams.fromStream(System.in));
            // Identificar al analizador léxico como fuente de tokens para el sintactico
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // Crear el objeto del analizador sintáctico a partir del buffer de tokens
            MiLenguajeParser parser = new MiLenguajeParser(tokens);
            ParseTree tree = parser.program(); // Iniciar el analisis sintáctico en la regla inicial: r
            System.out.println(tree.toStringTree(parser)); // imprime el arbol al estilo LISP
            System.out.println("Hola mundo");

            //walker pasa por los tokens
            ParseTreeWalker walker = new ParseTreeWalker();
            SmallBasicToPython listener = new SmallBasicToPython();
            walker.walk(listener, tree);

            System.out.println(listener.getCode());

        } catch (Exception e){
            System.err.println("Error (Test): " + e);
        }
    }
}
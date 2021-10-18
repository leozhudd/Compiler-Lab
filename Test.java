import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;


public class Test {
    public static void main(String[] args) {
        try {
            // ANTLRInputStream input = new ANTLRInputStream(System.in);// ANTLRInputStream已经弃用，用CharStream代替

            CharStream input = CharStreams.fromFileName(args[0]);
            SysYLexer lexer = new SysYLexer(input);
            SysYParser parser = new SysYParser(new CommonTokenStream(lexer));

            /* 使用Listener方法遍历语法树
            parser.addParseListener(new Listener());
            parser.compUnit();*/

            // 自定义错误分析器
            lexer.removeErrorListeners();
            lexer.addErrorListener(new ErrorListener());
            parser.removeErrorListeners();
            parser.addErrorListener(new ErrorListener());

            // 使用Visitor方法遍历语法树
            ParseTree tree = parser.compUnit();
            Visitor visitor = new Visitor();

            File file = new File(args[1]);
            BufferedWriter out  = new BufferedWriter(new FileWriter(file));
            out.write(visitor.visit(tree));
            out.close();
            // System.out.println(visitor.visit(tree));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

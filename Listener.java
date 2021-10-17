import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Listener extends SysYBaseListener {
    @Override
    public void enterFuncDef(SysYParser.FuncDefContext ctx) {
        super.enterFuncDef(ctx);
    }

    @Override
    public void exitFuncDef(SysYParser.FuncDefContext ctx) {
        super.exitFuncDef(ctx);
    }
}
// Generated from /Users/leozhudd/Desktop/大三上课程Doc/编译原理/Labs/Lab4/SysY.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SysYLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, ADD=19, SUB=20, MUL=21, DIV=22, MOD=23, INT=24, RETURN=25, NOT=26, 
		HEXADECIMAL_CONST=27, OCTAL_CONST=28, DECIMAL_CONST=29, WHITE_SPACE=30, 
		LINE_COMMENT=31, COMMENT=32, Ident=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "ADD", "SUB", "MUL", "DIV", "MOD", "INT", "RETURN", "NOT", "HEXADECIMAL_CONST", 
			"OCTAL_CONST", "DECIMAL_CONST", "WHITE_SPACE", "LINE_COMMENT", "COMMENT", 
			"Ident"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'const'", "','", "';'", "'='", "'('", "')'", "'{'", "'}'", "'if'", 
			"'else'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&&'", "'||'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'int'", "'return'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "ADD", "SUB", "MUL", "DIV", 
			"MOD", "INT", "RETURN", "NOT", "HEXADECIMAL_CONST", "OCTAL_CONST", "DECIMAL_CONST", 
			"WHITE_SPACE", "LINE_COMMENT", "COMMENT", "Ident"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SysYLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SysY.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00d0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\5\34\u0093\n\34\3\34\6\34\u0096\n\34\r\34\16\34\u0097"+
		"\3\35\3\35\7\35\u009c\n\35\f\35\16\35\u009f\13\35\3\36\3\36\7\36\u00a3"+
		"\n\36\f\36\16\36\u00a6\13\36\3\37\6\37\u00a9\n\37\r\37\16\37\u00aa\3\37"+
		"\3\37\3 \3 \3 \3 \7 \u00b3\n \f \16 \u00b6\13 \3 \3 \3 \3 \3!\3!\3!\3"+
		"!\7!\u00c0\n!\f!\16!\u00c3\13!\3!\3!\3!\3!\3!\3\"\3\"\7\"\u00cc\n\"\f"+
		"\"\16\"\u00cf\13\"\4\u00b4\u00c1\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\t\5\2\62;CHch\3\2\62"+
		"9\3\2\63;\3\2\62;\5\2\13\f\17\17\"\"\5\2C\\aac|\6\2\62;C\\aac|\2\u00d7"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5K\3\2\2"+
		"\2\7M\3\2\2\2\tO\3\2\2\2\13Q\3\2\2\2\rS\3\2\2\2\17U\3\2\2\2\21W\3\2\2"+
		"\2\23Y\3\2\2\2\25\\\3\2\2\2\27a\3\2\2\2\31c\3\2\2\2\33e\3\2\2\2\35h\3"+
		"\2\2\2\37k\3\2\2\2!n\3\2\2\2#q\3\2\2\2%t\3\2\2\2\'w\3\2\2\2)y\3\2\2\2"+
		"+{\3\2\2\2-}\3\2\2\2/\177\3\2\2\2\61\u0081\3\2\2\2\63\u0085\3\2\2\2\65"+
		"\u008c\3\2\2\2\67\u0092\3\2\2\29\u0099\3\2\2\2;\u00a0\3\2\2\2=\u00a8\3"+
		"\2\2\2?\u00ae\3\2\2\2A\u00bb\3\2\2\2C\u00c9\3\2\2\2EF\7e\2\2FG\7q\2\2"+
		"GH\7p\2\2HI\7u\2\2IJ\7v\2\2J\4\3\2\2\2KL\7.\2\2L\6\3\2\2\2MN\7=\2\2N\b"+
		"\3\2\2\2OP\7?\2\2P\n\3\2\2\2QR\7*\2\2R\f\3\2\2\2ST\7+\2\2T\16\3\2\2\2"+
		"UV\7}\2\2V\20\3\2\2\2WX\7\177\2\2X\22\3\2\2\2YZ\7k\2\2Z[\7h\2\2[\24\3"+
		"\2\2\2\\]\7g\2\2]^\7n\2\2^_\7u\2\2_`\7g\2\2`\26\3\2\2\2ab\7>\2\2b\30\3"+
		"\2\2\2cd\7@\2\2d\32\3\2\2\2ef\7>\2\2fg\7?\2\2g\34\3\2\2\2hi\7@\2\2ij\7"+
		"?\2\2j\36\3\2\2\2kl\7?\2\2lm\7?\2\2m \3\2\2\2no\7#\2\2op\7?\2\2p\"\3\2"+
		"\2\2qr\7(\2\2rs\7(\2\2s$\3\2\2\2tu\7~\2\2uv\7~\2\2v&\3\2\2\2wx\7-\2\2"+
		"x(\3\2\2\2yz\7/\2\2z*\3\2\2\2{|\7,\2\2|,\3\2\2\2}~\7\61\2\2~.\3\2\2\2"+
		"\177\u0080\7\'\2\2\u0080\60\3\2\2\2\u0081\u0082\7k\2\2\u0082\u0083\7p"+
		"\2\2\u0083\u0084\7v\2\2\u0084\62\3\2\2\2\u0085\u0086\7t\2\2\u0086\u0087"+
		"\7g\2\2\u0087\u0088\7v\2\2\u0088\u0089\7w\2\2\u0089\u008a\7t\2\2\u008a"+
		"\u008b\7p\2\2\u008b\64\3\2\2\2\u008c\u008d\7#\2\2\u008d\66\3\2\2\2\u008e"+
		"\u008f\7\62\2\2\u008f\u0093\7z\2\2\u0090\u0091\7\62\2\2\u0091\u0093\7"+
		"Z\2\2\u0092\u008e\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0095\3\2\2\2\u0094"+
		"\u0096\t\2\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0098\3\2\2\2\u00988\3\2\2\2\u0099\u009d\7\62\2\2\u009a\u009c"+
		"\t\3\2\2\u009b\u009a\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e:\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a4\t\4\2\2"+
		"\u00a1\u00a3\t\5\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5<\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7"+
		"\u00a9\t\6\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\37\2\2\u00ad"+
		">\3\2\2\2\u00ae\u00af\7\61\2\2\u00af\u00b0\7\61\2\2\u00b0\u00b4\3\2\2"+
		"\2\u00b1\u00b3\13\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b7\u00b8\7\f\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\b \2\2\u00ba"+
		"@\3\2\2\2\u00bb\u00bc\7\61\2\2\u00bc\u00bd\7,\2\2\u00bd\u00c1\3\2\2\2"+
		"\u00be\u00c0\13\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00c2"+
		"\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00c5\7,\2\2\u00c5\u00c6\7\61\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b!"+
		"\2\2\u00c8B\3\2\2\2\u00c9\u00cd\t\7\2\2\u00ca\u00cc\t\b\2\2\u00cb\u00ca"+
		"\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"D\3\2\2\2\u00cf\u00cd\3\2\2\2\13\2\u0092\u0097\u009d\u00a4\u00aa\u00b4"+
		"\u00c1\u00cd\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
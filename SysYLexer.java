// Generated from /Users/leozhudd/Desktop/大三上课程Doc/编译原理/Labs/Lab3/SysY.g4 by ANTLR 4.9.1
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, ADD=9, 
		SUB=10, MUL=11, DIV=12, MOD=13, INT=14, RETURN=15, HEXADECIMAL_CONST=16, 
		OCTAL_CONST=17, DECIMAL_CONST=18, WHITE_SPACE=19, LINE_COMMENT=20, COMMENT=21, 
		Ident=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "ADD", 
			"SUB", "MUL", "DIV", "MOD", "INT", "RETURN", "HEXADECIMAL_CONST", "OCTAL_CONST", 
			"DECIMAL_CONST", "WHITE_SPACE", "LINE_COMMENT", "COMMENT", "Ident"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'const'", "','", "';'", "'='", "'('", "')'", "'{'", "'}'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'int'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "ADD", "SUB", "MUL", 
			"DIV", "MOD", "INT", "RETURN", "HEXADECIMAL_CONST", "OCTAL_CONST", "DECIMAL_CONST", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u009a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\5\21]\n\21\3\21\6\21`\n"+
		"\21\r\21\16\21a\3\22\3\22\7\22f\n\22\f\22\16\22i\13\22\3\23\3\23\7\23"+
		"m\n\23\f\23\16\23p\13\23\3\24\6\24s\n\24\r\24\16\24t\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\7\25}\n\25\f\25\16\25\u0080\13\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\26\7\26\u008a\n\26\f\26\16\26\u008d\13\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\7\27\u0096\n\27\f\27\16\27\u0099\13\27\4~\u008b"+
		"\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\t\5\2\62;CHch\3\2\629\3\2\63"+
		";\3\2\62;\5\2\13\f\17\17\"\"\5\2C\\aac|\6\2\62;C\\aac|\2\u00a1\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\65\3"+
		"\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17?\3\2\2\2\21A"+
		"\3\2\2\2\23C\3\2\2\2\25E\3\2\2\2\27G\3\2\2\2\31I\3\2\2\2\33K\3\2\2\2\35"+
		"M\3\2\2\2\37Q\3\2\2\2!\\\3\2\2\2#c\3\2\2\2%j\3\2\2\2\'r\3\2\2\2)x\3\2"+
		"\2\2+\u0085\3\2\2\2-\u0093\3\2\2\2/\60\7e\2\2\60\61\7q\2\2\61\62\7p\2"+
		"\2\62\63\7u\2\2\63\64\7v\2\2\64\4\3\2\2\2\65\66\7.\2\2\66\6\3\2\2\2\67"+
		"8\7=\2\28\b\3\2\2\29:\7?\2\2:\n\3\2\2\2;<\7*\2\2<\f\3\2\2\2=>\7+\2\2>"+
		"\16\3\2\2\2?@\7}\2\2@\20\3\2\2\2AB\7\177\2\2B\22\3\2\2\2CD\7-\2\2D\24"+
		"\3\2\2\2EF\7/\2\2F\26\3\2\2\2GH\7,\2\2H\30\3\2\2\2IJ\7\61\2\2J\32\3\2"+
		"\2\2KL\7\'\2\2L\34\3\2\2\2MN\7k\2\2NO\7p\2\2OP\7v\2\2P\36\3\2\2\2QR\7"+
		"t\2\2RS\7g\2\2ST\7v\2\2TU\7w\2\2UV\7t\2\2VW\7p\2\2W \3\2\2\2XY\7\62\2"+
		"\2Y]\7z\2\2Z[\7\62\2\2[]\7Z\2\2\\X\3\2\2\2\\Z\3\2\2\2]_\3\2\2\2^`\t\2"+
		"\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\"\3\2\2\2cg\7\62\2\2df\t"+
		"\3\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h$\3\2\2\2ig\3\2\2\2jn\t"+
		"\4\2\2km\t\5\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o&\3\2\2\2pn\3"+
		"\2\2\2qs\t\6\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\b"+
		"\24\2\2w(\3\2\2\2xy\7\61\2\2yz\7\61\2\2z~\3\2\2\2{}\13\2\2\2|{\3\2\2\2"+
		"}\u0080\3\2\2\2~\177\3\2\2\2~|\3\2\2\2\177\u0081\3\2\2\2\u0080~\3\2\2"+
		"\2\u0081\u0082\7\f\2\2\u0082\u0083\3\2\2\2\u0083\u0084\b\25\2\2\u0084"+
		"*\3\2\2\2\u0085\u0086\7\61\2\2\u0086\u0087\7,\2\2\u0087\u008b\3\2\2\2"+
		"\u0088\u008a\13\2\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u008c"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e"+
		"\u008f\7,\2\2\u008f\u0090\7\61\2\2\u0090\u0091\3\2\2\2\u0091\u0092\b\26"+
		"\2\2\u0092,\3\2\2\2\u0093\u0097\t\7\2\2\u0094\u0096\t\b\2\2\u0095\u0094"+
		"\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		".\3\2\2\2\u0099\u0097\3\2\2\2\13\2\\agnt~\u008b\u0097\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from /Users/leozhudd/Desktop/大三上课程Doc/编译原理/Labs/Lab1/SysY.g4 by ANTLR 4.9.1
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
		ADD=1, SUB=2, MUL=3, DIV=4, MOD=5, L_PAREN=6, R_PAREN=7, L_BRACE=8, R_BRACE=9, 
		MAIN=10, RETURN=11, SEMICOLON=12, INT=13, HEXADECIMAL_CONST=14, OCTAL_CONST=15, 
		DECIMAL_CONST=16, WHITE_SPACE=17, LINE_COMMENT=18, COMMENT=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ADD", "SUB", "MUL", "DIV", "MOD", "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", 
			"MAIN", "RETURN", "SEMICOLON", "INT", "HEXADECIMAL_CONST", "OCTAL_CONST", 
			"DECIMAL_CONST", "WHITE_SPACE", "LINE_COMMENT", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", "'{'", "'}'", 
			"'main'", "'return'", "';'", "'int'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ADD", "SUB", "MUL", "DIV", "MOD", "L_PAREN", "R_PAREN", "L_BRACE", 
			"R_BRACE", "MAIN", "RETURN", "SEMICOLON", "INT", "HEXADECIMAL_CONST", 
			"OCTAL_CONST", "DECIMAL_CONST", "WHITE_SPACE", "LINE_COMMENT", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u0088\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\5\17R\n\17"+
		"\3\17\6\17U\n\17\r\17\16\17V\3\20\3\20\7\20[\n\20\f\20\16\20^\13\20\3"+
		"\21\3\21\7\21b\n\21\f\21\16\21e\13\21\3\22\6\22h\n\22\r\22\16\22i\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\7\23r\n\23\f\23\16\23u\13\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\7\24\177\n\24\f\24\16\24\u0082\13\24\3\24\3"+
		"\24\3\24\3\24\3\24\4s\u0080\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\7\5\2\62;C"+
		"Hch\3\2\629\3\2\63;\3\2\62;\5\2\13\f\17\17\"\"\2\u008e\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r"+
		"\63\3\2\2\2\17\65\3\2\2\2\21\67\3\2\2\2\239\3\2\2\2\25;\3\2\2\2\27@\3"+
		"\2\2\2\31G\3\2\2\2\33I\3\2\2\2\35Q\3\2\2\2\37X\3\2\2\2!_\3\2\2\2#g\3\2"+
		"\2\2%m\3\2\2\2\'z\3\2\2\2)*\7-\2\2*\4\3\2\2\2+,\7/\2\2,\6\3\2\2\2-.\7"+
		",\2\2.\b\3\2\2\2/\60\7\61\2\2\60\n\3\2\2\2\61\62\7\'\2\2\62\f\3\2\2\2"+
		"\63\64\7*\2\2\64\16\3\2\2\2\65\66\7+\2\2\66\20\3\2\2\2\678\7}\2\28\22"+
		"\3\2\2\29:\7\177\2\2:\24\3\2\2\2;<\7o\2\2<=\7c\2\2=>\7k\2\2>?\7p\2\2?"+
		"\26\3\2\2\2@A\7t\2\2AB\7g\2\2BC\7v\2\2CD\7w\2\2DE\7t\2\2EF\7p\2\2F\30"+
		"\3\2\2\2GH\7=\2\2H\32\3\2\2\2IJ\7k\2\2JK\7p\2\2KL\7v\2\2L\34\3\2\2\2M"+
		"N\7\62\2\2NR\7z\2\2OP\7\62\2\2PR\7Z\2\2QM\3\2\2\2QO\3\2\2\2RT\3\2\2\2"+
		"SU\t\2\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\36\3\2\2\2X\\\7\62"+
		"\2\2Y[\t\3\2\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2] \3\2\2\2^\\"+
		"\3\2\2\2_c\t\4\2\2`b\t\5\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d"+
		"\"\3\2\2\2ec\3\2\2\2fh\t\6\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2"+
		"jk\3\2\2\2kl\b\22\2\2l$\3\2\2\2mn\7\61\2\2no\7\61\2\2os\3\2\2\2pr\13\2"+
		"\2\2qp\3\2\2\2ru\3\2\2\2st\3\2\2\2sq\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\7\f"+
		"\2\2wx\3\2\2\2xy\b\23\2\2y&\3\2\2\2z{\7\61\2\2{|\7,\2\2|\u0080\3\2\2\2"+
		"}\177\13\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080\u0081\3\2\2\2\u0080~"+
		"\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7,\2\2\u0084"+
		"\u0085\7\61\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\24\2\2\u0087(\3\2\2"+
		"\2\n\2QV\\cis\u0080\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
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
		L_PAREN=1, R_PAREN=2, L_BRACE=3, R_BRACE=4, MAIN=5, RETURN=6, SEMICOLON=7, 
		INT=8, HEXADECIMAL_CONST=9, OCTAL_CONST=10, DECIMAL_CONST=11, WHITE_SPACE=12, 
		LINE_COMMENT=13, COMMENT=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", "MAIN", "RETURN", "SEMICOLON", 
			"INT", "HEXADECIMAL_CONST", "OCTAL_CONST", "DECIMAL_CONST", "WHITE_SPACE", 
			"LINE_COMMENT", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'main'", "'return'", "';'", "'int'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "L_PAREN", "R_PAREN", "L_BRACE", "R_BRACE", "MAIN", "RETURN", "SEMICOLON", 
			"INT", "HEXADECIMAL_CONST", "OCTAL_CONST", "DECIMAL_CONST", "WHITE_SPACE", 
			"LINE_COMMENT", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20t\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\5\n>\n\n\3\n\6\nA\n\n\r\n\16\nB\3\13\3\13\7\13G\n"+
		"\13\f\13\16\13J\13\13\3\f\3\f\7\fN\n\f\f\f\16\fQ\13\f\3\r\6\rT\n\r\r\r"+
		"\16\rU\3\r\3\r\3\16\3\16\3\16\3\16\7\16^\n\16\f\16\16\16a\13\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17k\n\17\f\17\16\17n\13\17\3\17\3"+
		"\17\3\17\3\17\3\17\4_l\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\3\2\7\5\2\62;CHch\3\2\629\3\2\63;\3\2\62;\5"+
		"\2\13\f\17\17\"\"\2z\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2"+
		"\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13\'\3\2\2\2\r,\3\2\2\2\17\63\3\2"+
		"\2\2\21\65\3\2\2\2\23=\3\2\2\2\25D\3\2\2\2\27K\3\2\2\2\31S\3\2\2\2\33"+
		"Y\3\2\2\2\35f\3\2\2\2\37 \7*\2\2 \4\3\2\2\2!\"\7+\2\2\"\6\3\2\2\2#$\7"+
		"}\2\2$\b\3\2\2\2%&\7\177\2\2&\n\3\2\2\2\'(\7o\2\2()\7c\2\2)*\7k\2\2*+"+
		"\7p\2\2+\f\3\2\2\2,-\7t\2\2-.\7g\2\2./\7v\2\2/\60\7w\2\2\60\61\7t\2\2"+
		"\61\62\7p\2\2\62\16\3\2\2\2\63\64\7=\2\2\64\20\3\2\2\2\65\66\7k\2\2\66"+
		"\67\7p\2\2\678\7v\2\28\22\3\2\2\29:\7\62\2\2:>\7z\2\2;<\7\62\2\2<>\7Z"+
		"\2\2=9\3\2\2\2=;\3\2\2\2>@\3\2\2\2?A\t\2\2\2@?\3\2\2\2AB\3\2\2\2B@\3\2"+
		"\2\2BC\3\2\2\2C\24\3\2\2\2DH\7\62\2\2EG\t\3\2\2FE\3\2\2\2GJ\3\2\2\2HF"+
		"\3\2\2\2HI\3\2\2\2I\26\3\2\2\2JH\3\2\2\2KO\t\4\2\2LN\t\5\2\2ML\3\2\2\2"+
		"NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2P\30\3\2\2\2QO\3\2\2\2RT\t\6\2\2SR\3\2\2"+
		"\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\r\2\2X\32\3\2\2\2YZ\7\61"+
		"\2\2Z[\7\61\2\2[_\3\2\2\2\\^\13\2\2\2]\\\3\2\2\2^a\3\2\2\2_`\3\2\2\2_"+
		"]\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7\f\2\2cd\3\2\2\2de\b\16\2\2e\34\3\2\2"+
		"\2fg\7\61\2\2gh\7,\2\2hl\3\2\2\2ik\13\2\2\2ji\3\2\2\2kn\3\2\2\2lm\3\2"+
		"\2\2lj\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7,\2\2pq\7\61\2\2qr\3\2\2\2rs\b\17"+
		"\2\2s\36\3\2\2\2\n\2=BHOU_l\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
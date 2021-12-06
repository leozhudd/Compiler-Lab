// SysY.g4
grammar SysY;

// 词法分析
// 添加些常量（遍历时通过SysYParser.MUL获取），方便对比
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
INT: 'int';
RETURN: 'return';
NOT: '!';

// 在词法规则中那些不会被语法规则直接调用的词法规则可以用一个fragment关键字来标识，fragment标识的规则只能为其它词法规则提供基础
HEXADECIMAL_CONST: ('0x' | '0X') [0-9a-fA-F]+;
OCTAL_CONST: '0' [0-7]*;
DECIMAL_CONST: [1-9] [0-9]*;

WHITE_SPACE: [ \t\r\n]+ -> skip; // 解析时忽略空格、Tab、换行、\r
LINE_COMMENT : '//' .*? '\n' -> skip ; // 解析时忽略行注释
COMMENT : '/*' .*? '*/' -> skip ; // 解析时忽略内注释

Ident: [a-zA-Z_] [a-zA-Z0-9_]*; // 标识符（常量名变量名函数名）必须非数字开头

// 语法分析，G[compUnit]
// 【*】代表0个或多个，【+】代表一个或多个，【?】代表0个或一个
compUnit: funcDef;

decl: constDecl | varDecl; // 常量定义与变量定义
constDecl: 'const' bType constDef (',' constDef)* ';'; // 支持同时声明多个
bType: INT;
constDef: Ident '=' constInitVal;
constInitVal: constExp;
constExp: addExp;
varDecl: bType varDef (',' varDef)* ';';
varDef: Ident | Ident '=' initVal;
initVal: exp;

funcDef: funcType Ident '(' ')' block;
funcType: INT;
block: '{' (blockItem)* '}';
blockItem: decl | stmt;
stmt: lVal '=' exp ';'      // # assign
    | (exp)? ';'            // # exp_only
    | RETURN exp ';'        // # return
    | block                 // block
    | 'if' '(' cond ')' stmt ('else' stmt)? // if-else
;
lVal: Ident;

exp: addExp;
cond: lOrExp;
relExp: addExp
    | relExp op=('<'|'>'|'<='|'>=') addExp;  // 逻辑大于小于
eqExp: relExp | eqExp op=('=='|'!=') relExp; // 逻辑等于不等于
lAndExp: eqExp | lAndExp '&&' eqExp;         // 逻辑且
lOrExp: lAndExp | lOrExp '||' lAndExp;       // 逻辑或

// 引入op变量更友好地标记运算符。在Visitor的上下文（ctx）中可以，ctx.op 这样直接获取到运算符。
addExp: mulExp
    | addExp op=(ADD|SUB) mulExp;
mulExp: unaryExp
    | mulExp op=(MUL|DIV|MOD) unaryExp;
unaryExp: primaryExp
    | op=(ADD|SUB|NOT) unaryExp
    | Ident '(' (funcRParams)? ')';
funcRParams: exp (',' exp)*;
primaryExp: '(' exp ')' | lVal | number;
number: DECIMAL_CONST | OCTAL_CONST | HEXADECIMAL_CONST;


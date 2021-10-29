// SysY.g4
grammar SysY;

// 词法分析
// 添加些常量（遍历时通过SysYParser.MUL获取），方便对比
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
L_PAREN: '(';
R_PAREN: ')';
L_BRACE: '{';
R_BRACE: '}';
MAIN: 'main';
RETURN: 'return';
SEMICOLON: ';';
INT: 'int';
HEXADECIMAL_CONST: ('0x' | '0X') [0-9a-fA-F]+;
OCTAL_CONST: '0' [0-7]*;
DECIMAL_CONST: [1-9] [0-9]*;
WHITE_SPACE: [ \t\r\n]+ -> skip; // 解析时忽略空格、Tab、换行、\r
LINE_COMMENT : '//' .*? '\n' -> skip ; // 解析时忽略行注释
COMMENT : '/*' .*? '*/' -> skip ; // 解析时忽略内注释

// 语法分析，G[compUnit]
compUnit: funcDef;
funcDef: funcType ident L_PAREN R_PAREN block;
funcType: INT;
ident: MAIN;
block: L_BRACE stmt R_BRACE;
stmt: RETURN exp SEMICOLON;
exp: addExp;
// 引入op变量更友好地标记运算符。在Visitor的上下文（ctx）中可以，ctx.op 这样直接获取到运算符。
addExp: mulExp
    | addExp op=(ADD|SUB) mulExp;
mulExp: unaryExp
    | mulExp op=(MUL|DIV|MOD) unaryExp;
unaryExp: primaryExp
    | unaryOp unaryExp;
primaryExp: L_PAREN exp R_PAREN | number;
unaryOp: ADD | SUB;
number: DECIMAL_CONST | OCTAL_CONST | HEXADECIMAL_CONST;


// SysY.g4
grammar SysY;

// 词法分析
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
stmt: RETURN number SEMICOLON;
number: DECIMAL_CONST | OCTAL_CONST | HEXADECIMAL_CONST;


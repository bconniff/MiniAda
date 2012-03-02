grammar MiniAda;

@header {import trees.*;}

//
// GRAMMAR RULES
//

compilation //returns [MiniAdaAST t]
   : direc* compilation_unit+ EOF;
direc
   : ('with'|'use') lib_name (',' lib_name)* ';'
   | pragma;
lib_name
   : id ('.' id)*;
pragma
   : 'pragma' id ('(' pragma_arg (',' pragma_arg)* ')')? ';';
pragma_arg
   : (id '=>')? expr;
compilation_unit
   : pkg_decl
   | subprogram;

pkg_decl
   : 'package' (pkg_spec|pkg_body) ';';
pkg_spec
   : id 'is' spec_decl* private_part? 'end' id?;
pkg_body
   : 'body' id 'is' body_decl* stmt_part? exception_part? 'end' id?;

spec_decl
   : private_type_decl
   | object_decl
   | type_decl
   | subtype_decl
   | pragma
   | subprogram_decl
   | 'use' name_list ';'
   | id_list ':' 'exception' ';';

stmt_part
   : 'begin' stmt+;

private_type_decl
   : 'type' id 'is' 'private' ';';
private_part
   : 'private' private_item+;
private_item
   : 'subtype' id 'is' subtype_def ';'
   | 'type' id 'is' type_def ';';

body_decl
   : subprogram
   | object_decl
   | type_decl
   | subtype_decl
   | pragma
   | 'use' name_list ';'
   | id_list ':' 'exception' ';';

subprogram
   : subprogram_spec subprogram_body? ';';

subprogram_body
   : 'is' body_decl* stmt_part exception_part? 'end' id?;

object_decl
   : id_list ':' const_option type_or_subtype init_option ';';
id_list
   : id (',' id)*;
id
   : NAME;
const_option
   : 'constant'?;
type_or_subtype
   : type
   | subtype_def;
init_option
   : (':=' expr)?;

type_decl
   : 'type' id 'is' type_def ';';
type
   : id
   | type_def;
type_def
   : record_type_def
   | array_type_def
   | enum_type_def
   | 'access' id (range_constraint | index_constraint)?;
incomplete_type_decl
   : 'type' id ';';

record_type_def
   : 'record' component_list 'end' 'record';
component_list
   : component_decl component_decl* variant_part?
   | 'null' ';';
component_decl
   : id_list ':' type_or_subtype init_option ';';
variant_part
   : 'case' id ':' id 'is' variant+ 'end' 'case' ';';
variant
   : 'when' variant_choice '=>' component_list;
variant_choice
   : simple_expr;

array_type_def
   : unconstrained_array_def
   | constrained_array_def;

unconstrained_array_def
   : 'array' unconstrained_index_list 'of' element_type;
unconstrained_index_list
   : '(' index_subtype_def (',' index_subtype_def)* ')';
index_subtype_def
   : id 'range' '<>';

constrained_array_def
   : 'array' constrained_index_list 'of' element_type;
constrained_index_list
   : '(' index_range (',' index_range)* ')';
index_range
   : 'range' range;
element_type
   : type_or_subtype;

enum_type_def
   : '(' id_list ')';

subtype_decl
   : 'subtype' id 'is' subtype_def ';';

subtype_def
   : id (range_constraint | index_constraint)
   | range_constraint;

range_constraint
   : 'range' range
   | 'range' '<>';
range
   : simple_expr DOTDOT simple_expr;
index_constraint
   : '(' discrete_range (',' discrete_range)* ')';

discrete_range
   : id (range_constraint| TICK 'range')?
   | range;

subprogram_decl
   : subprogram_spec ';';
subprogram_spec
   : 'procedure' id formal_part?
   | 'function' designator formal_part;

designator
   : id
   | operator_symbol;
operator_symbol
   : STR;

formal_part
   : '(' param_decl_list ')';
param_decl_list
   : param_decl (';' param_decl)*;
param_decl
   : id_list ':' mode? type_or_subtype;
mode
   : 'in' 'out'?
   | 'out';
exception_part
   : 'exception' exception_handler*;
exception_handler
   : 'when' exception_when '=>' stmt+;
exception_when
   : name ('|' name)* '=>' stmt+
   | other;
stmt
   : pragma
   | null_stmt
   | assign_stmt
   | block
   | loop_stmt
   | if_stmt
   | exit_stmt
   | return_stmt
   | case_stmt
   | raise_stmt;
assign_stmt
   : name (':=' expr)? ';';
null_stmt
   : 'null' ';';
block
   : (id ':')? decl_part? stmt_part exception_part? 'end' id? ';';
decl_part
   : 'declare' body_decl*;
return_stmt
   : 'return' expr? ';';
raise_stmt
   : 'raise' name ';';
if_stmt
   : if_part elsif_part* else_part? 'end' 'if' ';';
if_part
   : 'if' expr 'then' stmt+;
elsif_part
   : 'elsif' expr 'then' stmt+;
else_part
   : 'else' stmt+;
loop_stmt
   : (id ':')? it_clause? 'loop' stmt+ 'end' 'loop' ';';
it_clause
   : 'while' expr
   | 'for' id 'in' 'reverse'? discrete_range;
exit_stmt
   : 'exit' name? ('when' expr)? ';';
case_stmt
   : 'case' expr 'is' ('when' when)* ('when' other) 'end' 'case' ';';
when
   : choice ('|' choice)* '=>' stmt+;
other
   : 'others' '=>' stmt+;
choice
   : expr (DOTDOT expr)?;

log_op returns [BinOp op]
   : 'and' {op=BinOp.AND;} ('then' {op=BinOp.AND_THEN;})?
   | 'or' {op=BinOp.OR;} ('else' {op=BinOp.OR_ELSE;})?;
rel_op returns [BinOp op]
   : '=' {op=BinOp.EQ;}
   | '<' {op=BinOp.LT;}
   | '>' {op=BinOp.GT;}
   | '<=' {op=BinOp.LE;}
   | '/=' {op=BinOp.NE;}
   | '>=' {op=BinOp.GE;};
add_op returns [BinOp op]
   : '+' {op=BinOp.PLUS;}
   | '-' {op=BinOp.MINUS;}
   | '&' {op=BinOp.AMP;};
un_add_op returns [UnaryOp unOp]
   : '+' {unOp=UnaryOp.PLUS;}
   | '-' {unOp=UnaryOp.MINUS;};
un_bin_op returns [UnaryOp unOp]
   : 'not' {unOp=UnaryOp.NOT;}
   | 'abs' {unOp=UnaryOp.ABS;};
mult_op returns [BinOp op]
   : '*' {op=BinOp.MULT;}
   | '/' {op=BinOp.DIV;}
   | 'mod' {op=BinOp.MOD;};
pow_op returns [BinOp op]
   : '**' {op=BinOp.POW;};

expr returns [ExprNode value]
   : v=rel {value=$v.value;} (o=log_op v=rel {value=new BinNode($o.op,value,$v.value);})*;
rel returns [ExprNode value]
   : v=simple_expr {value=$v.value;} (o=rel_op v=simple_expr {value=new BinNode($o.op,value,$v.value);})*;

simple_expr returns [ExprNode value]
   : u=un_add_op v=term {value=new UnaryNode($u.unOp, $v.value);} (o=add_op v=term {value=new BinNode($o.op,value,$v.value);})*
   | v=term {value=$v.value;} (o=add_op v=term {value=new BinNode($o.op,value,$v.value);})*;
term returns [ExprNode value]
   : v=factor {value=$v.value;} (o=mult_op v=factor {value=new BinNode($o.op,value,$v.value);})*;
factor returns [ExprNode value]
   : v=primary {value=$v.value;} (o=pow_op v=primary {value=new BinNode($o.op,value,$v.value);})?
   | u=un_bin_op v=primary {value=new UnaryNode($u.unOp,$v.value);};
primary returns [ExprNode value]
   : l=literal {value=$l.value;}
   | name agg? {value=null;} // XXX
   | '(' e=expr ')' {value=$e.value;};
literal returns [ExprNode value]
   : i=INT {value=new IntValNode($i.text);}
   | c=CHAR {value=new CharValNode($c.text);}
   | s=STR {value=new StrValNode($s.text);}
   | f=FLOAT {value=new FloatValNode($f.text);};

name
   : id name_suffix* '.all'?;
name_suffix
   : '.' selected_suffix
   | '(' expr (',' expr)* ')'
   | TICK id;
selected_suffix
   : id
   | operator_symbol;

agg
   : TICK '(' component (',' component)* ')';

component
   : expr (('..' simple_expr)? component_tail)?
   | id range_constraint component_tail
   | 'others' component_tail;

component_tail
   : ('|' agg_choice)* '=>' expr;

agg_choice
   : simple_expr ('..' simple_expr)?
   | id range_constraint
   | 'others';

name_list
   : name (',' name)*;

//
// LEXER RULES
//

TICK: {input.LA(3) != '\'' || input.LA(5) == '\''}?=> '\'';
CHAR: {input.LA(3) == '\'' && input.LA(5) != '\''}?=> '\'' . '\'';
STR: '"' ~('"' | EOL)* '"';
NAME: ALPHA ('_'? (ALPHA|DIGIT))*;
INT: INT_NUM EXP?;
DOTDOT: '..';
FLOAT
   : (INT_NUM '.' INT_NUM EXP?)=> INT_NUM '.' INT_NUM EXP? {$type=FLOAT;}
   | INT_NUM {$type=INT;};
BASE_INT: INT_NUM '#' HEX_NUM '#' EXP?;
BASE_FLOAT: INT_NUM '#' HEX_NUM '.' HEX_NUM '#' EXP?;

// these get ignored
COMMENT: '--' (options{greedy=false;}:.)* EOL {skip();};
SPACE: (' '|'\t'|'\n'|'\r')+ {skip();};

// used in the definition of tokens, but not valid alone
fragment DIGIT: '0'..'9';
fragment ALPHA: ('a'..'z' | 'A'..'Z');
fragment HEX: (DIGIT | 'a'..'f' | 'A'..'F');
fragment EOL: '\n';
fragment INT_NUM: DIGIT ('_'? DIGIT)*;
fragment HEX_NUM: HEX ('_'? HEX)*;
fragment EXP: ('e'|'E') ('+'|'-')? INT_NUM;

// vim: ft=antlr3

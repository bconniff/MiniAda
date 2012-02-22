grammar MiniAda;

//
// GRAMMAR RULES
//

compilation: direc* compilation_unit+ EOF;
direc: ('with'|'use') lib_name (',' lib_name)* ';'
     | pragma;
lib_name: id ('.' id)*;
pragma: 'pragma' id ('(' pragma_arg (',' pragma_arg)* ')')? ';';
pragma_arg: (id '=>')? expr;
compilation_unit: pkg_decl | subprogram;

pkg_decl: 'package' (pkg_spec|pkg_body) ';';
pkg_spec: id 'is' spec_decl* private_part? 'end' id?;
pkg_body: 'body' id 'is' body_decl* stmt_part? exception_part? 'end' id?;

spec_decl: private_type_decl
         | object_decl
         | type_decl
         | subtype_decl
         | pragma
         | subprogram_decl
         | 'use' name_list ';'
         | id_list ':' 'exception' ';';

stmt_part: 'begin' stmt+;

private_type_decl: 'type' id 'is' 'private' ';';
private_part: 'private' private_item+;
private_item: 'subtype' id 'is' subtype_def ';'
            | 'type' id 'is' type_def ';';

body_decl: subprogram
         | object_decl
         | type_decl
         | subtype_decl
         | pragma
         | 'use' name_list ';'
         | id_list ':' 'exception' ';';

subprogram: subprogram_spec subprogram_body? ';';

subprogram_body: 'is' body_decl* stmt_part exception_part? 'end' id?;

object_decl: id_list ':' const_option type_or_subtype init_option ';';
id_list: id (',' id)*;
id: NAME;
const_option: 'constant'?;
type_or_subtype: type
               | subtype_def;
init_option: (':=' expr)?;

type_decl: 'type' id 'is' type_def ';';
type: id
    | type_def;
type_def: record_type_def
        | array_type_def
        | enum_type_def
        | 'access' id (range_constraint | index_constraint)?;
incomplete_type_decl: 'type' id ';';

record_type_def: 'record' component_list 'end' 'record';
component_list: component_decl component_decl* variant_part
              | 'null' ';';
component_decl: id_list ':' type_or_subtype init_option ';';
variant_part: 'case' id ':' id 'is' variant+ 'end' 'case' ';';
variant: 'when' variant_choice '=>' component_list;
variant_choice: simple_expr;

array_type_def: unconstrained_array_def
              | constrained_array_def;

unconstrained_array_def: 'array' unconstrained_index_list 'of' element_type;
unconstrained_index_list: '(' index_subtype_def (',' index_subtype_def)* ')';
index_subtype_def: id 'range' '<>';

constrained_array_def: 'array' constrained_index_list 'of' element_type;
constrained_index_list: '(' index_range (',' index_range)* ')';
index_range: 'range' range;
element_type: type_or_subtype;

enum_type_def: '(' id_list ')';

subtype_decl: 'subtype' id 'is' subtype_def ';';

subtype_def: id (range_constraint | index_constraint)
           | range_constraint;

range_constraint: 'range' range
                | 'range' '<>';
range: simple_expr '..' simple_expr;
index_constraint: '(' discrete_range (',' discrete_range)* ')';

discrete_range: id (range_constraint| TICK 'range')?
              | range;

subprogram_decl: subprogram_spec ';';
subprogram_spec: 'procedure' id formal_part?
               | 'function' designator formal_part;

designator: id
          | operator_symbol;
operator_symbol: STR;

formal_part: '(' param_decl_list ')';
param_decl_list: param_decl (';' param_decl)*;
param_decl: id_list ':' mode? type_or_subtype;
mode: 'in' 'out'?
    | 'out';
exception_part: 'exception' exception_handler*;
exception_handler: 'when' exception_when '=>' stmt+;
exception_when: name ('|' name)* '=>' stmt+
              | other;
stmt: pragma
    | null_stmt
    | assign_stmt
    | block
    | loop_stmt
    | if_stmt
    | exit_stmt
    | return_stmt
    | case_stmt
    | raise_stmt;
assign_stmt: name (':=' expr)? ';';
null_stmt: 'null' ';';
block: (id ':')? decl_part? stmt_part exception_part? 'end' id? ';';
decl_part: 'declare' body_decl*;
return_stmt: 'return' expr? ';';
raise_stmt: 'raise' name ';';
if_stmt: if_part elsif_part* else_part? 'end' 'if' ';';
if_part: 'if' expr 'then' stmt+;
elsif_part: 'elsif' expr 'then' stmt+;
else_part: 'else' stmt+;
loop_stmt: (id ':')? it_clause? 'loop' stmt+ 'end' 'loop' ';';
it_clause: 'while' expr
         | 'for' id 'in' 'reverse'? discrete_range;
exit_stmt: 'exit' name? ('when' expr)? ';';
case_stmt: 'case' expr 'is' ('when' when)* ('when' other) 'end' 'case' ';';
when: choice ('|' choice)* '=>' stmt+;
other: 'others' '=>' stmt+;
choice: expr ('..' expr)?;

log_op: 'and'
      | 'or';
rel_op: '='
      | '/='
      | '<'
      | '<='
      | '>'
      | '>=';
add_op: '+'
      | '-'
      | '&';
un_add_op: '+'
         | '-';
mult_op: '*'
       | '/'
       | 'mod';

expr_suffix: log_op rel
           | 'and' 'then' rel
           | 'or' 'else' rel;

expr: rel expr_suffix*;
rel: simple_expr (rel_op simple_expr)*;

simple_expr: un_add_op? term (add_op term)*;
term: factor (mult_op factor)*;
factor: primary ('**' primary)?
      | 'not' primary
      | 'abs' primary;
primary: literal
       | name agg?
       | '(' expr ')';
literal: INT
       | CHAR
       | STR
       | FLOAT;

name: id name_suffix* '.all'?;
name_suffix: '.' selected_suffix
           | '(' expr (',' expr)* ')'
           | TICK id;
selected_suffix: id
               | operator_symbol;

agg: TICK '(' component (',' component)* ')';

component: expr (('..' simple_expr)? component_tail)?
         | id range_constraint component_tail
         | 'others' component_tail;

component_tail: ('|' agg_choice)* '=>' expr;

agg_choice: simple_expr ('..' simple_expr)?
          | id range_constraint
          | 'others';

name_list: name (',' name)*;

//
// LEXER RULES
//

TICK: {input.LA(3) != '\''}?=> '\'';
CHAR: {input.LA(3) == '\''}?=> '\'' . '\'';
STR: '"' ~('"' | EOL)* '"';
NAME: ALPHA ('_'? (ALPHA|DIGIT))*;
INT: INT_NUM EXP?;
FLOAT: INT_NUM '.' INT_NUM EXP?;
BASE_INT: INT_NUM '#' HEX_NUM '#' EXP?;
BASE_FLOAT: INT_NUM '#' HEX_NUM '.' HEX_NUM '#' EXP?;

// these get ignored
COMMENT: '-' '-' (options{greedy=false;}:.)* EOL {skip();};
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

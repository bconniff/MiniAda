grammar MiniAda;

@header {
   import trees.*;
   import java.util.List;
   import java.util.ArrayList;
}

//
// GRAMMAR RULES
//

compilation returns [MiniAdaTree tree]
@init {tree=new MiniAdaTree();}
   : (d=direc {tree.addDirec($d.value);})* (c=compilation_unit {tree.addUnit($c.value);})+ EOF;

direc returns [DirecNode value]
   : 'with' {WithNode v=new WithNode();} l=lib_name {v.addLib($l.value);} (',' l=lib_name {v.addLib($l.value);})* ';' {value=v;}
   | 'use' {UseNode v=new UseNode();} l=lib_name {v.addLib($l.value);} (',' l=lib_name {v.addLib($l.value);})* ';' {value=v;}
   | p=pragma {value=$p.value;};
pragma returns [PragmaNode value]
   : 'pragma' i=id {value=new PragmaNode($i.text);} ('(' p=pragma_arg {value.addArg($p.value);} (',' p=pragma_arg {value.addArg($p.value);})* ')')? ';';
pragma_arg returns [PragmaArgNode value]
   : i=id '=>' e=expr {value=new PragmaArgNode($i.text,$e.value);}
   | e=expr {value=new PragmaArgNode($e.value);};

lib_name returns [List<String> value]
@init {value = new ArrayList<String>();}
   : i=id {value.add($i.text);} ('.' i=id {value.add($i.text);})*;

compilation_unit returns [CompilationNode value]
   : 'package' p=pkg_spec ';' {value=$p.spec;}
   | 'package' 'body' b=pkg_body ';' {value=$b.value;}
   | s=subprogram {value=$s.value;};
pkg_spec returns [PkgSpecNode spec]
   : i=id {spec=new PkgSpecNode($i.text);} 'is' (s=spec_decl {spec.addDecl($s.value);})* ('private' (p=private_item {spec.addItem($p.item);})+)? 'end' id?;
pkg_body returns [PkgBodyNode value]
   : i=id {value=new PkgBodyNode($i.text);} 'is' (d=body_decl {value.addDecl($d.value);})* (s=stmt_part {value.setStmts($s.sts);})? (e=exception_part {value.setExceps($e.exs);})? 'end' id?;

private_type_decl returns [PrivateTypeDeclNode value]
   : 'type' i=id 'is' 'private' ';' {value=new PrivateTypeDeclNode($i.text);};
private_item returns [PrivateItemNode item]
   : 'subtype' i=id 'is' s=subtype_def ';' {item=new PrivateItemNode($i.text,$s.type);}
   | 'type' i=id 'is' t=type_def ';' {item=new PrivateItemNode($i.text,$t.type);};

spec_decl returns [DeclNode value]
   : p=private_type_decl {value=$p.value;}
   | o=object_decl {value=$o.value;}
   | t=type_decl {value=$t.value;}
   | s=subtype_decl {value=$s.value;}
   | r=pragma {value=$r.value;}
   | f=subprogram_decl {value=$f.value;}
   | 'use' n=name_list ';' {value=new UseNode($n.names);}
   | i=id_list ':' 'exception' ';' {value=new ExceptionDeclNode($i.ids);};

stmt_part returns [List<StmtNode> sts]
@init {sts=new ArrayList<StmtNode>();}
   : 'begin' (s=stmt {sts.add($s.st);})+;

body_decl returns [DeclNode value]
   : f=subprogram {value=$f.value;}
   | o=object_decl {value=$o.value;}
   | t=type_decl {value=$t.value;}
   | s=subtype_decl {value=$s.value;}
   | p=pragma {value=$p.value;}
   | 'use' n=name_list ';' {value=new UseNode($n.names);}
   | i=id_list ':' 'exception' ';' {value=new ExceptionDeclNode($i.ids);};

subprogram returns [SubDeclNode value]
   : s=subprogram_spec {value=new SubDeclNode($s.spec);} (b=subprogram_body {value.setBody($b.value);})? ';';

subprogram_body returns [SubBodyNode value]
@init {value=new SubBodyNode();}
   : 'is' (d=body_decl {value.addDecl($d.value);})* s=stmt_part {value.setStmts($s.sts);} (e=exception_part {value.setExceps($e.exs);})? 'end' id?;

object_decl returns [ObjDeclNode value]
@init {value=new ObjDeclNode();}
   : i=id_list ':' ('constant' {value.setConst(true);})? t=type_or_subtype {value.setDecl($i.ids, $t.type);} (':=' e=expr {value.setInit($e.value);})? ';';
id_list returns [List<String> ids]
@init {ids=new ArrayList<String>();}
   : i=id {ids.add($i.text);} (',' i=id {ids.add($i.text);})*;
id: NAME;

type_or_subtype returns [TypeNode type]
   : t=type {type=$t.type;}
   | s=subtype_def {type=$s.type;};

type_decl returns [TypeDeclNode value]
   : 'type' i=id 'is' t=type_def ';' {value=new TypeDeclNode($i.text,$t.type);};
type returns [TypeNode type]
   : i=id {type=new IdTypeNode($i.text);}
   | t=type_def {type=$t.type;};
type_def returns [TypeNode type]
   : r=record_type_def {type=$r.type;}
   | a=array_type_def {type=$a.type;}
   | e=enum_type_def {type=$e.type;}
   | x=access_type_def {type=$x.type;};
incomplete_type_decl returns [TypeNode type]
   : 'type' i=id ';' {type=new IncompleteTypeNode($i.text);};

access_type_def returns [AccessTypeNode type]
   : 'access' i=id {type=new AccessTypeNode($i.text);}
      ( r=range_constraint {type.setConstraint($r.con);}
      | c=index_constraint {type.setConstraint($c.ranges);} )?;

record_type_def returns [RecordTypeNode type]
   : 'record' c=component_list {type=new RecordTypeNode($c.comps);} 'end' 'record';
component_list returns [ComponentListNode comps]
@init {comps=new ComponentListNode();}
   : (c=component_decl {comps.add($c.comp);})+ (v=variant_part {comps.setVariant($v.value);})?
   | 'null' ';';
component_decl returns [ComponentNode comp]
   : i=id_list ':' t=type_or_subtype {comp=new ComponentNode($i.ids,$t.type);} (':=' e=expr {comp.setInit($e.value);})? ';';
variant_part returns [VariantNode value]
@init {List<VariantChoiceNode> vars=new ArrayList<VariantChoiceNode>();}
   : 'case' i=id 'is' (v=variant {vars.add($v.value);})+ 'end' 'case' ';' {value=new VariantNode($i.text, vars);};
variant returns [VariantChoiceNode value]
   : 'when' e=simple_expr '=>' c=component_list {value=new VariantChoiceNode($e.value,$c.comps);};

enum_type_def returns [EnumTypeNode type]
   : '(' i=id_list ')' {type=new EnumTypeNode($i.ids);};

array_type_def returns [ArrayTypeNode type]
   : u=unconstrained_array_def {type=$u.type;}
   | c=constrained_array_def {type=$c.type;};
unconstrained_array_def returns [UnconstrainedArrayTypeNode type]
   : 'array' u=unconstrained_index_list 'of' e=element_type {type=new UnconstrainedArrayTypeNode($u.types, $e.type);};
unconstrained_index_list returns [List<SubtypeNode> types]
@init {types=new ArrayList<SubtypeNode>();}
   : '(' i=index_subtype_def {types.add($i.type);} (',' i=index_subtype_def {types.add($i.type);})* ')';
index_subtype_def returns [SubtypeNode type]
   : i=id 'range' '<>' {type=new SubtypeNode($i.text,new RangeConstraintNode());};
constrained_array_def returns [ConstrainedArrayTypeNode type]
   : 'array' c=constrained_index_list 'of' e=element_type {type=new ConstrainedArrayTypeNode($c.ranges, $e.type);};
constrained_index_list returns [List<RangeNode> ranges]
   : '(' r=index_range {ranges.add($r.range);} (',' r=index_range {ranges.add($r.range);})* ')';
index_range returns [RangeNode range]
   : 'range' r=range {range=$r.value;};
element_type returns [TypeNode type]
   : t=type_or_subtype {type=$t.type;};

subtype_decl returns [SubtypeDeclNode value]
   : 'subtype' i=id 'is' s=subtype_def ';' {value=new SubtypeDeclNode($i.text,$s.type);};

exception_part returns [List<ExceptionHandlerNode> exs]
@init {exs=new ArrayList<ExceptionHandlerNode>();}
   : 'exception' (e=exception_handler {exs.add($e.ex);})+;
exception_handler returns [ExceptionHandlerNode ex]
   : 'when' e=exception_when {ex=new ExceptionHandlerNode($e.value);} '=>' (s=stmt {ex.addStmt($s.st);})+;
exception_when returns [WhenNode value]
   : n=name {value.addChoice(new ChoiceNode($n.name));} ('|' n=name {value.addChoice(new ChoiceNode($n.name));})* '=>' (s=stmt {value.addStmt($s.st);})+
   | o=other {value=$o.value;};

decl_part returns [List<DeclNode> decls]
@init {decls=new ArrayList<DeclNode>();}
   : 'declare' (d=body_decl {decls.add($d.value);})+;

subtype_def returns [SubtypeNode type]
   : i=id c=range_constraint {type=new SubtypeNode($i.text,$c.con);}
   | i=id r=index_constraint {type=new SubtypeNode($i.text,$r.ranges);}
   | c=range_constraint {type=new SubtypeNode($c.con);};
range_constraint returns [RangeConstraintNode con]
   : 'range' r=range {con=new RangeConstraintNode($r.value);}
   | 'range' '<>' {con=new RangeConstraintNode();};
index_constraint returns [List<RangeNode> ranges]
@init {ranges=new ArrayList<RangeNode>();}
   : '(' r=discrete_range {ranges.add($r.value);} (',' r=discrete_range {ranges.add($r.value);})* ')';
range returns [RangeNode value]
   : a=simple_expr DOTDOT b=simple_expr {value=new RangeNode($a.value,$b.value);};

discrete_range returns [RangeNode value]
   : i=id c=range_constraint {value=new RangeNode($i.text,$c.con);}
   | i=id TICK 'range' {value=new RangeNode(new AttrNode($i.text,"range"));}
   | i=id {value=new RangeNode($i.text);}
   | r=range {value=$r.value;};

subprogram_decl returns [SubDeclNode value]
   : s=subprogram_spec {value=new SubDeclNode($s.spec);} ';';
subprogram_spec returns [SubSpecNode spec]
   : 'procedure' i=id p=formal_part {spec=new ProcNode($i.text,$p.params);}
   | 'function' d=designator p=formal_part 'return' i=id {spec=new FuncNode($d.text,$p.params,$i.text);};

designator: NAME | STR;

formal_part returns [List<ParamNode> params]
@init {params=new ArrayList<ParamNode>();}
   : ('(' p=param_decl {params.add($p.param);} (';' p=param_decl {params.add($p.param);})* ')')?;
param_decl returns [ParamNode param]
   : i=id_list ':' m=mode t=type_or_subtype {param=new ParamNode($i.ids,$m.mode,$t.type);};
mode returns [ParamNode.Mode mode]
   : 'in' 'out' {mode=ParamNode.Mode.IN_OUT;}
   | 'in' {mode=ParamNode.Mode.IN;}
   | 'out' {mode=ParamNode.Mode.OUT;}
   | {mode=ParamNode.Mode.IN;};
stmt returns [StmtNode st]
   : p=pragma {st=$p.value;}
   | n=null_stmt {st=$n.st;}
   | a=assign_stmt {st=$a.st;}
   | b=block {st=$b.st;}
   | l=loop_stmt {st=$l.st;}
   | i=if_stmt {st=$i.st;}
   | e=exit_stmt {st=$e.st;}
   | r=return_stmt {st=$r.st;}
   | c=case_stmt {st=$c.st;}
   | x=raise_stmt {st=$x.st;};
assign_stmt returns [StmtNode st]
   : n=name {st=new CallStmtNode($n.name);} (':=' e=expr {st=new AssignStmtNode($n.name,$e.value);})? ';';
null_stmt returns [NullStmtNode st]
   : 'null' ';' {st=new NullStmtNode();};
block returns [BlockStmtNode st]
@init {st=new BlockStmtNode();}
   : (i=id ':' {st.setName($i.text);})? (d=decl_part {st.setDecls($d.decls);})? s=stmt_part {st.setStmts($s.sts);} (e=exception_part {st.setExceps($e.exs);})? 'end' id? ';';
return_stmt returns [ReturnStmtNode st]
   : 'return' {st=new ReturnStmtNode();} (e=expr {st=new ReturnStmtNode($e.value);})? ';';
raise_stmt returns [RaiseStmtNode st]
   : 'raise' n=name {st=new RaiseStmtNode($n.name);} ';';
if_stmt returns [IfStmtNode st]
   : a=if_part {st=new IfStmtNode($a.value);} (b=elsif_part {st.addElsif($b.value);})* (c=else_part {st.addElse($c.value);})? 'end' 'if' ';';
if_part returns [IfClauseNode value]
@init {value=new IfClauseNode();}
   : 'if' e=expr {value.setExpr($e.value);} 'then' (s=stmt {value.addStmt($s.st);})+;
elsif_part returns [IfClauseNode value]
@init {value=new IfClauseNode();}
   : 'elsif' e=expr {value.setExpr($e.value);} 'then' (s=stmt {value.addStmt($s.st);})+;
else_part returns [IfClauseNode value]
@init {value=new IfClauseNode();}
   : 'else' (s=stmt {value.addStmt($s.st);})+;
loop_stmt returns [LoopStmtNode st]
@init {st=new LoopStmtNode();}
   : (i=id ':' {st.setName($i.text);})? (t=it_clause {st.setClause($t.value);})? 'loop' (s=stmt {st.addStmt($s.st);})+ 'end' 'loop' ';';
it_clause returns [LoopClauseNode value]
   : 'while' e=expr {value=new WhileClauseNode($e.value);}
   | {boolean reverse=false;} 'for' i=id 'in' ('reverse' {reverse=true;})? d=discrete_range {value=new ForClauseNode($i.text, $d.value, reverse);};
exit_stmt returns [ExitStmtNode st]
@init {st=new ExitStmtNode();}
   : 'exit' (i=id {st.setName($i.text);})? ('when' e=expr {st.setWhen($e.value);})? ';';
case_stmt returns [CaseStmtNode st]
   : 'case' e=expr {st=new CaseStmtNode($e.value);} 'is' ('when' w=when {st.addWhen($w.value);})* ('when' w=other {st.addWhen($w.value);}) 'end' 'case' ';';
when returns [WhenNode value]
@init {value=new WhenNode();}
   : c=choice {value.addChoice($c.value);} ('|' c=choice {value.addChoice($c.value);})* '=>' (s=stmt {value.addStmt($s.st);})+;
other returns [WhenNode value]
@init {value=new WhenNode();}
   : 'others' '=>' (s=stmt {value.addStmt($s.st);})+;
choice returns [ChoiceNode value]
@init {value=new ChoiceNode();}
   : a=expr {value.setVal($a.value);} (DOTDOT b=expr {value.setRange($a.value,$b.value);})?;

log_op returns [BinNode.Op op]
   : 'and' {op=BinNode.Op.AND;} ('then' {op=BinNode.Op.AND_THEN;})?
   | 'or' {op=BinNode.Op.OR;} ('else' {op=BinNode.Op.OR_ELSE;})?;
rel_op returns [BinNode.Op op]
   : '=' {op=BinNode.Op.EQ;}
   | '<' {op=BinNode.Op.LT;}
   | '>' {op=BinNode.Op.GT;}
   | '<=' {op=BinNode.Op.LE;}
   | '/=' {op=BinNode.Op.NE;}
   | '>=' {op=BinNode.Op.GE;};
add_op returns [BinNode.Op op]
   : '+' {op=BinNode.Op.PLUS;}
   | '-' {op=BinNode.Op.MINUS;}
   | '&' {op=BinNode.Op.AMP;};
un_add_op returns [UnaryNode.Op unOp]
   : '+' {unOp=UnaryNode.Op.PLUS;}
   | '-' {unOp=UnaryNode.Op.MINUS;};
un_bin_op returns [UnaryNode.Op unOp]
   : 'not' {unOp=UnaryNode.Op.NOT;}
   | 'abs' {unOp=UnaryNode.Op.ABS;};
mult_op returns [BinNode.Op op]
   : '*' {op=BinNode.Op.MULT;}
   | '/' {op=BinNode.Op.DIV;}
   | 'mod' {op=BinNode.Op.MOD;};
pow_op returns [BinNode.Op op]
   : '**' {op=BinNode.Op.POW;};

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
   | n=name {value=$n.name;} (a=agg {value=new AggNode(value,$a.value);})?
   | '(' e=expr ')' {value=$e.value;};

literal returns [ValNode value]
   : i=INT {value=new IntValNode($i.text);}
   | c=CHAR {value=new CharValNode($c.text);}
   | s=STR {value=new StrValNode($s.text);}
   | f=FLOAT {value=new FloatValNode($f.text);};

name returns [NameNode name]
   : i=id {name=new NameNode($i.text);} (s=name_suffix {name.addSuffix($s.suff);})* ('.' 'all' {name.addSuffix(new AllSuffixNode());})?;
name_suffix returns [SuffixNode suff]
   : '.' s=designator {suff=new DotSuffixNode($s.text);}
   | '(' {ParenSuffixNode p=new ParenSuffixNode();} e=expr {p.add($e.value);} (',' e=expr {p.add($e.value);})* ')' {suff=p;}
   | TICK i=id {suff=new AttrSuffixNode($i.text);};

agg returns [List<ComponentNode> value]
@init {value=new ArrayList<ComponentNode>();}
   : TICK '(' c=component {value.add($c.value);} (',' c=component {value.add($c.value);})* ')';

component returns [ComponentNode value]
@init {List<ComponentChoiceNode> l=new ArrayList<ComponentChoiceNode>();}
   : a=expr {value=new ComponentNode($a.value);} 
      ( DOTDOT
        b=simple_expr {l.add(new ComponentChoiceNode(new RangeNode($a.value,$b.value)));}
        t=component_tail {l.addAll($t.value);}
        '=>' e=expr {value=new ComponentNode(l,$e.value);} )?
   | c=component_head {l.add($c.value);} t=component_tail {l.addAll($t.value);} '=>' e=expr {value=new ComponentNode(l,$e.value);};

component_head returns [ComponentChoiceNode value]
   : i=id r=range_constraint {value=new ComponentChoiceNode(new SubtypeNode($i.text,$r.con));}
   | 'others' {value=new ComponentChoiceNode();};

component_tail returns [List<ComponentChoiceNode> value]
@init {value=new ArrayList<ComponentChoiceNode>();}
   : ('|' a=agg_choice {value.add($a.value);})*;

agg_choice returns [ComponentChoiceNode value]
   : a=simple_expr {value=new ComponentChoiceNode($a.value);} (DOTDOT b=simple_expr {value=new ComponentChoiceNode(new RangeNode($a.value,$b.value));})?
   | i=id r=range_constraint {value=new ComponentChoiceNode(new SubtypeNode($i.text,$r.con));}
   | 'others' {value=new ComponentChoiceNode();};

name_list returns [List<NameNode> names]
@init {names=new ArrayList<NameNode>();}
   : n=name {names.add($n.name);} (',' n=name {names.add($n.name);})*;

//
// LEXER RULES
//

TICK: {input.LA(3) != '\'' || input.LA(5) == '\''}?=> '\'';
CHAR: {input.LA(3) == '\'' && input.LA(5) != '\''}?=> '\'' . '\'';
STR: '"' ~('"' | EOL)* '"';
NAME: ALPHA ('_'? (ALPHA|DIGIT))*;
INT: INT_NUM EXP?;
DOTDOT: '..';
FLOAT: (INT_NUM '.' INT_NUM EXP?)=> INT_NUM '.' INT_NUM EXP? {$type=FLOAT;}
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

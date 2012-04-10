grammar MiniAda;

@header {
   import trees.*;
   import java.util.List;
   import java.util.ArrayList;
}

@lexer::header {
   import static utils.BaseConv.*;
}

//
// GRAMMAR RULES
//

compilation returns [MiniAdaTree tree]
   : d=direc_list c=compilation_unit EOF {tree=new MiniAdaTree($d.value, $c.value);};

direc_list returns [List<DirecNode> value]
@init {value=new ArrayList<DirecNode>();}
   : (d=direc {value.add($d.value);})*;

lib_suffix_list returns [List<SuffixNode> suffs]
@init {suffs=new ArrayList<SuffixNode>();}
   : ('.' i=id {suffs.add(new DotSuffixNode($i.value));})+;

lib_name returns [NameNode value]
   : i=id s=lib_suffix_list {value=new NameNode($i.value, $s.suffs);}
   | i=id {value=new NameNode($i.value);};
lib_list returns [List<NameNode> value]
@init {value=new ArrayList<NameNode>();}
   : l=lib_name {value.add($l.value);} (',' l=lib_name {value.add($l.value);})*;

direc returns [DirecNode value]
   : 'with' l=lib_list ';' {value=new WithNode($l.value);}
   | 'use' l=lib_list ';' {value=new UseNode($l.value);}
   | p=pragma {value=$p.value;};
pragma returns [PragmaNode value]
   : 'pragma' i=id a=arg_list ';' {value=new PragmaNode($i.value,$a.value);}
   | 'pragma' i=id ';' {value=new PragmaNode($i.value);};
arg returns [ArgNode value]
   : i=id '=>' e=expr {value=new ArgNode($i.value,$e.value);}
   | e=expr {value=new ArgNode($e.value);};
arg_list returns [List<ArgNode> value]
@init {value=new ArrayList<ArgNode>();}
   : '(' (a=arg {value.add($a.value);} (',' a=arg {value.add($a.value);})*)? ')';
arg_list_opt returns [List<ArgNode> value]
@init {value=new ArrayList<ArgNode>();}
   : ('(' (a=arg {value.add($a.value);} (',' a=arg {value.add($a.value);})*)? ')')?;

compilation_unit returns [CompilationNode value]
   : 'package' p=pkg_spec ';' {value=$p.spec;}
   | 'package' 'body' b=pkg_body ';' {value=$b.value;}
   | s=subprogram {value=$s.value;};
pkg_spec returns [PkgSpecNode spec]
   : i=id 'is' s=spec_decl_list p=private_item_list_opt 'end' id? {spec=new PkgSpecNode($i.value,$s.decls,$p.privs);};
pkg_body returns [PkgBodyNode value]
   : i=id 'is' b=body_decl_list s=stmt_part_opt e=exception_part_opt 'end' id? {value=new PkgBodyNode($i.value,$b.decls,$s.sts,$e.exs);};

private_item_list_opt returns [List<PrivateItemNode> privs]
@init {privs=new ArrayList<PrivateItemNode>();}
   : ('private' (p=private_item {privs.add($p.item);})+)?;

spec_decl_list returns [List<DeclNode> decls]
@init {decls=new ArrayList<DeclNode>();}
   : (s=spec_decl {decls.add($s.value);})*;
body_decl_list returns [List<DeclNode> decls]
@init {decls=new ArrayList<DeclNode>();}
   : (b=body_decl {decls.add($b.value);})*;

private_type_decl returns [PrivateTypeDeclNode value]
   : 'type' i=id 'is' 'private' ';' {value=new PrivateTypeDeclNode($i.value);};
private_item returns [PrivateItemNode item]
   : 'subtype' i=id 'is' s=subtype_def ';' {item=new PrivateItemNode($i.value,$s.type);}
   | 'type' i=id 'is' t=type_def ';' {item=new PrivateItemNode($i.value,$t.type);};

spec_decl returns [DeclNode value]
   : p=private_type_decl {value=$p.value;}
   | o=object_decl {value=$o.value;}
   | t=type_decl {value=$t.value;}
   | s=subtype_decl {value=$s.value;}
   | r=pragma {value=$r.value;}
   | f=subprogram_decl {value=$f.value;}
   | 'use' l=lib_list ';' {value=new UseNode($l.value);}
   | i=id_list ':' 'exception' ';' {value=new ExceptionDeclNode($i.ids);};

stmt_list returns [List<StmtNode> sts]
@init {sts=new ArrayList<StmtNode>();}
   : (s=stmt {sts.add($s.st);})+;

stmt_part_opt returns [List<StmtNode> sts]
@init {sts=new ArrayList<StmtNode>();}
   : ('begin' (s=stmt {sts.add($s.st);})+)?;

stmt_part returns [List<StmtNode> sts]
@init {sts=new ArrayList<StmtNode>();}
   : 'begin' (s=stmt {sts.add($s.st);})+;

body_decl returns [DeclNode value]
   : f=subprogram {value=$f.value;}
   | o=object_decl {value=$o.value;}
   | t=type_decl {value=$t.value;}
   | s=subtype_decl {value=$s.value;}
   | p=pragma {value=$p.value;}
   | 'use' l=lib_list ';' {value=new UseNode($l.value);}
   | i=id_list ':' 'exception' ';' {value=new ExceptionDeclNode($i.ids);};

subprogram returns [SubDeclNode value]
   : {boolean i=false;} s=subprogram_spec (b=subprogram_body {i=true;})? ';'
      {value=(i?new SubDeclNode($s.spec,$b.value):new SubDeclNode($s.spec));};

subprogram_body returns [SubBodyNode value]
   : 'is' d=body_decl_list s=stmt_part e=exception_part_opt 'end' id? {value=new SubBodyNode($d.decls,$s.sts,$e.exs);};

object_decl returns [ObjDeclNode value]
   : {boolean con=false, i=false;} l=id_list ':' ('constant' {con=true;})? t=type_or_subtype (':=' e=expr {i=true;})? ';'
      {value=(i?new ObjDeclNode(con,$l.ids,$t.type,$e.value):new ObjDeclNode(con,$l.ids,$t.type));};
id_list returns [List<IdNode> ids]
@init {ids=new ArrayList<IdNode>();}
   : i=id {ids.add($i.value);} (',' i=id {ids.add($i.value);})*;
id returns [IdNode value]
   : NAME {value = new IdNode($NAME.text);};

type_or_subtype returns [TypeNode type]
   : t=type {type=$t.type;}
   | s=subtype_def {type=$s.type;};

type_decl returns [TypeDeclNode value]
   : 'type' i=id 'is' t=type_def ';' {value=new TypeDeclNode($i.value,$t.type);};
type returns [TypeNode type]
   : i=id {type=new IdTypeNode($i.value);}
   | t=type_def {type=$t.type;};
type_def returns [TypeNode type]
   : r=record_type_def {type=$r.type;}
   | a=array_type_def {type=$a.type;}
   | e=enum_type_def {type=$e.type;}
   | x=access_type_def {type=$x.type;};
incomplete_type_decl returns [TypeNode type]
   : 'type' i=id ';' {type=new IncompleteTypeNode($i.value);};

access_type_def returns [AccessTypeNode type]
   : 'access' i=id r=range_constraint {type=new AccessTypeNode($i.value, $r.con);}
   | 'access' i=id c=index_constraint {type=new AccessTypeNode($i.value, $c.ranges);}
   | 'access' i=id {type=new AccessTypeNode($i.value);};

record_type_def returns [RecordTypeNode type]
   : 'record' c=component_list 'end' 'record' {type=new RecordTypeNode($c.comps);};

component_list returns [List<RecordItemNode> comps]
@init {comps=new ArrayList<RecordItemNode>();}
   : 'null' ';'
   |  (c=component_decl {comps.add($c.comp);})+ (v=variant_part {comps.add($v.value);})?;

component_decl returns [RecordComponentNode comp]
   : {boolean i=false;} l=id_list ':' t=type_or_subtype (':=' e=expr {i=true;})? ';'
      {comp=(i?new RecordComponentNode($l.ids,$t.type,$e.value):new RecordComponentNode($l.ids,$t.type));};

variant_part returns [VariantNode value]
@init {List<VariantChoiceNode> vars=new ArrayList<VariantChoiceNode>();}
   : 'case' i=id 'is' (v=variant {vars.add($v.value);})+ 'end' 'case' ';' {value=new VariantNode($i.value, vars);};
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
   : i=id 'range' '<>' {type=new SubtypeNode($i.value,new RangeConstraintNode());};
constrained_array_def returns [ConstrainedArrayTypeNode type]
   : 'array' c=constrained_index_list 'of' e=element_type {type=new ConstrainedArrayTypeNode($c.ranges, $e.type);};
constrained_index_list returns [List<RangeNode> ranges]
   : '(' r=index_range {ranges.add($r.range);} (',' r=index_range {ranges.add($r.range);})* ')';
index_range returns [RangeNode range]
   : 'range' r=range {range=$r.value;};
element_type returns [TypeNode type]
   : t=type_or_subtype {type=$t.type;};

subtype_decl returns [SubtypeDeclNode value]
   : 'subtype' i=id 'is' s=subtype_def ';' {value=new SubtypeDeclNode($i.value,$s.type);};

exception_part_opt returns [List<ExceptionHandlerNode> exs]
@init {exs=new ArrayList<ExceptionHandlerNode>();}
   : ('exception' (e=exception_handler {exs.add($e.ex);})+)?;
exception_handler returns [ExceptionHandlerNode ex]
   : 'when' e=exception_when '=>' s=stmt_list {ex=new ExceptionHandlerNode($e.value,$s.sts);};
exception_when returns [WhenNode value]
   : n=name_choice_list '=>' s=stmt_list {value=new WhenNode($n.value,$s.sts);}
   | o=other {value=$o.value;};

name_choice_list returns [List<ChoiceNode> value]
@init {value=new ArrayList<ChoiceNode>();}
   : n=name {value.add(new ChoiceNode($n.name));} ('|' n=name {value.add(new ChoiceNode($n.name));})*;

decl_part_opt returns [List<DeclNode> decls]
@init {decls=new ArrayList<DeclNode>();}
   : ('declare' (d=body_decl {decls.add($d.value);})+)?;

subtype_def returns [SubtypeNode type]
   : i=id c=range_constraint {type=new SubtypeNode($i.value,$c.con);}
   | i=id r=index_constraint {type=new SubtypeNode($i.value,$r.ranges);}
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
   : i=id c=range_constraint {value=new RangeNode($i.value,$c.con);}
   | i=id TICK 'range' {List<SuffixNode> s=new ArrayList<SuffixNode>(); s.add(new AttrSuffixNode(new IdNode("range"))); value=new RangeNode(new NameNode($i.value,s));}
   | i=id {value=new RangeNode($i.value);}
   | r=range {value=$r.value;};

subprogram_decl returns [SubDeclNode value]
   : s=subprogram_spec ';' {value=new SubDeclNode($s.spec);};
subprogram_spec returns [SubSpecNode spec]
   : 'procedure' i=id p=formal_part {spec=new ProcNode($i.value,$p.params);}
   | 'function' d=designator p=formal_part 'return' i=id {spec=new FuncNode($d.value,$p.params,new IdTypeNode($i.value));};

designator returns [IdNode value]
   : i=id {value=$i.value;}
   | STR {value=new IdNode($STR.text);};

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
   : {boolean n=false;} (i=id ':' {n=true;})? d=decl_part_opt s=stmt_part e=exception_part_opt 'end' id? ';'
      {st=(n?new BlockStmtNode($i.value,$d.decls,$s.sts,$e.exs):new BlockStmtNode($d.decls,$s.sts,$e.exs));};
return_stmt returns [ReturnStmtNode st]
   : 'return' {st=new ReturnStmtNode();} (e=expr {st=new ReturnStmtNode($e.value);})? ';';
raise_stmt returns [RaiseStmtNode st]
   : 'raise' n=name {st=new RaiseStmtNode($n.name);} ';';

if_stmt returns [IfStmtNode st]
   : 'if' i=if_clause_list 'end' 'if' ';' {st=new IfStmtNode($i.value);};

if_clause_list returns [List<IfClauseNode> value]
@init {value=new ArrayList<IfClauseNode>();}
   : a=if_part {value.add($a.value);} (b=elsif_part {value.add($b.value);})* (c=else_part {value.add($c.value);})?;

if_part returns [IfClauseNode value]
   : e=expr 'then' s=stmt_list {value=new IfClauseNode($e.value,$s.sts);};
elsif_part returns [IfClauseNode value]
   : 'elsif' v=if_part {value=$v.value;};
else_part returns [IfClauseNode value]
   : 'else' s=stmt_list {value=new IfClauseNode($s.sts);};

loop_stmt returns [LoopStmtNode st]
   : {boolean i=false;} (n=id ':' {i=true;})? t=it_clause 'loop' s=stmt_list 'end' 'loop' ';'
      {st=(i?new LoopStmtNode($n.value,$t.value,$s.sts):new LoopStmtNode($t.value,$s.sts));};

it_clause returns [LoopClauseNode value]
   : 'while' e=expr {value=new WhileClauseNode($e.value);}
   | {boolean reverse=false;} 'for' i=id 'in' ('reverse' {reverse=true;})? d=discrete_range {value=new ForClauseNode($i.value, $d.value, reverse);}
   | {value=new WhileClauseNode(new BoolValNode(true));};
exit_stmt returns [ExitStmtNode st]
@init {ExprNode cond=new BoolValNode(true);}
   : {boolean i=false;} 'exit' (n=id {i=true;})? ('when' e=expr {cond=$e.value;})? ';'
      {st=(i?new ExitStmtNode($n.value,cond):new ExitStmtNode(cond));};
case_stmt returns [CaseStmtNode st]
   : 'case' e=expr 'is' w=when_list 'end' 'case' ';' {st=new CaseStmtNode($e.value,$w.value);};

when_list returns [List<WhenNode> value]
@init {value=new ArrayList<WhenNode>();}
   : ('when' w=when {value.add($w.value);})* 'when' w=other {value.add($w.value);};

when returns [WhenNode value]
   : c=choice_list '=>' s=stmt_list {value=new WhenNode($c.value,$s.sts);};

choice_list returns [List<ChoiceNode> value]
@init {value=new ArrayList<ChoiceNode>();}
   : c=choice {value.add($c.value);} ('|' c=choice {value.add($c.value);})*;

other returns [WhenNode value]
   : 'others' '=>' s=stmt_list {value=new WhenNode($s.sts);};
choice returns [ChoiceNode value]
   : a=expr {value=new ChoiceNode($a.value);} (DOTDOT b=expr {value=new ChoiceNode($a.value,$b.value);})?;

log_op returns [BinNode.Op op]
   : 'and' {op=BinNode.Op.AND;} ('then' {op=BinNode.Op.AND_THEN;})?
   | 'or' {op=BinNode.Op.OR;} ('else' {op=BinNode.Op.OR_ELSE;})?
   | 'xor' {op=BinNode.Op.XOR;};
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
mult_op returns [BinNode.Op op]
   : '*' {op=BinNode.Op.MULT;}
   | '/' {op=BinNode.Op.DIV;}
   | 'mod' {op=BinNode.Op.MOD;}
   | 'rem' {op=BinNode.Op.REM;};
un_bin_op returns [UnaryNode.Op unOp]
   : 'not' {unOp=UnaryNode.Op.NOT;}
   | 'abs' {unOp=UnaryNode.Op.ABS;};
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
   : c=CHAR {value=new CharValNode($c.text);}
   | s=STR {value=new StrValNode($s.text);}
   | b=BOOL {value=new BoolValNode($b.text);}
   | i=INT {value=new IntValNode($i.text);}
   | f=FLOAT {value=new FloatValNode($f.text);}
   | x=BASE_INT {value=new IntValNode($x.text);}
   | y=BASE_FLOAT {value=new FloatValNode($y.text);};

name_suffix returns [SuffixNode suff]
   : '.' s=designator {suff=new DotSuffixNode($s.value);}
   | a=arg_list {suff=new ParenSuffixNode($a.value);}
   | TICK i=id {suff=new AttrSuffixNode($i.value);};
name_suffix_list returns [List<SuffixNode> suffs]
@init {suffs=new ArrayList<SuffixNode>();}
   : (s=name_suffix {suffs.add($s.suff);})+ ('.' 'all' {suffs.add(new AllSuffixNode());})?
   | '.' 'all' {suffs.add(new AllSuffixNode());};
name returns [NameNode name]
   : i=id s=name_suffix_list {name=new NameNode($i.value, $s.suffs);}
   | i=id {name=new NameNode($i.value);};

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
   : i=id r=range_constraint {value=new ComponentChoiceNode(new SubtypeNode($i.value,$r.con));}
   | 'others' {value=new ComponentChoiceNode();};

component_tail returns [List<ComponentChoiceNode> value]
@init {value=new ArrayList<ComponentChoiceNode>();}
   : ('|' a=agg_choice {value.add($a.value);})*;

agg_choice returns [ComponentChoiceNode value]
   : a=simple_expr {value=new ComponentChoiceNode($a.value);} (DOTDOT b=simple_expr {value=new ComponentChoiceNode(new RangeNode($a.value,$b.value));})?
   | i=id r=range_constraint {value=new ComponentChoiceNode(new SubtypeNode($i.value,$r.con));}
   | 'others' {value=new ComponentChoiceNode();};

name_list returns [List<NameNode> names]
@init {names=new ArrayList<NameNode>();}
   : n=name {names.add($n.name);} (',' n=name {names.add($n.name);})*;

//
// LEXER RULES
//

TICK: {input.LA(3) != '\'' || input.LA(5) == '\''}?=> '\'';
CHAR: {input.LA(3) == '\'' && input.LA(5) != '\''}?=> '\'' . '\'';
BOOL: 'true' | 'false';
STR: '"' ('""' | ~('"' | EOL))* '"';
NAME: ALPHA ('_'? (ALPHA|DIGIT))*;
INT: INT_NUM INT_EXP?;
DOTDOT: '..';
FLOAT: (INT_NUM '.' INT_NUM EXP?)=> INT_NUM '.' INT_NUM EXP? {$type=FLOAT;}
     | INT_NUM {$type=INT;};

// enforce base is between 2 and 16, and that digits are valid for the base
BASE_INT
@init {int base=10;}
   : i=INT_NUM {base=Integer.parseInt($i.text);} {2<=base&&base<=16}?
      '#' v=HEX_NUM {isValid($v.text,base)}? '#' EXP?;
BASE_FLOAT
@init {int base=10;}
   : i=INT_NUM {base=Integer.parseInt($i.text);} {2<=base&&base<=16}? 
      '#' a=HEX_NUM '.' b=HEX_NUM {isValid($a.text+$b.text,base)}? '#' EXP?;

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
fragment INT_EXP: ('e'|'E') '+'? INT_NUM;
fragment EXP: ('e'|'E') ('+'|'-')? INT_NUM;

// vim: ft=antlr3

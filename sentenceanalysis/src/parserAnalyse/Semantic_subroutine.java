package parserAnalyse;

import mode.stack_node;

public class Semantic_subroutine {
public void semantic_subpro(stack_node tmp) {
	int pronum = 0;
	switch(pronum) {
	case  0:		/* start -> compound_statement */
		  break;
	     case  1:		/* compound_statement -> { } */
		  break;
	     case  2:		/* compound_statement -> { statement_list } */
		  break;
	     case  3:		/* compound_statement -> { declaration_list } */
		  break;
	     case  4:		/* compound_statement -> { declaration_list statement_list } */
		  break;
	     case  5:		/* declaration_list -> declaration */
		  /* nothing */
		  break;
	     case  6:		/* declaration_list -> declaration_list declaration */
		  /* nothing */
		  break;
	     case  7:		/* declaration -> type_specifier init_declarator ; */
		  break;
	     case  8:		/* type_specifier -> VOID */
		  /* unused */
		  break;
	     case  9:		/* type_specifier -> CHAR */
	    	 
		  break;
	     case 10:		/* type_specifier -> SHORT */
	    	
		  break;
	     case 11:		/* type_specifier -> INT */
	    	 
		  break;
	     case 12:		/* type_specifier -> LONG */
	    	 
		  break;
	     case 13:		/* type_specifier -> FLOAT */
	    	 
		  break;
	     case 14:		/* type_specifier -> DOUBLE */
	    	 
		  break;
	     case 15:		/* init_declarator -> IDENTIFIER */
		  break;
	     case 16:		/* init_declarator -> IDENTIFIER = assignment_expression */
		  break;
	     case 17:		/* statement_list -> statement */
		  break;
	     case 18:		/* statement_list -> statement_list statement */
		  break;
	     case 19:		/* statement -> compound_statement */
		  break;
	     case 20:		/* statement -> expression_statement */
		  break;
	     case 21:		/* statement -> selection_statement */
		  break;
	     case 22:		/* statement -> iteration_statement */
		  break;
	     case 23:		/* expression_statement -> ; */
		  break;
	     case 24:		/* expression_statement -> expression ; */
		  break;
	     case 25:		/* selection_statement -> IF ( expression ) statement */
		  break;
	     case 26:		/* selection_statement -> IF ( expression ) statement ELSE statement */
		  break;
	     case 27:		/* iteration_statement -> WHILE ( expression ) statement */
		  break;
	     case 28:		/* iteration_statement -> FOR ( expression_statement expression_statement expression ) statement */
		  break;
	     case 29:		/* primary_expression -> IDENTIFIER */
		  break;
	     case 30:		/* primary_expression -> CONSTANT */
		  break;
	     case 31:		/* primary_expression -> STRING_LITERAL */
		  break;
	     case 32:		/* primary_expression -> ( expression ) */
		  break;
	     case 33:		/* postfix_expression -> primary_expression */
		  break;
	     case 34:		/* postfix_expression -> postfix_expression INC_OP */
		  break;
	     case 35:		/* postfix_expression -> postfix_expression DEC_OP */
		  break;
	     case 36:		/* unary_expression -> postfix_expression */
		  break;
	     case 37:		/* unary_expression -> INC_OP unary_expression */
		  break;
	     case 38:		/* unary_expression -> DEC_OP unary_expression */
		  break;
	     case 39:		/* unary_expression -> unary_operator unary_expression */
		  break;
	     case 40:		/* unary_operator -> + */
	    	 
		  break;
	     case 41:		/* unary_operator -> - */
	    	
		  break;
	     case 42:		/* unary_operator -> ! */
	    	 
		  break;
	     case 43:		/* multiplicative_expression -> unary_expression */
	    	 
		  break;
	     case 44:		/* multiplicative_expression -> multiplicative_expression * unary_expression */
		  break;
	     case 45:		/* multiplicative_expression -> multiplicative_expression / unary_expression */
		  break;
	     case 46:		/* multiplicative_expression -> multiplicative_expression % unary_expression */
		  break;
	     case 47:		/* additive_expression -> multiplicative_expression */
		  break;
	     case 48:		/* additive_expression -> additive_expression + multiplicative_expression */
		  break;
	     case 49:		/* additive_expression -> additive_expression - multiplicative_expression */
		  break;
	     case 50:		/* relational_expression -> additive_expression */
		  break;
	     case 51:		/* relational_expression -> relational_expression < additive_expression */
		  break;
	     case 52:		/* relational_expression -> relational_expression > additive_expression */
		  break;
	     case 53:		/* relational_expression -> relational_expression LE_OP additive_expression */
		  break;
	     case 54:		/* relational_expression -> relational_expression GE_OP additive_expression */
		  break;
	     case 55:		/* equality_expression -> relational_expression */
		  break;
	     case 56:		/* equality_expression -> equality_expression EQ_OP relational_expression */
		  break;
	     case 57:		/* equality_expression -> equality_expression NE_OP relational_expression */
		  break;
	     case 58:		/* logical_and_expression -> equality_expression */
		  break;
	     case 59:		/* logical_and_expression -> logical_and_expression AND_OP equality_expression */
		  break;
	     case 60:		/* logical_or_expression -> logical_and_expression */
		  break;
	     case 61:		/* logical_or_expression -> logical_or_expression OR_OP logical_and_expression */
		  break;
	     case 62:		/* assignment_expression -> logical_or_expression */
		  break;
	     case 63:		/* assignment_expression -> unary_expression = assignment_expression */
		  break;
	     case 64:		/* expression -> assignment_expression */
		  break;
	     case 65:		/* expression -> expression , assignment_expression */
		  break;
	}
}
}

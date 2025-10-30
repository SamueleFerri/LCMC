package compiler;

import compiler.AST.*;
import compiler.lib.Node;
import compiler.lib.BaseASTVisitor;

public class PrintASTVisitor extends BaseASTVisitor<Void> {

    String indent;

    public Void visit(Node n) {
        String temp=indent;
        indent=(indent==null)?"":indent+"  ";
        super.visit(n);
        indent=temp;
        return null;
    }

    Void printNode(Node n) {
		System.out.println(indent+extractNodeName(n.getClass().getName()));
        return null;
	}

    Void printNode(Node n, String s) {
		System.out.println(indent+extractNodeName(n.getClass().getName())+": "+s);
        return null;
	}

	String extractNodeName(String s) { // s is in the form compiler.AST$NameNode
		return s.substring(s.lastIndexOf('$')+1,s.length()-4);
	}

	public Void visitNode(ProgNode n) {
		printNode(n);
        visit(n.exp);
        return null;
	}

	public Void visitNode(PlusNode n) {
        printNode(n);
        visit(n.left);
        visit(n.right);
        return null;
	}

	public Void visitNode(TimesNode n) {
        printNode(n);
        visit(n.left);
        visit(n.right);
        return null;
	}

	public Void visitNode(IntNode n) {
        printNode(n, n.val.toString());
        return null;
	}
}

//	PrintASTVisitor() { super(true); }



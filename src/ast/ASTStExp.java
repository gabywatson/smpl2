/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import java.util.ArrayList;


public abstract class ASTStExp extends ASTNode{

    public ASTStExp() {
    }

    public ASTStExp(String nm) {
        super(nm);
    }

    public ASTStExp(String nm, ASTNode... c) {
        super(nm, c);
    }

    public ASTStExp(String nm, ArrayList<ASTNode> c) {
        super(nm, c);
    }
    
}

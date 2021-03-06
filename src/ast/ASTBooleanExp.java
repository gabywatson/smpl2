/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;

public class ASTBooleanExp extends ASTBoExp{

    boolean value;
    
    public ASTBooleanExp(boolean param){
        super("boolean");
        value = param;
    }
    
    public boolean getValue(){
        return value;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitMakeBoolean(this, state);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import smpl.Visitor;

public class ASTNmLEqlExp extends ASTNmExp{
    public ASTNmLEqlExp(ASTNode i1, ASTNode i2){
        super("<=", i1, i2);
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> visitor, S state) {
        return visitor.visitNmLEqls(this, state);
    }
}

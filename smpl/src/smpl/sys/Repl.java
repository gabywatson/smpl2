package smpl.sys;

import smpl.lang.*;
import java.io.*;
import javax.swing.JOptionPane.*;

public class Repl {

   
   
   
    public static void repl(InputStream is, Visitor visitor) {
        String PROMPT = "\nSMPL >> ";
        while (true) {
            System.out.print(PROMPT);
	   
            Object result = Driver.parseEvalReturn(is, visitor);
            visitor.display(result);
	
        }
	
    }

    public static void main(String[] args) {
        // call setup code from Driver.
        Driver driver = new Driver(args);
        driver.run();
	
	
        repl(System.in, driver.getVisitor());
    
    
    }
}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tosade.domain.TargetSchema;
import tosade.target.Reader;

/**
 *
 * @author Jelle
 */
public class Tosade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Generator.Run();
    }
    
}

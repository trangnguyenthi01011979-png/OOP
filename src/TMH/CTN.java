
package TMH;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CTN {
    public CTN(){
        
    }
    private Connection con(){
        Connection c = null;
        try{
            c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            System.out.println("Kết nối thành công!");
        }catch(Exception e){
            System.out.println("Kết nối không thành công!");
        }
        return c;
    }
    
    public Connection c(){
        return con();
    }
}

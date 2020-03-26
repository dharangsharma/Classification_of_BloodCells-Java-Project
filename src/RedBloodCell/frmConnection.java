package RedBloodCell;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class frmConnection
{

public Connection con=null;
 public static Statement stat = null;
public frmConnection()
{
	
}
public Connection getCon() throws Exception
{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cbir", "root", "");
            return con;

}
public void closeCon()
{
    try
    {
        con.close();
    }
    catch(Exception ex)
    {
        
    }
}
public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy_MM_dd_hh_mm_ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

}


package bootathan;

import java.sql.*;
public class Bean 
{
    private static Connection connection;
    public static Connection getConnection() throws Exception
    {
        Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","");
        return connection;
    }
}

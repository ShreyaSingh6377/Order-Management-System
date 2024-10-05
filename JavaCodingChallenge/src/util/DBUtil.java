


package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil 
{
	public static Connection getDBConn(String connString)
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded..");
		con=DriverManager.getConnection(connString);
		//System.out.println(connString);
		
			//con=DriverManager.getConnection(("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu@6377"));
			System.out.println("Connected...");
		}catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Driver Loading Failed");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Not Connected to Database");
		}
		return con;
	}
	public static void main(String[] args) 
	{
		String connString=DBPropertyUtil.getConnString("data.Properties");
		Connection con=getDBConn(connString);
		System.out.print(con);
		//Connection con=getDBConn("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu@6377");
		//System.out.println(con);
	}
}

package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class DBPropertyUtil
{
	public static String getConnString(String propFileName)
	{
		String connString=null;
		Properties propsObject=new Properties();
		//i have created a stream connected to properties file
		try(FileInputStream fis=new FileInputStream(propFileName))  //try with resources
		{
				propsObject.load(fis);//loading the properties existed in file into propsObject
				//creating connection string
				connString=propsObject.getProperty("db.url")+"?user="+propsObject.getProperty("db.username")+"&password="+propsObject.getProperty("db.password");
		}catch (IOException fnf) {
			fnf.printStackTrace();
			System.out.println("Error:  Not able to Properties from File");
		}
		//returning connection string
		return connString;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(getConnString("data.properties"));
	}
	
}

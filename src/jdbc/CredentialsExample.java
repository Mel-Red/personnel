package jdbc;

public class CredentialsExample 
{
	private static String driver ="mysql",
			driverClassName = "com.mysql.cj.jdbc.Driver",
			host = "localhost", 
			port ="3306",
			database ="personnel",
			user = "root",
			password = "maithi";
	
	static String getUrl() 
	{
		return "jdbc:" + driver + "://" + host + "/" + database + "?&"
				+ "serverTimezone=UTC";
	}
	
	static String getDriverClassName()
	{
		return driverClassName;
	}
	
	static String getUser() 
	{
		return user;
	}

	static String getPassword() 
	{
		return password;
	}
}

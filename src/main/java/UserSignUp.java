
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class UserSignUp implements Servlet
{
	public void init(ServletConfig h)
	{ }
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();

		String type=req.getParameter("type");	
		String contact=req.getParameter("contactname");	
		String user=req.getParameter("username");	
		String email=req.getParameter("emailname");
		String pass=req.getParameter("passname");	
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			PreparedStatement ps=con.prepareStatement("insert into Panchuser values(?,?,?,?,?)");

			ps.setString(1,type);
			ps.setString(2,contact);
			ps.setString(3,user);
			ps.setString(4,email);
			ps.setString(5,pass);
			ps.executeUpdate();

			RequestDispatcher dispatcher=req.getRequestDispatcher("User LogIn.html");
			
			pw.println("<br> <center> <font color='red' size=+2> Hey "+ user +" you are registered succesfully </font> </center>");
			dispatcher.include(req, res);
		}
		catch(Exception e)
		{ }
		pw.close();
	}
	
	public void destroy()
	{ }
	public String getServletInfo()
	{
		return(null);	
	}
	public ServletConfig getServletConfig()
	{
		return(null);
	}
}
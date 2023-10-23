
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class AfterLogIn implements Servlet
{
	public void init(ServletConfig h)
	{ }
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String circular_no=req.getParameter("cno");	
		String subject=req.getParameter("subject");
       	String categary=req.getParameter("usercategary");
		String language=req.getParameter("userlanguage");
		String file_desc=req.getParameter("fdescription");
		String issued_date=req.getParameter("issuedDate");
		String issued_by=req.getParameter("issueBy");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=con.prepareStatement("insert into Panchayat values(?,?,?,?,?,?,?)");

			ps.setString(1,circular_no);
			ps.setString(2,subject);
			ps.setString(3,categary);
			ps.setString(4,language);
			ps.setString(5,file_desc);
			ps.setString(6,issued_date);
			ps.setString(7,issued_by);
			ps.executeUpdate();

			RequestDispatcher dispatcher=req.getRequestDispatcher("After LogIn.html");

			out.println("<html><body><center>");
			out.println("<font size=+2> Congrtulations " + circular_no + " Circular No is Registered Succesfully </font>");
			out.println("</center></body></html>");

			dispatcher.include(req, res);	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		out.close();
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
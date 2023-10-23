
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.sql.*;
import java.io.*;

public class ViewData extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			PreparedStatement ps=con.prepareStatement("select * from Panchayat");
			ResultSet rs=ps.executeQuery();
			
			out.println("<html><body ><center>");
			out.println("<table border='2'>");
			out.println("<tr><th>ser no.</th><th>Circular_no</th><th>Categary</th><th>File language</th><th>File Description</th><th>Issued on</th><th>Issued By</th><th>Action</th></tr>");
			out.println("</center></body></html>");

			int serno=1;
			while(rs.next())
			{
				out.println("<tr><td>"+serno+"</td><td>"+rs.getString("circular_no")+"</td><td>"+rs.getString("categary")+"</td><td>"+rs.getString("language")+"</td><td>"+rs.getString("file_desc")+"</td><td>"+rs.getString("issued_date")+"</td><td>" +rs.getString("issued_by")+"</td><td>"+"<a href=Delete.html> DELETE </a>" + "</td></tr>");
				serno++;
			}
		}
		catch(Exception e)
		{ }
	}
}

import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;

public class Delete extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String circular=req.getParameter("Circular");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement ps=conn.prepareStatement("delete from Panchayat where circular_no=?");
			ps.setString(1, circular);
			int r=ps.executeUpdate();

			RequestDispatcher dispatcher=req.getRequestDispatcher("ViewData");

			out.println("<center> <font color='red'> Record is Deleted.....! </font></center>");
			dispatcher.include(req, res);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
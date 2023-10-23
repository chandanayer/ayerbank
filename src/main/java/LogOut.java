
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
public class LogOut extends HttpServlet
{
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		out.println("<html><body><center>");
		out.println("<font size=+2> Succesfully logged out</font>");
						
		out.println("</center>");
		out.println("</body></html>");	

		RequestDispatcher disp=req.getRequestDispatcher("First.html");
		disp.include(req,res);
		HttpSession session=req.getSession(false);
		session.invalidate();
		
	}
}

import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class OfficialLogIn extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String type=req.getParameter("type");
		String user=req.getParameter("textname");	
		String password=req.getParameter("passname");
		HttpSession session=req.getSession();
		session.setAttribute("name",user);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();

			if(user.equals("Chandan") && password.equals("Singh"))
			{
				out.println("Welcome Admin " + user);
				RequestDispatcher dispatcher1=req.getRequestDispatcher("After LogIn.html");
				dispatcher1.forward(req, res);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Invalid Username or Password", "Alert Message", JOptionPane.WARNING_MESSAGE);
				RequestDispatcher dispatcher=req.getRequestDispatcher("Official LogIn.html");
				dispatcher.include(req, res);
			}  
		}
		catch(Exception e)
		{ }	
	}
}

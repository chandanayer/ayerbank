
import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

public class UserLogIn extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

		String user=req.getParameter("textname");	
		String pass=req.getParameter("passname");
		HttpSession session=req.getSession();
		session.setAttribute("name",user);
		
		try
		{
			int flag=0;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			Statement s=con.createStatement();

			ResultSet rs=s.executeQuery("select username,pass from Panchuser");
			while(rs.next())
			{
				if(user.equals(rs.getString(1)) && pass.equals(rs.getString(2)))
				{
					out.println("<br> <center> <font color='red' size=+2> Welcome " + user + "</font> </center>");
					 
				     RequestDispatcher dispatcher=req.getRequestDispatcher("LogOut.html");
				     dispatcher.include(req, res);
				     flag=1;
				     break;
				}
			}
			if(flag==0)
			{
				JOptionPane.showMessageDialog(null,"Invalid Username or Password", "Alert Message",JOptionPane.WARNING_MESSAGE);
				RequestDispatcher dispatcher=req.getRequestDispatcher("User LogIn.html");
				dispatcher.include(req, res);
			}
		}	
		catch(Exception e)
		{ }	
	}
}
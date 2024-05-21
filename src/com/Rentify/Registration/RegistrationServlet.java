package com.Rentify.Registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UFirstname = request.getParameter("firstname");
		String ULastname = request.getParameter("lastname");
		String UEmail = request.getParameter("email");
		String UPassword =  request.getParameter("pass");
		String UPhone = request.getParameter("contact");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentify?useSSL=false","root","root");
			PreparedStatement pst = con.prepareStatement("insert into reg(First_Name,Last_Name,Email,Password,Phone) values(?,?,?,?,?)");
			
			pst.setString(1, UFirstname);
			pst.setString(2, ULastname);
			pst.setString(3, UEmail);
			pst.setString(4, UPassword);
			pst.setString(5, UPhone);
			
			int rowcount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			if(rowcount > 0)
			{
				request.setAttribute("status", "success");
				
			}else
			{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

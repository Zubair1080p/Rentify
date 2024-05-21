package com.Rentify.Registration;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Uemail = request.getParameter("username");
		String Upwd =  request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentify?useSSL=false","root","root");
			PreparedStatement pst = con.prepareStatement("select * from reg where Email = ? and Password = ?");
			pst.setString(1, Uemail);
			pst.setString(2, Upwd);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				session.setAttribute("name", rs.getString("Last_Name"));
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			else
			{
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

}

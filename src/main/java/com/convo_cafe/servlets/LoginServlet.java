package com.convo_cafe.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.convo_cafe.dao.RestaurantDAO;
import com.convo_cafe.dao.UserDAO;
import com.convo_cafe.objects.Restaurant;
import com.convo_cafe.objects.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	User loginUser = new User();
	Restaurant loginRest = new Restaurant();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itsAUser = request.getParameter("form user login");//change to form name for collecting email
		String itsARest = request.getParameter("form rest login");//change to form name for collecting password
		
		try{
			if(!(itsAUser.equals(null))){
				loginUser.setEmail(request.getParameter("form user login email"));
				loginUser.setPassword(request.getParameter("form user login password"));
				
				System.out.println("Collected user to login.");
				
				boolean lostOrFound = UserDAO.userLoginSearch(loginUser);
				
				if(lostOrFound){
					System.out.println("User was found and redirected to home page.");
					request.getRequestDispatcher("/WEB-INF/home.jsp");//change to home page
				} else{
					response.sendRedirect("loginfailed.jsp");
					System.out.println("Invalid user, try again.");
				}
			}
		} catch(NullPointerException e){
			
			try{
				if (!(itsARest.equals(null))){
					
					loginRest.setEmail(request.getParameter("form rest login"));//change to actual form name
					loginRest.setPassword(request.getParameter("form rest password"));//change to actual form name
					
					System.out.println("Collected Restaurant to login");
					
					boolean lostOrFound = RestaurantDAO.restLoginSearch(loginRest);
					
					if(lostOrFound){
						System.out.println("Success, redirected to home page");
						request.getRequestDispatcher("/WEB-INF/Homepage.jsp");//change to page name
						System.out.println("Rest was found and redirected.");
					} else{
						response.sendRedirect("loginfailed page or back to login page with text");
						System.out.println("Invalid entry, try again.");
					}
				}
			} catch (NullPointerException ex){
				response.sendRedirect("login failed page or redirect to login page");
				System.out.println("Error");
			}
		}
	}

}

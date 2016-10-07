package com.convo_cafe.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.convo_cafe.dao.UserDAO;
import com.convo_cafe.objects.User;



/**
 * Servlet implementation class addToDB
 */
@WebServlet("/UserServlet")//must match jsp or html
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	User userToAddToDB = new User();
	User searchUser = new User();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		
		String theName = request.getParameter("form-name");//change to actual form name from front end
		
		try{
			
			if(!(theName.equals(null))){
				
				
				
			
		
				userToAddToDB.setName(request.getParameter("name"));
				userToAddToDB.setAgeRange(request.getParameter("age_range"));
				userToAddToDB.setGender(request.getParameter("gender"));
				userToAddToDB.setStreetAddress(request.getParameter("street_address"));
				userToAddToDB.setCity(request.getParameter("city"));
				userToAddToDB.setState(request.getParameter("state"));
				userToAddToDB.setZip(request.getParameter("zip"));
				userToAddToDB.setAboutMe(request.getParameter("about_me"));
				userToAddToDB.setEmail(request.getParameter("email"));
				userToAddToDB.setPassword(request.getParameter("password"));
				userToAddToDB.setFirstLanguageId(request.getParameter("first_language_id"));
				userToAddToDB.setLearningLanguageId(request.getParameter("learning_language_id"));
				userToAddToDB.setSkillLevel(request.getParameter("skill_level_id"));
		
				System.out.println("Collected a new User entry");
				
				UserDAO.userWriteToDB(userToAddToDB);
				
				if(com.convo_cafe.dao.UserDAO.passOrFail){
					response.sendRedirect("userlogin.jsp");//change to actual user login page
					System.out.println("User has been added to the database");
				}
			}
		} catch (NullPointerException e){
			boolean foundAUser;
			
			String searchByLearningLanguage = request.getParameter("learning_language_id");
			String searchBySkillLevel = request.getParameter("skill_level_id");
			
			System.out.println(searchByLearningLanguage);
			System.out.println(searchBySkillLevel);
			
			foundAUser = UserDAO.searchForUsers(searchByLearningLanguage, searchBySkillLevel);
			
			if(foundAUser){
				response.sendRedirect("readusertable.jsp");//change to actual page to read tale
			}
		}
	}

}

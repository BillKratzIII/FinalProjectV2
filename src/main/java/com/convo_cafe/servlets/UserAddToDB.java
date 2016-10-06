package com.convo_cafe.servlets;

import java.io.IOException;

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
@WebServlet("/UserAddToDB")//must match jsp or html
public class UserAddToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddToDB() {
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
		User addTodb = new User();
		
		addTodb.setName(request.getParameter("name"));
		addTodb.setAgeRangeId(request.getParameter("ageRange"));
		addTodb.setGender(request.getParameter("gender"));
		addTodb.setStreetAddress(request.getParameter("streetAddress"));
		addTodb.setCity(request.getParameter("city"));
		addTodb.setState(request.getParameter("state"));
		addTodb.setZip(request.getParameter("zip"));
		addTodb.setAboutMe(request.getParameter("aboutMe"));
		addTodb.setEmail(request.getParameter("email"));
		addTodb.setPassword(request.getParameter("password"));
		addTodb.setFirstLanguageId(request.getParameter("firstLanguage"));
		addTodb.setLearningLanguageId(request.getParameter("learningLanguage"));
		addTodb.setSkillLevelId(request.getParameter("skillLevel"));
		
		System.out.println(addTodb.toString());
		
		UserDAO.userWriteToDB(addTodb);
		
		//request.getRequestDispatcher("add.jsp").forward(request, response);
	}

}

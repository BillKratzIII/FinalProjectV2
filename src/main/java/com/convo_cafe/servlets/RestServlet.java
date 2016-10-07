package com.convo_cafe.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.convo_cafe.dao.RestaurantDAO;
import com.convo_cafe.objects.Restaurant;

/**
 * Servlet implementation class RestaurantAddToDB
 */
@WebServlet("/RestServlet")//must match jsp or html
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Restaurant restToAddToDB = new Restaurant();
	Restaurant searchARest = new Restaurant();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestServlet() {
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
		
		
		try{
			
			
		
		
		restToAddToDB.setName(request.getParameter("rest_name"));//input form name for "name" and so on
		restToAddToDB.setStreetAddress(request.getParameter("rest_address"));
		restToAddToDB.setCity(request.getParameter("rest_city"));
		restToAddToDB.setState(request.getParameter("rest_state"));
		restToAddToDB.setZip(request.getParameter("rest_zip"));
		restToAddToDB.setLanguageId(request.getParameter("rest_lang"));
		restToAddToDB.setEmail(request.getParameter("rest_email"));
		restToAddToDB.setPassword(request.getParameter("rest_pass"));
		
		System.out.println("Collected a new Restaurant entry");
		
		RestaurantDAO.restaurantWriteToDB(restToAddToDB);
		response.sendRedirect("restaurant page");// change to actual page name
		
				
	//}

		}catch(NullPointerException e){
			boolean foundARest;
			
			String searchByLangId = request.getParameter("language_id");
			
			System.out.println(searchByLangId);
			
			foundARest = RestaurantDAO.searchForRestaurant(searchByLangId);
			
			if(foundARest){
				response.sendRedirect("FakeRest.html");//put in reference to read table page
			}
		}
	}
}

package com.convo_cafe.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.convo_cafe.dao.RestaurantDAO;
import com.convo_cafe.dao.UserDAO;
import com.convo_cafe.objects.Restaurant;
import com.convo_cafe.objects.User;

/**
 * Servlet implementation class RestaurantDeleteFromDB
 */
@WebServlet("/RestaurantDeleteFromDB")//must match jsp or html
public class RestaurantDeleteFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantDeleteFromDB() {
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
		
		
//		deleteFromDB.setPlayerID(request.getParameter("Player ID"));
		
		int aNumber = Integer.parseInt(request.getParameter("restaurant_id"));
		
		RestaurantDAO.restaurantDeleteFromDB(aNumber);
		
		//request.getRequestDispatcher("delete.jsp").forward(request, response);
	}

}

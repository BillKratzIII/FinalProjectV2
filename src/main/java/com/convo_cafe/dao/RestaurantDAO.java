package com.convo_cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.convo_cafe.objects.Restaurant;
import com.convo_cafe.objects.User;

public class RestaurantDAO {
		
		public static boolean passOrFail = false;
		public static Restaurant activeRest = new Restaurant();
		public static final ArrayList<Restaurant> ourRestaurant = new ArrayList<>();
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root$autoReconnect=true&useSSL=false";
		static final String USER = "root";
		static final String PASSWORD = "root";

		static Connection CONN = null;
		static Statement STMT = null;
		static PreparedStatement PREP_STMT = null;
		static ResultSet RES_SET = null;
		
		
		//connecting to DB
		public static void restaurantConnToDB() {

			try {
				
				Class.forName(JDBC_DRIVER);
				
				
				System.out.println("Trying to connect to the Database...");
				CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				System.out.println("Connected to the Database");
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Failed to connect to the Database");
				e.printStackTrace();
			}

		}
		
		//search for a restaurant
		public static boolean searchForRestaurant(String language_id){
			String formatStmt = searchStmt(language_id);
			
			try{
				ourRestaurant.clear();
				System.out.println("Cleared restaurant arrayList");
				restaurantConnToDB();
				STMT = CONN.createStatement();
				RES_SET = STMT.executeQuery(formatStmt);
				
				while(RES_SET.next()){
					
					Restaurant restForUser = new Restaurant();
					
					restForUser.setRestaurantId(RES_SET.getInt("restaurant_id"));
					restForUser.setName(RES_SET.getString("name"));
					restForUser.setStreetAddress(RES_SET.getString("street_address"));
					restForUser.setCity(RES_SET.getString("city"));
					restForUser.setState(RES_SET.getString("state"));
					restForUser.setZip(RES_SET.getString("zip"));
					restForUser.setLanguageId(RES_SET.getString("language_id"));
					restForUser.setLanguage(RES_SET.getString("language"));
					restForUser.setEmail(RES_SET.getString("email"));
					restForUser.setPassword(RES_SET.getString("password"));
					
					System.out.println(restForUser.toString());
					ourRestaurant.add(restForUser);
				}
			}catch (SQLException e){
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(ourRestaurant.size()>0){
				return true;
			}else {
			return false;
			}
		}
		
		//restaurant login
		public static boolean restLoginSearch(Restaurant loginRest){
			boolean restFound = false;
			boolean passwordMatch = false;
			restaurantReadFromDB();
			
			for (int i=0; i<ourRestaurant.size(); i++){
				if(ourRestaurant.get(i).getEmail().equalsIgnoreCase(loginRest.getEmail()) && 
						ourRestaurant.get(i).getPassword().equals(loginRest.getPassword())){
					System.out.println("Rest found, sucessful login");
					activeRest = ourRestaurant.get(i);
					System.out.println(activeRest.toString());
					restFound = true;
					passwordMatch = true;
				}else if(ourRestaurant.get(i).getEmail().equalsIgnoreCase(loginRest.getEmail())){
					System.out.println("User found, incorrect password");
					restFound = true;
				}
			}
			if(!restFound && !passwordMatch){
				System.out.println("No user found by that email");
			}
			return restFound;
		}
		
		public static void restLogOut(){
			activeRest.setRestaurantId(0);
			activeRest.setName(null);
			activeRest.setStreetAddress(null);
			activeRest.setCity(null);
			activeRest.setState(null);
			activeRest.setZip(null);
			activeRest.setLanguageId(null);
			activeRest.setLanguage(null);
			activeRest.setEmail(null);
			activeRest.setPassword(null);
		}
		
		//Gather info from database
		public static void restaurantReadFromDB() {
			
			ourRestaurant.clear();
			System.out.println("Cleared restaurant arrayList");
			restaurantConnToDB();
			

			try {
				STMT = CONN.createStatement();
				RES_SET = STMT.executeQuery("SELECT " 
				+"restaurant_id, name, street_address, city, state, zip, restaurant.language_id, first_language.language, email, password " 
				+"FROM convo_cafe.restaurant "
				+"JOIN " 
				+"convo_cafe.first_language ON (convo_cafe.restaurant.language_id = convo_cafe.first_language.language_id);");

				while (RES_SET.next()) {
					Restaurant restInDB = new Restaurant();

					restInDB.setRestaurantId(RES_SET.getInt("restaurant_id"));
					restInDB.setName(RES_SET.getString("name"));
					restInDB.setStreetAddress(RES_SET.getString("street_address"));
					restInDB.setCity(RES_SET.getString("city"));
					restInDB.setState(RES_SET.getString("state"));
					restInDB.setZip(RES_SET.getString("zip"));
					restInDB.setLanguageId(RES_SET.getString("language_id"));
					restInDB.setLanguage(RES_SET.getString("language"));
					restInDB.setEmail(RES_SET.getString("email"));
					restInDB.setPassword(RES_SET.getString("password"));
					
										
					System.out.println(restInDB.toString());
					ourRestaurant.add(restInDB);

				} 


			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		
		//add to database
		public static void restaurantWriteToDB(Restaurant restaurants){
			Restaurant restToAdd = new Restaurant();
			
			restToAdd = restaurants;
			
			restaurantConnToDB();
			
			try{
				PREP_STMT =CONN.prepareStatement(insertToDB);
				
				PREP_STMT.setString(1, restToAdd.getName());
				PREP_STMT.setString(2, restToAdd.getStreetAddress());
				PREP_STMT.setString(3, restToAdd.getCity());
				PREP_STMT.setString(4, restToAdd.getState());
				PREP_STMT.setString(5, restToAdd.getZip());
				PREP_STMT.setString(6, restToAdd.getLanguageId());
				PREP_STMT.setString(7, restToAdd.getEmail());
				PREP_STMT.setString(8, restToAdd.getPassword());
				
								
				PREP_STMT.executeUpdate();
				passOrFail = true;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//update to Database
		public static void restaurantUpdateDB(Restaurant newRestObj){
			Restaurant restToUpdate = new Restaurant();
			
			restToUpdate = newRestObj;
			restaurantConnToDB();
			
			try{
				PREP_STMT = CONN.prepareStatement(updateToDB);
				
				PREP_STMT.setString(1, restToUpdate.getName());
				PREP_STMT.setString(2, restToUpdate.getStreetAddress());
				PREP_STMT.setString(3, restToUpdate.getCity());
				PREP_STMT.setString(4, restToUpdate.getState());
				PREP_STMT.setString(5, restToUpdate.getZip());
				PREP_STMT.setString(6, restToUpdate.getLanguageId());
				PREP_STMT.setString(7, restToUpdate.getEmail());
				PREP_STMT.setString(8, restToUpdate.getPassword());
				PREP_STMT.setString(9, String.valueOf(restToUpdate.getRestaurantId()));
				
				
				PREP_STMT.executeUpdate();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		
		
		//delete from database
		public static void restaurantDeleteFromDB(int id){
			
			restaurantConnToDB();
			try{
				PREP_STMT = CONN.prepareStatement(deleteDB);
						
				PREP_STMT.setInt(1, id);
				
				PREP_STMT.executeUpdate();
				System.out.println("restaurant deleted");
				
			} catch (SQLException e){
				e.printStackTrace();
			}

		}
		
		private static String deleteDB = "DELETE FROM convo_cafe.restaurant WHERE restaurant_id = (?);";
		
		private static String insertToDB = "INSERT INTO convo_cafe.restaurant " 
				+"(name, street_address, city, state, zip, language_id, email, password) " 
				+"VALUES " 
				+"(?, ?, ?, ?, ?, ?, ?, ?)";
		
		private static String updateToDB =
					"UPDATE convo_cafe.restaurant " 
					+ "SET " 
					+ "name = (?), street_address = (?), city = (?), state = (?), zip = (?), language_id = (?), email = (?), password = (?) " 
					+ "WHERE restaurant_id = (?)";
		
		private static String searchStmt(String language_id){
						
			return "SELECT "
			+ "restaurant_id, name, street_address, city, state, zip, restaurant.language_id, first_language.language, email, password "
			+ "FROM convo_cafe.restaurant "
			+ "JOIN " 
			+ "convo_cafe.first_language ON (convo_cafe.restaurant.language_id = convo_cafe.first_language.language_id) "
			+ "WHERE restaurant.language_id = '" + language_id + "';";
		}
		
		
		
	}
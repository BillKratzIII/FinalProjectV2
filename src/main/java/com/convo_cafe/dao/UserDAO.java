package com.convo_cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.convo_cafe.objects.User;

public class UserDAO {
	
	public static boolean passOrFail = false;
	public static User activeUser = new User();
	public static final ArrayList<User> ourUser = new ArrayList<>();
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root$autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";

	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	
	
	//connect to database
	public static void userConnToDB() {

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
	
	public static boolean searchForUsers(String learning_language_id, String skill_level_id){
		String formatStmt = searchStmt(learning_language_id, skill_level_id);
		
		try{
			//ourUser.clear();
			//System.out.println("Cleared users arrayList");
			userConnToDB();
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery(formatStmt);
			
			while(RES_SET.next()){
				User userForUser = new User();
				
				userForUser.setUserId(RES_SET.getInt("user_id"));
				userForUser.setName(RES_SET.getString("name"));
				userForUser.setAgeRangeId(RES_SET.getString("age_range_id"));
				userForUser.setAgeRange(RES_SET.getString("age_range"));
				userForUser.setGender(RES_SET.getString("gender"));
				userForUser.setStreetAddress(RES_SET.getString("street_address"));
				userForUser.setCity(RES_SET.getString("city"));
				userForUser.setState(RES_SET.getString("state"));
				userForUser.setZip(RES_SET.getString("zip"));
				userForUser.setAboutMe(RES_SET.getString("about_me"));
				userForUser.setEmail(RES_SET.getString("email"));
				userForUser.setPassword(RES_SET.getString("password"));
				userForUser.setFirstLanguageId(RES_SET.getString("first_language_id"));
				userForUser.setFirstLanguage(RES_SET.getString("first_language"));
				userForUser.setLearningLanguageId(RES_SET.getString("learning_language_id"));
				userForUser.setSkillLevel(RES_SET.getString("skill_level_id"));
				userForUser.setFirstLanguageId(RES_SET.getString("first_language_id"));
				userForUser.setLearningLanguageId(RES_SET.getString("learning_language_id"));
				userForUser.setLearningLanguage(RES_SET.getString("learning_language"));
				userForUser.setSkillLevel(RES_SET.getString("skill_level_id"));
				userForUser.setSkillLevel(RES_SET.getString("skill_level"));
				
				
				System.out.println(userForUser.toString());
				ourUser.add(userForUser);
				
			}
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		if(ourUser.size()>0){
			return true;
		}else {
		return false;
		}
	}

	//gather info from database
	public static void userReadFromDB() {
		//ourUser.clear();
		//System.out.println("Cleared users arrayList");
		userConnToDB();
		

		try {
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT " 
			+ "user.user_id, user.name, user.age_range_id, age.age_range, user.gender, user.street_address, user.city, user.state, user.zip, user.about_me, user.email, user.password, user.first_language_id, first_language.language as first_language, user.learning_language_id, learning_language.language as learning_language, user.skill_level_id, skill_level.skill_level "
			+ "FROM convo_cafe.user "
			+ "JOIN convo_cafe.age ON (user.age_range_id = age.age_id) "
			+ "JOIN convo_cafe.first_language ON (user.first_language_id = first_language.language_id) "
			+ "JOIN convo_cafe.learning_language ON (user.learning_language_id = learning_language.language_id) "
			+ "JOIN convo_cafe.skill_level ON (user.skill_level_id = skill_level.skill_level_id);");

			while (RES_SET.next()) {
				User usersInDB = new User();
				
				usersInDB.setUserId(RES_SET.getInt("user_id"));
				usersInDB.setName(RES_SET.getString("name"));
				usersInDB.setAgeRangeId(RES_SET.getString("age_range_id"));
				usersInDB.setAgeRange(RES_SET.getString("age_range"));
				usersInDB.setGender(RES_SET.getString("gender"));
				usersInDB.setStreetAddress(RES_SET.getString("street_address"));
				usersInDB.setCity(RES_SET.getString("city"));
				usersInDB.setState(RES_SET.getString("state"));
				usersInDB.setZip(RES_SET.getString("zip"));
				usersInDB.setAboutMe(RES_SET.getString("about_me"));
				usersInDB.setEmail(RES_SET.getString("email"));
				usersInDB.setPassword(RES_SET.getString("password"));
				usersInDB.setFirstLanguageId(RES_SET.getString("first_language_id"));
				usersInDB.setFirstLanguage(RES_SET.getString("first_language"));
				usersInDB.setLearningLanguageId(RES_SET.getString("learning_language_id"));
				usersInDB.setLearningLanguage(RES_SET.getString("learning_language"));
				usersInDB.setSkillLevelId(RES_SET.getString("skill_level_id"));
				usersInDB.setSkillLevel(RES_SET.getString("skill_level"));
				
				System.out.println(usersInDB.toString());
				ourUser.add(usersInDB);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//add new info to database
	public static void userWriteToDB(User users){
		User userToAdd = new User();
		
		userToAdd = users;
		
		userConnToDB();
		
		try{
			
			PREP_STMT = CONN.prepareStatement(insertToDB);
			
			PREP_STMT.setString(1, userToAdd.getName());
			PREP_STMT.setString(2, userToAdd.getAgeRangeId());
			PREP_STMT.setString(3, userToAdd.getGender());
			PREP_STMT.setString(4, userToAdd.getStreetAddress());
			PREP_STMT.setString(5, userToAdd.getCity());
			PREP_STMT.setString(6, userToAdd.getState());
			PREP_STMT.setString(7, userToAdd.getZip());
			PREP_STMT.setString(8, userToAdd.getAboutMe());
			PREP_STMT.setString(9, userToAdd.getEmail());
			PREP_STMT.setString(10, userToAdd.getPassword());
			PREP_STMT.setString(11, userToAdd.getFirstLanguageId());
			PREP_STMT.setString(12, userToAdd.getLearningLanguageId());
			PREP_STMT.setString(13, userToAdd.getSkillLevelId());
			
			System.out.println(PREP_STMT);			
			
			
			PREP_STMT.executeUpdate();
			
			passOrFail = true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean userLoginSearch(User loginUser){
		boolean userFound = false;
		boolean passwordMatch = false;
		userReadFromDB();
		
		for (int i=0; i<ourUser.size(); i++){
			if(ourUser.get(i).getEmail().equalsIgnoreCase(loginUser.getEmail()) && 
					ourUser.get(i).getPassword().equals(loginUser.getPassword())){
				System.out.println("User found, sucessful login");
				activeUser = ourUser.get(i);
				System.out.println(activeUser.toString());
				userFound = true;
				passwordMatch = true;
			}else if(ourUser.get(i).getEmail().equalsIgnoreCase(loginUser.getEmail())){
				System.out.println("User found, incorrect password");
				userFound = true;
			}
		}
		if(!userFound && !passwordMatch){
			System.out.println("No user found by that email");
		}
		return userFound;
	}
	
	public static void userLogOut(){
		activeUser.setUserId(0);
		activeUser.setName(null);
		activeUser.setAgeRangeId(null);
		activeUser.setAgeRange(null);
		activeUser.setGender(null);
		activeUser.setStreetAddress(null);
		activeUser.setCity(null);
		activeUser.setState(null);
		activeUser.setZip(null);
		activeUser.setAboutMe(null);
		activeUser.setEmail(null);
		activeUser.setPassword(null);
		activeUser.setFirstLanguageId(null);
		activeUser.setFirstLanguage(null);
		activeUser.setLearningLanguageId(null);
		activeUser.setLearningLanguage(null);
		activeUser.setSkillLevelId(null);
		activeUser.setSkillLevel(null);
	}
	
	//update info to the database
	public static void userUpdateDB(User users){
		User userToUpdate = new User();
		
		userToUpdate = users;
		userConnToDB();
		
		try{
			PREP_STMT = CONN.prepareStatement(updateToDB);
			
			PREP_STMT.setString(1, userToUpdate.getName());
			PREP_STMT.setString(2, userToUpdate.getAgeRangeId());
			PREP_STMT.setString(3, userToUpdate.getGender());
			PREP_STMT.setString(4, userToUpdate.getStreetAddress());
			PREP_STMT.setString(5, userToUpdate.getCity());
			PREP_STMT.setString(6, userToUpdate.getState());
			PREP_STMT.setString(7, userToUpdate.getZip());
			PREP_STMT.setString(8, userToUpdate.getAboutMe());
			PREP_STMT.setString(9, userToUpdate.getEmail());
			PREP_STMT.setString(10, userToUpdate.getPassword());
			PREP_STMT.setString(11, userToUpdate.getFirstLanguageId());
			PREP_STMT.setString(12, userToUpdate.getLearningLanguageId());
			PREP_STMT.setString(13, userToUpdate.getSkillLevelId());
			PREP_STMT.setString(14, String.valueOf(userToUpdate.getUserId()));
			
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//delete from database on ID
	public static void userDeleteFromDB(int id){
		
		userConnToDB();
		try{
			PREP_STMT = CONN.prepareStatement(deleteDB);
					
			PREP_STMT.setInt(1, id);
			
			PREP_STMT.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}

	}
	
	private static String deleteDB = "DELETE FROM convo_cafe.user WHERE user_id = (?);";
	
	private static String insertToDB = "INSERT INTO convo_cafe.user" 
			+"(name, age_range_id, gender, street_address, city, state, zip, about_me, email, password, first_language_id, learning_language_id, skill_level_id)" 
			+"VALUES" 
			+"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static String updateToDB = "UPDATE convo_cafe.user " 
	+ "SET " 
	+ "name = (?), age_range_id = (?), gender = (?), street_address = (?), city = (?), state = (?), zip = (?), about_me = (?), email = (?), password = (?), first_language_id = (?), learning_language_id = (?), skill_level_id = (?) " 
	+ "WHERE user_id = (?)";
	
	private static String searchStmt(String firstLanguageId, String learningLanguageId){
		
		return "SELECT "
		+ "user.user_id, user.name, user.age_range_id, age.age_range, user.gender, user.street_address, user.city, user.state, user.zip, user.about_me, user.email, user.password, user.first_language_id, first_language.language as first_language, user.learning_language_id, learning_language.language as learning_language, user.skill_level_id, skill_level.skill_level "
		+ "FROM convo_cafe.user "
		+ "JOIN convo_cafe.age ON (user.age_range_id = age.age_id) "
		+ "JOIN convo_cafe.first_language ON (user.first_language_id = first_language.language_id) "
		+ "JOIN convo_cafe.learning_language ON (user.learning_language_id = learning_language.language_id) " 
		+ "JOIN convo_cafe.skill_level ON (user.skill_level_id = skill_level.skill_level_id) "
		+ "WHERE first_language_id = '" + firstLanguageId + "' AND learning_language_id = '" + learningLanguageId + "';";
	}
	
}
package com.convo_cafe.application;

import com.convo_cafe.dao.RestaurantDAO;
import com.convo_cafe.dao.UserDAO;
import com.convo_cafe.objects.Restaurant;
import com.convo_cafe.objects.User;

public class AppStarter {

	public static void main(String[] args) {
		/*User Tests*/
		//UserDAO.userReadFromDB();
		//boolean test = UserDAO.searchForUsers("1", "2");
		//System.out.println(test);
		User testUser = makeUser();
		User testUser2 = makeUser2();
		//System.out.println(testUser.toString());
		//UserDAO.userWriteToDB(testUser);
		//System.out.println(UserDAO.passOrFail);
		//boolean test2 = UserDAO.userLoginSearch(testUser);
		//System.out.println(test2);
		//UserDAO.userLogOut();
		//System.out.println(UserDAO.activeUser.toString());
		//UserDAO.userUpdateDB(testUser2);
		//UserDAO.userDeleteFromDB(testUser2.getUserId());
		//UserDAO.userDeleteFromDB(testUser2.getUserId());
		
		/*Restaurant Tests*/
		//RestaurantDAO.restaurantReadFromDB();
		//boolean rtest = RestaurantDAO.searchForRestaurant("2");
		//System.out.println(rtest);
		Restaurant testRest = makeRestaurant();
		Restaurant testRest2 = makeRestaurant2();
		//RestaurantDAO.restaurantWriteToDB(testRest);
		//boolean rtest2 = RestaurantDAO.restLoginSearch(testRest);
		//System.out.println(rtest2);
		//System.out.println(RestaurantDAO.activeRest.toString());
		//RestaurantDAO.restLogOut();
		//System.out.println(RestaurantDAO.activeRest.toString());
		//RestaurantDAO.restaurantUpdateDB(testRest2);
		//RestaurantDAO.restaurantDeleteFromDB(testUser2.getUserId());
	}//main()
	
	public static User makeUser(){
		User testUser = new User();
		
		testUser.setName("Kyle West");
		testUser.setAgeRangeId("2");
		testUser.setGender("Male");
		testUser.setStreetAddress("8414 Roanoke Rd.");
		testUser.setCity("Stevensville");
		testUser.setState("MD");
		testUser.setZip("21666");
		testUser.setAboutMe("about me test");
		testUser.setEmail("kyle@west.com");
		testUser.setPassword("IronYard");
		testUser.setFirstLanguageId("1");
		testUser.setLearningLanguageId("5");
		testUser.setSkillLevelId("1");
		
		return testUser;
	}//makeUser()
	
	public static User makeUser2(){
		User testUser = new User();
		
		testUser.setUserId(2);
		testUser.setName("Graham Burgess");
		testUser.setAgeRangeId("3");
		testUser.setGender("Male");
		testUser.setStreetAddress("1 Liberty Rd.");
		testUser.setCity("Edgewater");
		testUser.setState("MD");
		testUser.setZip("21037");
		testUser.setAboutMe("about me test");
		testUser.setEmail("graham@medical.com");
		testUser.setPassword("IronYard2");
		testUser.setFirstLanguageId("1");
		testUser.setLearningLanguageId("6");
		testUser.setSkillLevelId("2");
		
		return testUser;
	}//makeUser2()
	
	public static Restaurant makeRestaurant(){
		Restaurant testRest = new Restaurant();
		
		testRest.setName("Bella Napoli");
		testRest.setStreetAddress("350 Mountain Road");
		testRest.setCity("Pasadena");
		testRest.setState("MD");
		testRest.setZip("21122");
		testRest.setLanguageId("4");
		testRest.setEmail("info@BellaNapoli.com");
		testRest.setPassword("IronYard");
		
		return testRest;
	}//makeRestaurant()
	
	public static Restaurant makeRestaurant2(){
		Restaurant testRest = new Restaurant();
		
		testRest.setRestaurantId(2);
		testRest.setName("TestRest");
		testRest.setStreetAddress("1 Street Road");
		testRest.setCity("Baltimore");
		testRest.setState("MD");
		testRest.setZip("21225");
		testRest.setLanguageId("6");
		testRest.setEmail("info@restaurant.com");
		testRest.setPassword("IronYard1");
		
		return testRest;
	}//makeRestaurant2()

}

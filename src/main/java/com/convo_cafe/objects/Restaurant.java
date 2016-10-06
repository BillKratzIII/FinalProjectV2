package com.convo_cafe.objects;

public class Restaurant {
	/*restaurant variables*/
	private int restaurantId;
	private String name;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String languageId;
	private String language;
	private String email;
	private String password;
	
	/*empty (default) constructor*/
	public Restaurant() {
	}
	
	/*getters and setters below- */
	
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", languageId=" + languageId + ", language="
				+ language + ", email=" + email + ", password=" + password + "]";
	}

	
}//class
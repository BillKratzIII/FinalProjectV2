package com.convo_cafe.objects;

public class User {
	/*user variables*/
	private int userId;
	private String name;
	private String ageRangeId;
	private String ageRange;
	private String gender;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String aboutMe;
	private String email;
	private String password;
	private String firstLanguageId;
	private String firstLanguage;
	private String learningLanguageId;
	private String learningLanguage;
	private String skillLevelId;
	private String skillLevel;
	
	
	/*empty (default) constructor*/
	public User() {
	}
	
	/*getters and setters below- */
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(String ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
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
	public String getFirstLanguageId() {
		return firstLanguageId;
	}
	public void setFirstLanguageId(String firstLanguageId) {
		this.firstLanguageId = firstLanguageId;
	}
	public String getFirstLanguage() {
		return firstLanguage;
	}
	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguage = firstLanguage;
	}
	public String getLearningLanguageId() {
		return learningLanguageId;
	}
	public void setLearningLanguageId(String learningLanguageId) {
		this.learningLanguageId = learningLanguageId;
	}
	public String getLearningLanguage() {
		return learningLanguage;
	}
	public void setLearningLanguage(String learningLanguage) {
		this.learningLanguage = learningLanguage;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}


	public String getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(String skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", ageRangeId=" + ageRangeId + ", ageRange=" + ageRange
				+ ", gender=" + gender + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", aboutMe=" + aboutMe + ", email=" + email + ", password=" + password
				+ ", firstLanguageId=" + firstLanguageId + ", firstLanguage=" + firstLanguage + ", learningLanguageId="
				+ learningLanguageId + ", learningLanguage=" + learningLanguage + ", skillLevelId=" + skillLevelId
				+ ", skillLevel=" + skillLevel + "]";
	}
	
}
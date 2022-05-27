package com.inexture.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "user_details")
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;

	@NotBlank(message = "firstName is required")
	@Size(min = 2, message = "firstName: min size 2")
	@Column(name = "FirstName")
	private String firstName;

	@NotBlank(message = "lastName is required")
	@Size(min = 2, message = "lastName:min size 2")
	@Column(name = "LastName")
	private String lastName;

	@NotBlank(message = "dob is required")
	@Column(name = "Dob")
	private String dob;

	@Column(name = "Role")
	private String role = "User";

	@Transient
	private String secutiryQuestion;

	@NotBlank(message = "mobaileNo is required")
	@Column(name = "mobaileNo")
	private String mobaileNo;

	@NotBlank(message = "gender is required")
	private String gender;

	@NotBlank(message = "answer is required")
	private String answer;

	@NotBlank(message = "language is required")
	private String language;

	@Transient
	private String hobby;

	@NotBlank(message = "email is required")
	@Pattern(regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
	@Column(name = "Email_Address", unique = true)
	private String email;

	// @NotBlank(message = "password is required")
	@Size(min = 8, message = "min size 8")
	private String password;

	@Transient
	private String base64Image;

	@Valid
	@Autowired
	@OneToMany(mappedBy = "userBean", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<UserAddressBean> userAddress;

	@Autowired
	@OneToMany(mappedBy = "userBean", cascade = CascadeType.ALL)
	private List<UserProfileBean> userProfile;

	@Transient
	private List<MultipartFile> file;

	public List<MultipartFile> getFile() {
		return file;
	}

	public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobaileNo() {
		return mobaileNo;
	}

	public void setMobaileNo(String mobaileNo) {
		this.mobaileNo = mobaileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

//	public String getBase64Image() {
//		return base64Image;
//	}
//
//	public void setBase64Image(String base64Image) {
//		this.base64Image = base64Image;
//	}

	public List<UserAddressBean> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddressBean> userAddress) {
		this.userAddress = userAddress;
	}

	public List<UserProfileBean> getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(List<UserProfileBean> userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "UserBean [UserId=" + UserId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", role=" + role + ", secutiryQuestion=" + secutiryQuestion + ", mobaileNo=" + mobaileNo + ", gender="
				+ gender + ", answer=" + answer + ", language=" + language + ", hobby=" + hobby + ", email=" + email
				+ ", password=" + password + ", base64Image=" + base64Image + ", userAddress=" + userAddress
				+ ", userProfile=" + userProfile + "]";
	}
}

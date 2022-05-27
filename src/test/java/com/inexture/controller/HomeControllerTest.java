package com.inexture.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.inexture.filter.LogOutFilter;
import com.inexture.model.UserAddressBean;
import com.inexture.model.UserBean;
import com.inexture.service.UserImpl;


@ExtendWith(MockitoExtension.class) 
@TestInstance(Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserImpl userImpl;
	
	@InjectMocks 
	private HomeController homeController;

	UserBean expectedUser = new UserBean();
	
	@BeforeEach
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/ftl/");
		viewResolver.setSuffix(".ftl");
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).setViewResolvers(viewResolver).build();
	}


	@BeforeAll
	public void user() {

		expectedUser.setUserId(50);
		expectedUser.setFirstName("rajat");
		expectedUser.setLastName("patel");
		expectedUser.setDob("11/20/2000");
		expectedUser.setLanguage("JAVA");
		expectedUser.setEmail("rajt@gmail.com");
		expectedUser.setPassword("123123123");
		expectedUser.setGender("Male");
		expectedUser.setMobaileNo("9909398963");
		expectedUser.setRole("User");
		expectedUser.setAnswer("Hello Rajat");

		List<UserAddressBean> addressList = new ArrayList<UserAddressBean>();
		UserAddressBean address = new UserAddressBean();
		address.setAddressId("12");
		address.setCountry("india");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPinCode("380006");
		address.setAddress("ahmedabad");
		address.setUserBean(expectedUser);

		addressList.add(address);
 
		expectedUser.setUserAddress(addressList);

		List<UserBean> expectedUserList = new ArrayList<UserBean>();
		expectedUserList.add(expectedUser);

		MultipartFile[] file = new MultipartFile[1];
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				"some xml".getBytes());
		file[0] = firstFile;
		expectedUser.setFile(Arrays.asList(file));

	}

	@Test 
	public void homeTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/index")).andExpect(status().is(200));
	}
  
	@Test 
	public void UserHomeTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/UserHome")); 
	}

	@Test
	public void userProfileTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/UserProfile"));
	}

	@Test
	public void registrationTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/registration"));
	}

	@Test
	public void adminProfileTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/AdminProfile"));
	}

	@Test
	public void adminRegistrationTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/adminRegistration"));
	}

	@Test 
	public void resetPasswordTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/ResetPassword"));
	}

	@Test
	public void updateUserControllerTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/updateUserController"));
	}
  
	@Test
	public void loginTest() throws Exception {

		UserBean user = new UserBean();
		user.setUserId(55);

		String email = "raj@gmail.com";
		String password = "123123123";

		when(userImpl.getRole(email, password)).thenReturn("Admin");
		when(userImpl.getEmployeeByEmail(email)).thenReturn(user);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/login").param("email", email).param("password", password))
				.andExpect(status().is(302));
	}

	@Test
	public void userloginTest() throws Exception {

		UserBean user = new UserBean();
		user.setUserId(55);

		String email = "raj@gmail.com";
		String password = "123123123";

		when(userImpl.getRole(email, password)).thenReturn("User");
		when(userImpl.getEmployeeByEmail(email)).thenReturn(user);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/login").param("email", email).param("password", password))
				.andExpect(status().is(302));
	}

	@Test
	public void invalideLoginTest() throws Exception {

		UserBean user = new UserBean();
		user.setUserId(55);

		String email = "raj@gmail.com";
		String password = "123123123";

		when(userImpl.getRole(email, password)).thenReturn("Invalide");

		this.mockMvc.perform(MockMvcRequestBuilders.get("/login").param("email", email).param("password", password))
				.andExpect(status().is(200));
	}

	@Test
	public void checkEmailIsPresentTest() throws Exception {
		String email = "raj@gmail.com";

		when(userImpl.getEmailIsPresent(email)).thenReturn(true);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/CheckEmailIsPresent").param("email", email))
				.andExpect(status().is(200));
	}
	
	@Test
	public void notEmailIsPresentTest() throws Exception {
		String email = "raj@gmail.com";

		when(userImpl.getEmailIsPresent(email)).thenReturn(false);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/CheckEmailIsPresent").param("email", email))
				.andExpect(status().is(200));
	}

	@Test
	public void adminHomeTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/AdminHome").sessionAttr("role", "User"))
				.andExpect(status().is(200));
	}

	@Test
	public void adminOrUserHomeTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/AdminHome").sessionAttr("role", "Admin"))
				.andExpect(status().is(200));
	}

	@Test
	public void getAllUserTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getAllUser")).andExpect(status().is(200));
	}

	@Test
	public void deleteUserTest() throws Exception {
		
		when(userImpl.deleteUserById(0)).thenReturn(0);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/DeleteUser").param("UserId", "50"))
				.andExpect(status().is(200));
	}

	@Test
	public void notDeleteUserTest() throws Exception {
		when(userImpl.deleteUserById(1)).thenThrow(new RuntimeException());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/DeleteUser").param("UserId", "1"))
				.andExpect(status().is(200));
	}

	@Test
	public void logoutTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/logout").sessionAttr("session", "false"))
				.andExpect(status().is(302));
	}

	@Test
	public void logoutExceptionTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/logout").sessionAttr("session", "null"))
				.andExpect(status().is(302));
	}

	@Test
	public void getAllUserAddressTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/GetAllUserAddress").param("userId", "50"))
				.andExpect(status().isOk());
	}

	@Test
	public void getAllUserDetailsTest() throws Exception {
		UserBean user = new UserBean();
		user.setUserId(68);
		String email = "raj@gmail.com";
		when(userImpl.getEmployeeByEmail(email)).thenReturn(user);
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/GetAllUserDetails").param("email", "raj@gmail.com").param("userId", "50"))
				.andExpect(status().is(200));
	}
  
	@Test 
	public void deleteUserProfileTest() throws Exception {

		when(userImpl.deleteImage(12)).thenReturn(1);

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.get("/DeleteUserProfile").param("profileId", "12").param("userId", "50"))
				.andExpect(status().is(200));
	}

	@Test
	public void notDeleteUserProfileTest() throws Exception {

		when(userImpl.deleteImage(12)).thenReturn(0);

		this.mockMvc
				.perform(
						MockMvcRequestBuilders.get("/DeleteUserProfile").param("profileId", "12").param("userId", "50"))
				.andExpect(status().is(200));
	}

	@Test
	public void forgotPasswordTest() throws Exception {
		String email = "raj@gmail.com";
		String password = "123123123";
		String answer = "Hello";

		when(userImpl.checkAns(email, answer)).thenReturn(10);
		when(userImpl.updatePassword(10, password)).thenReturn(1);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/ForgotPassword").param("email", "raj@gmail.com")
				.param("answer", "Hello").param("password", "123123123")).andExpect(status().is(200));
	}
 
	@Test
	public void forgotPasswordExceptionTest() throws Exception {
		String email = "raj@gmail.com";
		String answer = "Hello";

		when(userImpl.checkAns(email, answer)).thenReturn(0);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/ForgotPassword").param("email", "raj@gmail.com")
				.param("answer", "Hello").param("password", "123123123")).andExpect(status().is(200));
	}

	@Test
	public void forgotPasswordInnerExceptionTest() throws Exception {

		int userId = 50;
		String email = "raj@gmail.com";
		String password = "123123123";
		String answer = "Hello";

		when(userImpl.checkAns(email, answer)).thenReturn(1);
		when(userImpl.updatePassword(userId, password)).thenReturn(0);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/ForgotPassword").param("email", "raj@gmail.com")
				.param("answer", "Hello").param("password", "123123123")).andExpect(status().is(200));
	}

	@Test
	public void submitFormWithErrorsTest() throws Exception {
		UserBean user = new UserBean();
		user.setFirstName("rajat");
		MultipartFile[] file = new MultipartFile[1];
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				"some xml".getBytes());
		file[0] = firstFile;
 
		byte[] byteArr = file[0].getBytes();
 
		this.mockMvc
				.perform(MockMvcRequestBuilders.multipart("/submitform").file("userProfile.profiles[]", byteArr)
						.accept(MediaType.MULTIPART_FORM_DATA_VALUE).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().is(200));
	}

	@Test
	public void submitFormTest() throws Exception {

		when(userImpl.saveUser(expectedUser)).thenReturn(1);

		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/submitform")
				.file("userProfile.profiles[]", expectedUser.getFile().get(0).getBytes())
				.flashAttr("User", expectedUser).accept(MediaType.MULTIPART_FORM_DATA_VALUE)
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)).andExpect(status().is(200));
	}

	@Test
	public void submitFormWithNotSaveTest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/submitform")
				.file("userProfile.profiles[]", expectedUser.getFile().get(0).getBytes())
				.flashAttr("User", expectedUser).accept(MediaType.MULTIPART_FORM_DATA_VALUE)
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)).andExpect(status().is(200));
	}

	@Test
	public void updateFormWithErrorsTest() throws Exception {
		UserBean user = new UserBean();
		user.setFirstName("rajat");
		MultipartFile[] file = new MultipartFile[1];
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				"some xml".getBytes());
		file[0] = firstFile;

		byte[] byteArr = file[0].getBytes();

		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/UpdateUserDetails")
				.file("userProfile.profiles[]", byteArr).sessionAttr("User", user)
				.accept(MediaType.MULTIPART_FORM_DATA_VALUE).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().is(200));
	}
 
	@Test
	public void updateFormTest() throws Exception {
		expectedUser.setRole("Admin");
		when(userImpl.updateEmployeeDetails(expectedUser)).thenReturn(1);
		when(userImpl.getEmployeeByEmail("raj@gmail.com")).thenReturn(expectedUser);

		this.mockMvc
				.perform(MockMvcRequestBuilders.multipart("/UpdateUserDetails")
						.file("userProfile.profiles[]", expectedUser.getFile().get(0).getBytes())
						.flashAttr("User", expectedUser).sessionAttr("User", expectedUser)
						.accept(MediaType.MULTIPART_FORM_DATA_VALUE).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
				.andExpect(status().is(200));
	}
	
	@Test
	public void notUpdateUserTest() throws Exception {
		UserBean user = new UserBean();
		user.setFirstName("rajat");
		user.setLastName("patel");
		user.setDob("11/20/2000");
		user.setLanguage("JAVA");
		user.setEmail("raj@gmail.com");
		user.setPassword("123123123");
		user.setGender("Male");
		user.setMobaileNo("9909398963");
		user.setRole("User");
		user.setAnswer("Hello Rajat");

		UserAddressBean userAddress = new UserAddressBean();
		userAddress.setAddressId("");
		userAddress.setCountry("india");
		userAddress.setState("guharat");
		userAddress.setCity("Ahmedabad");
		userAddress.setPinCode("380006");
		userAddress.setAddress("Ahmedabad");
		userAddress.setUserBean(user);

		List<UserAddressBean> userAddressList = new ArrayList<UserAddressBean>();
		userAddressList.add(userAddress);

		user.setUserAddress(userAddressList);

		MultipartFile[] file = new MultipartFile[1];
		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
				"some xml".getBytes());
		file[0] = firstFile;

		byte[] byteArr = file[0].getBytes();
		 
		when(userImpl.getEmployeeByEmail("raj@gmail.com")).thenReturn(user);

		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/UpdateUserDetails").file("userProfile.profiles[]", byteArr).sessionAttr("User", user)
				.flashAttr("User", user).accept(MediaType.MULTIPART_FORM_DATA_VALUE)
				.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)).andExpect(status().is(200));
	}
	
	@Test
	public void filterTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).addFilters(new LogOutFilter()).build();

		this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().is(302));
	}

	@Test
	public void filterSessionTest() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).addFilters(new LogOutFilter()).build();

		this.mockMvc.perform(MockMvcRequestBuilders.get("/updateUserController").sessionAttr("session", "NotNull")).andExpect(status().is(200));
	}

}

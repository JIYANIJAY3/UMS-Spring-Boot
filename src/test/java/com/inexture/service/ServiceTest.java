package com.inexture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.inexture.dao.UserDaoInterface;
import com.inexture.dao.UserProfileDao;
import com.inexture.model.UserAddressBean;
import com.inexture.model.UserBean;
import com.inexture.model.UserProfileBean;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ServiceTest {
 
	@InjectMocks
	private UserInterface userService;

	@Mock
	private UserDaoInterface userDaoImpl;

	@Mock
	private UserProfileDao userProfileDaoImpl;

	@BeforeAll
	void init() {
		this.userService = new UserImpl();
	}

	UserBean expectedUser = new UserBean();

	@BeforeAll
	public void user() {

		expectedUser.setUserId(1);
		expectedUser.setFirstName("jay");

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
	public void getRoleTest() { 
		String email = "raj@gmail.com";
		String password = "123123123";

		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword("$2a$10$x4Adrr/08KJOSA6o4fx4weH0lMPWukqMREfhotEc2/rnGIaBzwKri");
 
		when(userDaoImpl.findByEmail(email)).thenReturn(user);

		String role = userService.getRole(email, password);
		assertNotNull(role);
		
	}
	
	@Test
	public void getNotValideRoleTest() { 
		String email = "raj@gmail.com";
		String password = "123123123";

		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword("$2a$10$x4Adrr/08KJfOSA6o4fx4weH0lMPWukqMREfhotEc2/rnGIaBzwKri");
 
		when(userDaoImpl.findByEmail(email)).thenReturn(user);

		String role = userService.getRole(email, password);
		assertNotNull(role);
		
	}

	@Test 
	public void getEmailIsPresentTest() {
		String email = "raj@gmail.com";

		when(userDaoImpl.existsByEmail(email)).thenReturn(true);

		boolean status = userService.getEmailIsPresent(email);
		assertTrue(status);
	}

	@Test
	public void getEmployeeByEmailTest() {
		String email = "raj@gmail.com";

		UserBean expectedUser = new UserBean();
		expectedUser.setUserId(1);
		expectedUser.setFirstName("raj");

		when(userDaoImpl.findByEmail(email)).thenReturn(expectedUser);

		UserBean user = userService.getEmployeeByEmail(email);
		assertNotNull(user);

	}
  
	@Test
	public void deleteUserByIdTest() {
		int userId = 63;

		userDaoImpl.deleteById(userId);
		int status = userService.deleteUserById(userId);
		assertEquals(1, status); 
	}

	@Test
	public void getEmployeeByIdTest() {
		int userId = 50;

		UserBean expectedUser = new UserBean();

		expectedUser.setUserId(1);
		expectedUser.setFirstName("jay");

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

		System.out.println(expectedUser);

		when(userDaoImpl.findById(userId)).thenReturn(expectedUser);

		String user1 = userService.getEmployeeById(userId);
		assertNotNull(user1);
	}

	@Test
	public void deleteImageTest() {
		int imageId = 50;

		userProfileDaoImpl.deleteById(imageId);

		int status = userService.deleteImage(imageId);
		assertEquals(1, status);
	}

	@Test
	public void checkAnsTest() {
		String email = "raj@gmail.com";
		String answer = "Hello";

		UserBean user = new UserBean();
		user.setUserId(2);
		user.setAnswer(answer);
		user.setEmail(email);

		when(userDaoImpl.findByEmailAndAnswer(email, answer)).thenReturn(user);
		int status = userService.checkAns(email, answer);
		assertEquals(2, status);
	}

	@Test
	public void saveUserTest() {

		userDaoImpl.save(expectedUser);

		int status = userService.saveUser(expectedUser);
		assertEquals(0, status);

	}

	@Test
	public void saveUserExceptionTest() throws Exception {
		UserBean user = new UserBean();

		when(userDaoImpl.save(user)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, () -> userService.saveUser(user));
	}

	@Test
	public void getUserImgTest() {
		byte[] anyBytes = new byte[] {0,1,0,1,0};
		UserBean user = new UserBean();
		user.setUserId(1);
		UserProfileBean profileBean = new UserProfileBean();
		List<UserProfileBean> profileList = new ArrayList<UserProfileBean>();
		profileBean.setImageId(1);
		profileBean.setUserId(50);
		profileBean.setUserBean(user);
		profileBean.setProfiles(anyBytes);
		user.setUserProfile(profileList);
		profileList.add(profileBean);

		user.setUserProfile(profileList);
 
		when(userDaoImpl.findById(50)).thenReturn(user);

		List<UserProfileBean> actualProfileList = userService.getUserImg(50);
		assertNotNull(actualProfileList);
	}

	@Test
	public void updateEmployeeDetailsTest() {

		when(userDaoImpl.findById(expectedUser.getUserId())).thenReturn(expectedUser);
		userDaoImpl.save(expectedUser);
		int status = userService.updateEmployeeDetails(expectedUser);
		assertEquals(1, status);
	}

	@Test
	public void getAllEmployeeTest() {
		UserBean expectedUser = new UserBean();

		expectedUser.setUserId(1);
		expectedUser.setFirstName("jay");

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

		when(userDaoImpl.findAll()).thenReturn(expectedUserList);
		String result = userService.getAllUser();
		assertNotNull(result);
	}


	@Test
	public void updateUserExceptionTest() throws Exception {
		UserBean user = new UserBean();
		when(userDaoImpl.save(user)).thenThrow(new RuntimeException());
		assertThrows(RuntimeException.class, ()-> userService.updateEmployeeDetails(user));
	}

	@Test
	public void updatePasswordTest() {
		int userId = 50;
		String password = "123123123";

		userDaoImpl.updatePassword(userId, password);
		int status = userService.updatePassword(userId, password);
		assertEquals(1, status);
	}
}

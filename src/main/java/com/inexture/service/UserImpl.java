package com.inexture.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inexture.dao.UserDaoInterface;
import com.inexture.dao.UserProfileDao;
import com.inexture.model.UserAddressBean;
import com.inexture.model.UserBean;
import com.inexture.model.UserProfileBean;

@Service
public class UserImpl implements UserInterface {

	final static Logger log = Logger.getLogger("debuglog");

	@Autowired
	private UserDaoInterface userDaoImpl;

	@Autowired
	private UserProfileDao userProfileDaoImpl;

	@Override
	public String getRole(String email, String password) {

		UserBean theUser = this.userDaoImpl.findByEmail(email);
		if (BCrypt.checkpw(password, theUser.getPassword())) {
			return theUser.getRole();
		} else {
			return ""; 
		}
	}
 
	@Override
	public String getAllUser() {

		List<UserBean> userList = this.userDaoImpl.findAll();

		List<UserBean> userlist = new ArrayList<UserBean>();

		for (UserBean ub : userList) {
			if (ub.getRole().equals("User")) {
				UserBean ubean = new UserBean();
				ubean.setUserId(ub.getUserId());
				ubean.setFirstName(ub.getFirstName());
				ubean.setLastName(ub.getLastName());
				ubean.setDob(ub.getDob());
				ubean.setMobaileNo(ub.getMobaileNo());
				ubean.setGender(ub.getGender());
				ubean.setLanguage(ub.getLanguage());
				ubean.setEmail(ub.getEmail());
				userlist.add(ubean);
			} else {
				continue;
			}
		}

		// Convert list To->JSON DATA
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject json = new JsonObject();

		json.add("data", gson.toJsonTree(userlist));

		log.info(json);

		return json.toString();
	}

	@Override
	public boolean getEmailIsPresent(String email) {
		return this.userDaoImpl.existsByEmail(email);
	}

	@Override
	public int saveUser(UserBean user) {
		List<UserProfileBean> list = new ArrayList<UserProfileBean>();
		try {
			for (MultipartFile getfile : user.getFile()) {
				UserProfileBean bean = new UserProfileBean();

				byte[] bytefile = getfile.getBytes();

				bean.setProfiles(bytefile);
				bean.setUserBean(user);
				list.add(bean);
			}
		} catch (Exception e) {
			log.info(e);
		}

		String encrptPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encrptPassword);
		user.setUserProfile(list);

		for (UserAddressBean useraddress : user.getUserAddress()) {
			useraddress.setUserBean(user);
		}
		UserBean getUser = (UserBean) userDaoImpl.save(user);

		return getUser != null ? 1 : 0;
	}

	@Override
	public UserBean getEmployeeByEmail(String email) {
		return this.userDaoImpl.findByEmail(email);
	}

	@Override
	public List<UserProfileBean> getUserImg(int userid) {

		UserBean theUser = this.userDaoImpl.findById(userid);

		List<UserProfileBean> userProfileList = theUser.getUserProfile();
		List<UserProfileBean> list = new ArrayList<UserProfileBean>();

		for (UserProfileBean user : userProfileList) {
			UserProfileBean userProfile = new UserProfileBean();
			userProfile.setBase64Image(Base64.getEncoder().encodeToString(user.getProfiles()));
			userProfile.setImageId(user.getImageId());
			list.add(userProfile);
		}
		return list;
	}

	@Override
	public int deleteUserById(int UserId) {
		this.userDaoImpl.deleteById(UserId);
		return 1;
	}

	@Override
	public String getEmployeeById(int UserId) {

		UserBean user = this.userDaoImpl.findById(UserId);
		List<UserAddressBean> list = new ArrayList<UserAddressBean>();

		for (UserAddressBean userlist : user.getUserAddress()) {
			UserAddressBean address = new UserAddressBean();
			address.setAddressId(String.valueOf(userlist.getAddressId()));
			address.setAddress(userlist.getAddress());
			address.setCity(userlist.getCity());
			address.setPinCode(userlist.getPinCode());
			address.setState(userlist.getState());
			address.setCountry(userlist.getCountry());
			address.setUserId(userlist.getUserId());
			list.add(address);
		}
		// Convert List Data To -> JSON Data
		Gson gson = new Gson();

		return gson.toJson(list);
	}

	@Override
	public int updateEmployeeDetails(UserBean user) {
 
		UserBean oldUser = userDaoImpl.findById(user.getUserId());

		List<UserProfileBean> list = new ArrayList<UserProfileBean>();

		try {
			for (MultipartFile getfile : user.getFile()) {
				if (getfile.getSize() > 0) {
					UserProfileBean bean = new UserProfileBean();

					byte[] bytefile = getfile.getBytes();

					bean.setProfiles(bytefile);
					bean.setUserBean(user);
					list.add(bean);
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			log.info(e);
		}

		user.getUserAddress().stream().forEach(address -> {
			address.setUserBean(user);
		});
		user.setUserProfile(list);
		user.setPassword(oldUser.getPassword());

		this.userDaoImpl.save(user);

		return 1;
	}

	@Override
	public int deleteImage(int imageId) {
		this.userProfileDaoImpl.deleteById(imageId);
		return 1;
	}

	@Override
	public int checkAns(String email, String answer) {
		UserBean user = this.userDaoImpl.findByEmailAndAnswer(email, answer);
		return user != null ? user.getUserId() : 0;
	}

	@Override
	public int updatePassword(int UserId, String Password) {
		String password = BCrypt.hashpw(Password, BCrypt.gensalt());
		this.userDaoImpl.updatePassword(UserId, password);
		return 1;
	}

}

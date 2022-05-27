package com.inexture.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "user_profile_details")
public class UserProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private int UserId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ImageId;

	@Transient
	private String base64Image; 

	@Column(name = "Profile", columnDefinition = "BLOB")
	@Lob
	private byte[] profiles;
	
	@Autowired
	@ManyToOne
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public byte[] getProfiles() {
//		return Image;
		return Arrays.copyOf(profiles, profiles.length);
	}

	public void setProfiles(byte[] profiles) {
		this.profiles = Arrays.copyOf(profiles, profiles.length);
	}
}

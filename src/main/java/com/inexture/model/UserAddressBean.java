package com.inexture.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "user_address_details")
public class UserAddressBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private int userId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@NotBlank(message = "country is required")
	@Size(min = 2, message = "country: min size 2")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "only character allowed")
	private String country;

	@NotBlank(message = "state is required")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "state: only character allowed")
	private String state;

	@NotBlank(message = "city is required")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "city: only character allowed")
	private String city;

	@NotBlank(message = "pincode is required")
	@Pattern(regexp = "^[0-9]*$", message = "pincode: only number allowed")
	@Size(min = 6, max = 6, message = "pincode: enter only 6 digit")
	private String pinCode;

	@NotBlank(message = "address is required")
	private String address;

	@Autowired
	@ManyToOne
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {

		if (addressId.isEmpty()) {
			this.addressId = 0;
		} else {
			this.addressId = Integer.parseInt(addressId);
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

package com.onepointpropertybackend.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {

	private int id;
	@NotNull
	private String firstname;
	private String lastname;
	private String emailid;
	private String contact;
	private String password;
	private String address;
	private String city;
	private String state;
	private int role;
	private String profileimage;
	private boolean status;
}
//	@NotNull
	//@Pattern(regexp = "(user|employee|admin)")
//	private String role;
//
//	public Profile() {
//	}
//
//	public Profile(int profileId, String username, String firstname, String lastname, String email, String contact,
//			Date dob, String address, String city, String state, String pincode, boolean status, String role) {
//		super();
//		this.profileId = profileId;
//		this.username = username;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.contact = contact;
//		this.dob = dob;
//		this.address = address;
//		this.city = city;
//		this.state = state;
//		this.pincode = pincode;
//		this.status = status;
//		this.role = role;
//	}
//
//	public Profile(ProfileWithPassword profileWithPassword) {
//		super();
//		profileId = profileWithPassword.getProfileId();
//		username = profileWithPassword.getUsername();
//		firstname = profileWithPassword.getFirstname();
//		lastname = profileWithPassword.getLastname();
//		email = profileWithPassword.getEmail();
//		contact = profileWithPassword.getContact();
//		dob = profileWithPassword.getDob();
//		address = profileWithPassword.getAddress();
//		city = profileWithPassword.getCity();
//		state = profileWithPassword.getState();
//		pincode = profileWithPassword.getPincode();
//		status = profileWithPassword.isStatus();
//		role = profileWithPassword.getRole();
//	}
//
//	public int getProfileId() {
//		return profileId;
//	}
//
//	public void setProfileId(int profileId) {
//		this.profileId = profileId;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getContact() {
//		return contact;
//	}
//
//	public void setContact(String contact) {
//		this.contact = contact;
//	}
//
//	public Date getDob() {
//		return dob;
//	}
//
//	public void setDob(Date dob) {
//		this.dob = dob;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getPincode() {
//		return pincode;
//	}
//
//	public void setPincode(String pincode) {
//		this.pincode = pincode;
//	}
//
//	public boolean isStatus() {
//		return status;
//	}
//
//	public void setStatus(boolean status) {
//		this.status = status;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
//		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
//		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
//		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
//		result = prime * result + profileId;
//		result = prime * result + ((role == null) ? 0 : role.hashCode());
//		result = prime * result + ((state == null) ? 0 : state.hashCode());
//		result = prime * result + (status ? 1231 : 1237);
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Profile other = (Profile) obj;
//		if (profileId != other.profileId)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "Profile [profileId=" + profileId + ", username=" + username + ", firstname=" + firstname + ", lastname="
//				+ lastname + ", email=" + email + ", contact=" + contact + ", dob=" + dob + ", address=" + address
//				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", status=" + status + ", role="
//				+ role + "]";
//	}



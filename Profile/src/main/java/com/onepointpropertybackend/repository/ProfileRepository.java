package com.onepointpropertybackend.repository;


import com.onepointpropertybackend.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository {
	public List<Profile> findAllProfiles();

	Profile findProfile(String emailid);

	void addProfile(Profile addProfileRequest);



	void updateProfile(int id, Profile updateProfile);

	void updateProfile(String emailId, String password);
}

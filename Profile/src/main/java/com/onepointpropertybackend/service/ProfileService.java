package com.onepointpropertybackend.service;


import com.onepointpropertybackend.model.Profile;
import com.onepointpropertybackend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private String DEFAULT_PASSWORD = "testing1";
	@Autowired
	@Qualifier("profileRepo")
	ProfileRepository profileRepository;

	public List<Profile> findAllProfiles() {
		List<Profile> list = profileRepository.findAllProfiles();
		if (!list.isEmpty())
			return list;
		return  null;
	}

	public Profile findProfile(String emailid) {
		return profileRepository.findProfile(emailid);
	}

	public void addProfile(Profile addProfileRequest) {
		profileRepository.addProfile(addProfileRequest);
	}

	public void updateProfile(int id, Profile updateProfile) {
		profileRepository.updateProfile(id,updateProfile);
	}

    public void forgotPassword(String emailId) {
        profileRepository.updateProfile(emailId, DEFAULT_PASSWORD);
    }

}

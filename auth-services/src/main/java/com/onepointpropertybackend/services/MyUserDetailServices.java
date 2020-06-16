package com.onepointpropertybackend.services;

import com.onepointpropertybackend.models.ProfileModel;
import com.onepointpropertybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyUserDetailServices implements UserDetailsService {


	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ProfileModel profiles=userRepository.getProfileByUsername(username);
		if(profiles==null){
			throw new UsernameNotFoundException("Invalid Username or Password"+profiles.getUsername());
		}
		return new User(profiles.getUsername(),profiles.getPassword(), Arrays.asList(new SimpleGrantedAuthority(getRole(profiles.getRole()))));
	}
    public String getRole(int roleId){
		if(roleId==1){
			return "Admin";
		}else if(roleId==2){
			return "Saleperson";
		}else{
			return "Agent";
		}

	}


}








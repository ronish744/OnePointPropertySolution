package com.onepointpropertybackend.repository;

import com.onepointpropertybackend.models.ProfileModel;


public interface UserRepository  {



    public ProfileModel getProfileByUsername(String username);










}

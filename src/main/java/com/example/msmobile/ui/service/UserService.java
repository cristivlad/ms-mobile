package com.example.msmobile.ui.service;

import com.example.msmobile.ui.model.request.UserDetailsRequestModel;
import com.example.msmobile.ui.model.response.UserRest;

public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetails);
}

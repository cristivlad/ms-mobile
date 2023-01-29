package com.example.msmobile.ui.service;

import com.example.msmobile.shared.Utils;
import com.example.msmobile.ui.model.request.UserDetailsRequestModel;
import com.example.msmobile.ui.model.response.UserRest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Utils utils;

    Map<String, UserRest> users = new HashMap<>();

    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnedUser = new UserRest();
        returnedUser.setFirstName(userDetails.getFirstName());
        returnedUser.setLastName(userDetails.getLastName());
        returnedUser.setEmail(userDetails.getEmail());
        returnedUser.setUserId(utils.generateUserId());

        if (CollectionUtils.isEmpty(users)) users.put(returnedUser.getUserId(), returnedUser);

        return returnedUser;
    }
}

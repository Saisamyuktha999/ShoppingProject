package com.shopping.service;


import com.shopping.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUserByName(String userName) {
        return restTemplate.getForObject("http://localhost:8090/users/name/" + userName, User.class);
    }

    public void updateUserPasswordById(int id, String password) {
        User user = new User(id, password);
        restTemplate.put("http://localhost:8090/users/changePassword/", user, User.class);
    }
    public User addUser(User user) {
        return restTemplate.postForObject("http://localhost:8090/users/", user, User.class);
    }
}


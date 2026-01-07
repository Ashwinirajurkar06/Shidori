package com.shidori.service;

import java.util.Date;
import java.util.Random;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shidori.model.Users;
import com.shidori.repo.UserRepo;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepo userrepo;

    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public String generateTempPassword(int length) throws BadRequestException {
        try {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "0123456789"
                    + "!@#$%&*";
            StringBuilder password = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                password.append(characters.charAt(random.nextInt(characters.length())));
            }
            return password.toString();
        } catch (Exception e) {
            throw new BadRequestException("Failed to generate temporary password: " + e.getMessage());
        }
    }

	@Transactional
	public Users SaveUser(Users user) throws BadRequestException {
		// TODO Auto-generated method stub
		String password=generateTempPassword(10);
		user.setPasswordHash(passwordEncoder.encode(password));
		
		
		return userrepo.save(user);
	}

}

package com.shidori.service;

import java.security.SecureRandom;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.shidori.config.ApiResponse;
import com.shidori.config.ValidationException;
import com.shidori.model.Users;
import com.shidori.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	private static final String PASSWORD_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";

	public ApiResponse<Object> registerUser(Users user) {

		Map<String, String> errors = new HashMap<>();

		// Email validation
		if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			errors.put("email", "Invalid email format.");
		}

		// Phone validation
		if (!user.getPhone().matches("\\d{10}")) {
			errors.put("phone", "Phone number must be 10 digits.");
		}

		// Throw validation exception if any error exists
		if (!errors.isEmpty()) {
			throw new ValidationException(errors);
		}

		// Already exists check
		if (userrepo.existsByEmail(user.getEmail())) {
			return new ApiResponse<>(200, false, "User already registered. Please log in using your email address.",
					null);
		}

		if (userrepo.existsByPhone(user.getPhone())) {
			return new ApiResponse<>(200, false, "User already registered. Please log in using your email address.",
					null);
		}

		// Generate random password
		String rawPassword = generateRandomPassword(10);
		String encryptedPassword = passwordEncoder.encode(rawPassword);

		user.setPasswordHash(encryptedPassword);
		user.setHasUpdatedPassword(false);
		user.setFirstLogin(true);
		user.setStatus(Users.Status.active);

		userrepo.save(user);

		// Send password email
		emailService.sendPasswordEmail(user.getEmail(), user.getName(), rawPassword);

		// (Later send via email/SMS)
		System.out.println("Generated Password: " + rawPassword);

		return new ApiResponse<>(201, true, "User registered successfully.", null);

	}

	// ---------------- Helper ----------------

	private String generateRandomPassword(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(PASSWORD_CHARS.length());
			password.append(PASSWORD_CHARS.charAt(index));
		}

		return password.toString();
	}
}

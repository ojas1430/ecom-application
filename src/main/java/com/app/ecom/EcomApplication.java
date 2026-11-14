package com.app.ecom;

import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}

	@Service
	@RequiredArgsConstructor
	public static class UserService {
		private final UserRepository userRepository;
	 //   public List<User> userList = new ArrayList<User>();

		public List<User> fetchAllUsers(){

			return userRepository.findAll();
		}

		public void addUser( User user){
			userRepository.save(user);
		}

		public Optional<User> fetchUser(Long id) {
			return userRepository.findById(id);
		}

		public boolean updateUser(Long id, User updateUser){
			return userRepository.findById(id)
					.map(existingUser -> {
						existingUser.setFirstName(updateUser.getFirstName());
						existingUser.setLastName(updateUser.getLastName());
						userRepository.save(existingUser);
						return true;
					}).orElse(false);
		}
	}
}

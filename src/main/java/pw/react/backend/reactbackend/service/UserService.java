package pw.react.backend.reactbackend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import pw.react.backend.reactbackend.entity.User;

public interface UserService {
	List<User> findAll();

	ResponseEntity insertUser(User usr);

	void updateUser(User usr);

	void executeUpdateUser(User usr);

	void deleteUser(User usr);
	
}

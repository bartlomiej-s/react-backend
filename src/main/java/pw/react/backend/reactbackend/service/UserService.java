package pw.react.backend.reactbackend.service;


import org.springframework.http.ResponseEntity;
import pw.react.backend.reactbackend.entity.User;

public interface UserService {
	Object findUser(String login);

	ResponseEntity insertUser(User usr);

	ResponseEntity updateUser(User usr);

	void executeUpdateUser(User usr);

	ResponseEntity deleteUser(String login);
	
}

package pw.react.backend.reactbackend.service;

import pw.react.backend.reactbackend.entity.User;

public interface UserExistsService {

	boolean exists(User usr);
	
}

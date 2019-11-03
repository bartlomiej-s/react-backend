package pw.react.backend.reactbackend.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.dao.UserDao;
import pw.react.backend.reactbackend.entity.User;

@Service
public class UserServiceImpl implements UserService{
	@Resource 
	UserDao userDao;
	@Resource
	UserExistsService userExistsService;
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	@Override
	public ResponseEntity insertUser(User usr) {
		if (userExistsService.exists(usr))
			return new ResponseEntity("User with such a login is already present in database.", HttpStatus.CONFLICT);
		else
		{
			userDao.insertUser(usr);
			return new ResponseEntity(HttpStatus.OK);
		}
	}
	@Override
	public void updateUser(User usr) {
		userDao.updateUser(usr);
		
	}
	@Override
	public void executeUpdateUser(User usr) {
		userDao.executeUpdateUser(usr);
		
	}

	@Override
	public void deleteUser(User usr) {
		userDao.deleteUser(usr);
		
	}
}

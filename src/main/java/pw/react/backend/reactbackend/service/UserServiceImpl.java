package pw.react.backend.reactbackend.service;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.dao.UserDao;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.repository.FindUserRepo;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	UserDao userDao;
	@Resource
	UserExistsService userExistsService;
	@Resource
	FindUserRepo findUserRepo;

	@Override
	public Object findUser(String login) {
		User usr = findUserRepo.find(login);
		if (usr==null) return new ResponseEntity("User with such login is not present in the database.", HttpStatus.CONFLICT);
		else return usr;
	}

	@Override
	public ResponseEntity insertUser(User usr) {
		if (userExistsService.exists(usr))
			return new ResponseEntity("User with such login is already present in the database.", HttpStatus.CONFLICT);
		else
		{
			userDao.insertUser(usr);
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity updateUser(User usr) {
		if (userExistsService.exists(usr)) {
			userDao.updateUser(usr);
			return new ResponseEntity(HttpStatus.OK);
		}
		else return new ResponseEntity("User with such a login is not present in the database.", HttpStatus.CONFLICT);
	}

	@Override
	public void executeUpdateUser(User usr) {
		userDao.executeUpdateUser(usr);
	}

	@Override
	public ResponseEntity deleteUser(String login) {
		User usr = findUserRepo.find(login);
		if (usr==null) return new ResponseEntity("User with such a login is not present in the database.", HttpStatus.CONFLICT);
		else {
			userDao.deleteUser(usr);
			return new ResponseEntity(HttpStatus.OK);
		}
	}


}

package pw.react.backend.reactbackend.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.dao.UserDao;
import pw.react.backend.reactbackend.entity.User;

@Service
public class UserExistsServiceImpl implements UserExistsService{
	@Resource 
	UserDao userDao;

	public boolean exists(User usr) {
		List<User> list = userDao.findAll();
		boolean inDb = false;
		for(User x: list)
		{
			if (x.getLogin().equals(usr.getLogin()))
			{
				inDb = true;
				break;
			}
		}
		return inDb;
	}
}

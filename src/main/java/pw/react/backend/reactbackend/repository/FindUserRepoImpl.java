package pw.react.backend.reactbackend.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.dao.UserDao;
import pw.react.backend.reactbackend.entity.User;

import javax.annotation.Resource;

@Repository
public class FindUserRepoImpl implements FindUserRepo{

    @Resource
    UserDao userDao;

    public User find(User usr)
    {
        List<User> list = userDao.findAll();
        for(User x: list)
        {
            if (x.getLogin().equals(usr.getLogin()))
            {
                return x;
            }
        }
        return null;
    }
}
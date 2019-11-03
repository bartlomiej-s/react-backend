package pw.react.backend.reactbackend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import pw.react.backend.reactbackend.entity.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int arg1) throws SQLException {

        User usr = new User();
        usr.setId(rs.getString("id"));
        usr.setLogin(rs.getString("login"));
        usr.setFirstName(rs.getString("firstName"));
        usr.setLastName(rs.getString("lastName"));
        usr.setDateOfBirth(rs.getString("dateOfBirth"));
        usr.setActive(rs.getString("active"));
        return usr;
    }
}
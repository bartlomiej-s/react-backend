package pw.react.backend.reactbackend.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import pw.react.backend.reactbackend.entity.User;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        /*Long idX = Long.parseLong(rs.getString("id"));
        String loginX = rs.getString("login");
        String firstNameX = rs.getString("first_name");
        String lastNameX = rs.getString("last_name");
        Date date_of_birthX =  null;
        try
        {
            date_of_birthX = new SimpleDateFormat("YYYY-MM-DD").parse(rs.getString("date_of_birth"));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        boolean activeX = Boolean.parseBoolean(rs.getString("active"));*/

        User usr = new User(); //idX, loginX, firstNameX, lastNameX, date_of_birthX, activeX
        usr.setUserId(rs.getString("id"));
        usr.setUserLogin(rs.getString("login"));
        usr.setUserFirstName(rs.getString("first_name"));
        usr.setUserLastName(rs.getString("last_name"));
        usr.setUserDateOfBirth(rs.getString("date_of_birth"));
        usr.setUserActive(rs.getString("active"));
        return usr;
    }
}
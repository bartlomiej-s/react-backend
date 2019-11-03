package pw.react.backend.reactbackend.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.mapper.UserRowMapper;

@Repository
public class UserDaoImpl implements UserDao{

    public UserDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    private NamedParameterJdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("select * from db_schema.Users", new UserRowMapper());
    }

    @Override
    public void insertUser(User usr) {
        final String sql = "insert into db_schema.Users(id, login, firstName, lastName, dateOfBirth, active) values(:id, :login, :firstName, :lastName, :dateOfBirth, :active);";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", usr.getId())
                .addValue("login", usr.getLogin())
                .addValue("firstName", usr.getFirstName())
                .addValue("lastName", usr.getLastName())
                .addValue("dateOfBirth", usr.getDateOfBirth())
                .addValue("active", usr.getActive());
        template.update(sql,param, holder);
    }

    @Override
    public void updateUser(User usr) {
        final String sql = "update db_schema.Users set login=:login, firstName=:firstName, lastName=:lastName, dateOfBirth=:dateOfBirth, active=:active where id=:id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", usr.getId())
                .addValue("login", usr.getLogin())
                .addValue("firstName", usr.getFirstName())
                .addValue("lastName", usr.getLastName())
                .addValue("dateOfBirth", usr.getDateOfBirth())
                .addValue("active", usr.getActive());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateUser(User usr) {
        final String sql = "update db_schema.Users set login=:login, firstName=:firstName, lastName=:lastName, dateOfBirth=:dateOfBirth, active=:active where id=:id";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", usr.getId());
        map.put("login", usr.getLogin());
        map.put("firstName", usr.getFirstName());
        map.put("lastName", usr.getLastName());
        map.put("dateOfBirth", usr.getDateOfBirth());
        map.put("active", usr.getActive());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteUser(User usr) {
        final String sql = "delete from db_schema.Users where id=:id";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", usr.getId());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
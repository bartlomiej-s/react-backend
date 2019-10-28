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
    NamedParameterJdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("select * from db_schema.Users", new UserRowMapper());
    }

    @Override
    public void insertUser(User usr) {
        final String sql = "insert into db_schema.Users(id, login, first_name, last_name, date_of_birth, active) values(:id, :login, :first_name, :last_name, :date_of_birth, :active);";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", usr.getUserId())
                .addValue("login", usr.getUserLogin())
                .addValue("first_name", usr.getUserFirstName())
                .addValue("last_name", usr.getUserLastName())
                .addValue("date_of_birth", usr.getUserDateOfBirth())
                .addValue("active", usr.getUserActive());
        template.update(sql,param, holder);
    }

    @Override
    public void updateUser(User usr) {
        final String sql = "update db_schema.Users set login=:login, first_name=:first_name, last_name=:last_name, date_of_birth=:date_of_birth, active=:active where id=:id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", usr.getUserId())
                .addValue("login", usr.getUserLogin())
                .addValue("first_name", usr.getUserFirstName())
                .addValue("last_name", usr.getUserLastName())
                .addValue("date_of_birth", usr.getUserDateOfBirth())
                .addValue("active", usr.getUserActive());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateUser(User usr) {
        final String sql = "update db_schema.Users set login=:login, first_name=:first_name, last_name=:last_name, date_of_birth=:date_of_birth, active=:active where id=:id";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", usr.getUserId());
        map.put("login", usr.getUserLogin());
        map.put("first_name", usr.getUserFirstName());
        map.put("last_name", usr.getUserLastName());
        map.put("date_of_birth", usr.getUserDateOfBirth());
        map.put("active", usr.getUserActive());

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
        map.put("id", usr.getUserId());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}
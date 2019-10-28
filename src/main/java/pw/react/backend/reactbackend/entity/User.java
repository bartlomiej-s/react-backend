package pw.react.backend.reactbackend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@Entity
public class User implements Serializable {

    /*public User(Long idX, String loginX, String firstNameX, String lastNameX, Date date_of_birthX, boolean activeX) {
        this.id = idX;
        this.login = loginX;
        this.firstName = firstNameX;
        this.lastName = lastNameX;
        this.date_of_birth = date_of_birthX;
        this.active = activeX;
    }*/

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    protected String login;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    @NotNull
    protected Date date_of_birth;
    @NotNull
    protected boolean active;

    public Long getUserId() {
        return id;
    }
    public String getUserLogin() {
        return login;
    }
    public String getUserFirstName() {
        return firstName;
    }
    public String getUserLastName() {
        return lastName;
    }
    public Date getUserDateOfBirth() {
        return date_of_birth;
    }
    public Boolean getUserActive() {
        return active;
    }

    public void setUserId(String  id) {
        this.id = Long.parseLong(id);
    }
    public void setUserLogin(String login) {
        this.login = login;
    }
    public void setUserFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setUserLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserDateOfBirth(String date_of_birth) {
        try
        {
            this.date_of_birth = new SimpleDateFormat("YYYY-MM-DD").parse(date_of_birth);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    public void setUserActive(String active) {
        this.active = Boolean.parseBoolean(active);
    }
}
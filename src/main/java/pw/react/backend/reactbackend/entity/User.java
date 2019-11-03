package pw.react.backend.reactbackend.entity;

import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = "login")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    private String login;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private boolean active;

    public String getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public Boolean getActive() {
        return active;
    }

    public void setId(String  id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(String dateOfBirth) {
        try
        {
            this.dateOfBirth = new SimpleDateFormat("YYYY-MM-DD").parse(dateOfBirth);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    public void setActive(String active) {
        this.active = Boolean.parseBoolean(active);
    }
}
package ua.kiev.naukma.auth.shared.model.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements IUser, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name="name")
    private String userName;

    @NotNull
    @Column(name="password")
    private String password;

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Version
    private Long version;

    public User() {
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
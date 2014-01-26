package ua.kiev.naukma.auth.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginModel implements IsSerializable {
    private String login;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LoginModel() {
        super();
    }
}

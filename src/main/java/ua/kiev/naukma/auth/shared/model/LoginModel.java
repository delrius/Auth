package ua.kiev.naukma.auth.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginModel implements IsSerializable, Comparable<LoginModel> {
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

    @Override
    public int compareTo(LoginModel o) {
        return login == null ? 0 : login.compareTo(o.getLogin());
    }
}

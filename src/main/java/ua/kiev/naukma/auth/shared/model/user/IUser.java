package ua.kiev.naukma.auth.shared.model.user;

@SuppressWarnings("UnusedDeclaration")
public interface IUser {
    String getUserName();

    void setUserName(String userName);

    String getPassword();

    void setPassword(String password);

    Long getId();

    void setId(Long id);
}

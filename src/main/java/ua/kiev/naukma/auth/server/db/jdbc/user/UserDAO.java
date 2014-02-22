package ua.kiev.naukma.auth.server.db.jdbc.user;


public interface UserDAO {
    public boolean isUserExist(String name);
    public boolean registerUser(String name, String password);
    public boolean checkIfUserAndPassMatches(String user, String password);
}

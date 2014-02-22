package ua.kiev.naukma.auth.server.db.jdbc.user;

import ua.kiev.naukma.auth.server.db.jdbc.DatabaseConnectivityMgr;

public class UserDAOImpl implements UserDAO {

    private static DatabaseConnectivityMgr manager = new DatabaseConnectivityMgr();

    private static String CHECK_USER_EXISTS = "SELECT id FROM users as U WHERE U.name = ? ";
    private static String REGISTER_USER = "INSERT INTO users (name, password) VALUES (?, ?)";
    private static String GET_USERS = "SELECT name, password FROM users as U WHERE U.name = ? and U.password = ?";

    public UserDAOImpl() {
    }

    @Override
    public boolean isUserExist(String user) {
        return !manager.getData(CHECK_USER_EXISTS, 1, user).isEmpty();
    }

    @Override
    public boolean registerUser(String user, String password) {
        return manager.insertData(REGISTER_USER, user, password);
    }

    @Override
    public boolean checkIfUserAndPassMatches(String user, String password) {
        return !manager.getData(GET_USERS, 2, user, password).isEmpty();
    }
}

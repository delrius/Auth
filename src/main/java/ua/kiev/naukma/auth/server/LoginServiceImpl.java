package ua.kiev.naukma.auth.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.LoginService;
import ua.kiev.naukma.auth.server.db.UserDAO;
import ua.kiev.naukma.auth.server.db.UserDAOImpl;
import ua.kiev.naukma.auth.shared.ConstantResults;


@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {
    private static UserDAO impl = new UserDAOImpl();

    public String login(String name, String password) {
//        return FileUtils.login(name, password);
        if (!impl.isUserExist(name)) {
            return ConstantResults.noSuchUser;
        } else if (!impl.checkIfUserAndPassMatches(name, password)) {
            return ConstantResults.passwordIncorrect;
        } else {
            return ConstantResults.logined;
        }
    }
}
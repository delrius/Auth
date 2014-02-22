package ua.kiev.naukma.auth.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.LoginService;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;
import ua.kiev.naukma.auth.shared.ConstantResults;
import ua.kiev.naukma.auth.shared.model.user.User;

import java.util.List;


@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {
    private static UserDAOImpl impl = new UserDAOImpl();

    public String login(String name, String password) {
        return loginWithHibernate(name, password);
    }
//
//    public String loginWithFile(String name, String password) {
//        return FileUtils.login(name, password);
//    }
//
//    public String loginWithJDBC(String name, String password) {
//        if (!impl.isUserExist(name)) {
//            return ConstantResults.noSuchUser;
//        } else if (!impl.checkIfUserAndPassMatches(name, password)) {
//            return ConstantResults.passwordIncorrect;
//        } else {
//            return ConstantResults.logined;
//        }
//    }

    public String loginWithHibernate(String name, String password) {
        final List<User> all = impl.getAll();
        for (User user : all) {
            if (user.getUserName().equals(name)) {
                if (user.getPassword().equals(password)) {
                    return ConstantResults.logined;
                }  else {
                    return ConstantResults.passwordIncorrect;
                }
            }
        }
        return ConstantResults.noSuchUser;
    }
}
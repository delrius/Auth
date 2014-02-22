package ua.kiev.naukma.auth.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.RegistrationService;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;
import ua.kiev.naukma.auth.shared.ConstantResults;
import ua.kiev.naukma.auth.shared.model.user.User;

import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RegistrationServiceImpl extends RemoteServiceServlet implements
        RegistrationService {
    private static UserDAOImpl impl = new UserDAOImpl();

    public String register(String name, String password) {
        return registerWithHibernate(name, password);
    }


//    public String registerWithFile(String name, String password) {
//        return FileUtils.register(name, password);
//    }
//
//    public String registerWithJDBC(String name, String password) {
//        if (impl.isUserExist(name)) {
//            return ConstantResults.alreadyRegistered;
//        } else if (impl.registerUser(name, password)) {
//            return ConstantResults.registered;
//        } else {
//            return ConstantResults.databaseError;
//        }
//    }

    public String registerWithHibernate(String name, String password) {
        final List<User> all = impl.getAll();
        for (User user : all) {
            if (user.getUserName().equals(name)) {
                if (user.getPassword().equals(password)) {
                    return ConstantResults.alreadyRegistered;
                }
            }
        }
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        final Long save = impl.save(user);
        if (save != null) {
            return ConstantResults.registered;
        } else {
            return ConstantResults.databaseError;
        }
    }
}

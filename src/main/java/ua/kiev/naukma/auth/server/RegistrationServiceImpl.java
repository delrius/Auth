package ua.kiev.naukma.auth.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.RegistrationService;
import ua.kiev.naukma.auth.server.db.UserDAO;
import ua.kiev.naukma.auth.server.db.UserDAOImpl;
import ua.kiev.naukma.auth.shared.ConstantResults;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RegistrationServiceImpl extends RemoteServiceServlet implements
        RegistrationService {
    private static UserDAO impl = new UserDAOImpl();

    public String register(String name, String password) {
//        return FileUtils.register(name, password);
        if (impl.isUserExist(name)){
            return ConstantResults.alreadyRegistered;
        } else if (impl.registerUser(name, password)) {
            return ConstantResults.registered;
        } else {
            return ConstantResults.databaseError;
        }
    }
}

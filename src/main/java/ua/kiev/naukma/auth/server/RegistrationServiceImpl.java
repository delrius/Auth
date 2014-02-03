package ua.kiev.naukma.auth.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.RegistrationService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RegistrationServiceImpl extends RemoteServiceServlet implements
        RegistrationService {

    public String greetServer(String input) throws IllegalArgumentException {
        return null;
    }

    public String register(String name, String password) {
        return FileUtils.register(name, password);
    }
}

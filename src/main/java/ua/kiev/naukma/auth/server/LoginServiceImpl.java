package ua.kiev.naukma.auth.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.LoginService;


@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {

    public String login(String name, String password) {
        return FileUtils.login(name, password);
    }
}
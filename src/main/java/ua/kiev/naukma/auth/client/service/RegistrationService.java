package ua.kiev.naukma.auth.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("register")
public interface RegistrationService extends RemoteService {
    String register(String name, String password);
}

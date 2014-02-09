package ua.kiev.naukma.auth.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.kiev.naukma.auth.shared.model.LoginModel;
import ua.kiev.naukma.auth.shared.model.PagingResult;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
    PagingResult<LoginModel> getUsers(int start, int length, boolean sortInfo);
}

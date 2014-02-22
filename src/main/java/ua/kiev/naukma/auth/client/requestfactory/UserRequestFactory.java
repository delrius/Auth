package ua.kiev.naukma.auth.client.requestfactory;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import ua.kiev.naukma.auth.client.model.UserProxy;
import ua.kiev.naukma.auth.server.locator.user.UserDaoLocator;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;

import java.util.List;

@SuppressWarnings("unused")
public interface UserRequestFactory extends RequestFactory {

    @Service(value = UserDAOImpl.class, locator = UserDaoLocator.class)
    public interface UserRequestContext extends RequestContext {
        Request<UserProxy> findById(Long id);
        Request<List<UserProxy>> getAll();
    }

    UserRequestContext context();
}
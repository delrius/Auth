package ua.kiev.naukma.auth.server.locator.user;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;

public class UserDaoLocator implements ServiceLocator {
    @Override
    public Object getInstance(Class<?> clazz) {
        return new UserDAOImpl();
    }
}

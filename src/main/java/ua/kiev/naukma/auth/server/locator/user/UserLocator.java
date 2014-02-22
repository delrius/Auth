package ua.kiev.naukma.auth.server.locator.user;

import com.google.web.bindery.requestfactory.shared.Locator;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;
import ua.kiev.naukma.auth.shared.model.user.User;

public class UserLocator extends Locator<User, Long> {
    @Override
    public User create(Class<? extends User> clazz) {
        return new User();
    }

    @Override
    public User find(Class<? extends User> clazz, Long id) {
        return getUserDao().findById(id);
    }

    public UserDAOImpl getUserDao() {
        return new UserDAOImpl();
    }

    @Override
    public Class<User> getDomainType() {
        return User.class;
    }

    @Override
    public Long getId(User domainObject) {
        return domainObject.getId();
    }

    @Override
    public Class<Long> getIdType() {
        return Long.class;
    }

    @Override
    public Object getVersion(User domainObject) {
        return domainObject.getVersion();
    }
}

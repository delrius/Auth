package ua.kiev.naukma.auth.client.model;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import ua.kiev.naukma.auth.server.locator.user.UserLocator;
import ua.kiev.naukma.auth.shared.model.user.IUser;
import ua.kiev.naukma.auth.shared.model.user.User;

@ProxyFor(value = User.class, locator = UserLocator.class)
public interface UserProxy extends EntityProxy, IUser {

}

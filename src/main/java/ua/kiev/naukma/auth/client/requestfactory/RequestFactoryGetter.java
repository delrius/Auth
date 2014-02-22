package ua.kiev.naukma.auth.client.requestfactory;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class RequestFactoryGetter {

    private static UserRequestFactory userMgr;

    public static UserRequestFactory getUserFactory() {
        if (userMgr == null) {
            final EventBus eventBus = new SimpleEventBus();
            userMgr = GWT.create(UserRequestFactory.class);
            userMgr.initialize(eventBus);
        }
        return userMgr;
    }
}

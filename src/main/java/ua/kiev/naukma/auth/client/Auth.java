package ua.kiev.naukma.auth.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import ua.kiev.naukma.auth.client.resources.MyBundle;
import ua.kiev.naukma.auth.client.ui.LoginPanel;
import ua.kiev.naukma.auth.client.ui.MenuBar;

public class Auth implements EntryPoint {

    public void onModuleLoad() {
        Log.setUncaughtExceptionHandler();
        MyBundle.INSTANCE.css().ensureInjected();
        RootPanel.get("menu").add(new MenuBar().asWidget());
        RootPanel.get("bodyElem").add(new LoginPanel().asWidget());
    }
}

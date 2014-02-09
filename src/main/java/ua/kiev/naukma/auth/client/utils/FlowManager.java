package ua.kiev.naukma.auth.client.utils;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import ua.kiev.naukma.auth.client.ui.LoginPanel;
import ua.kiev.naukma.auth.client.ui.RegistrationSuccessPanel;
import ua.kiev.naukma.auth.client.ui.SignInSuccessPanel;

public class FlowManager {
    private static final String ELEM = "bodyElem";

    public static void switchToFlow(Flow flow) {
        RootPanel.get(ELEM).clear();
        Widget panel;

        switch (flow) {
            case LOGIN:
                panel = new LoginPanel().asWidget();
                break;
            case AFTER_SIGN_IN:
                panel = new SignInSuccessPanel().asWidget();
                break;
            case AFTER_SIGN_UP:
                panel = new RegistrationSuccessPanel().asWidget();
                break;
            default:
                return;
        }

        RootPanel.get(ELEM).add(panel);
    }

    public enum Flow {
        LOGIN, AFTER_SIGN_UP, AFTER_SIGN_IN
    }
}

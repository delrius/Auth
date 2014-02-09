package ua.kiev.naukma.auth.client.utils;

import com.google.gwt.core.client.GWT;
import ua.kiev.naukma.auth.client.Messages;

public class Text {
    private static Messages messages;

    public static Messages getMessages() {
        if (messages == null) {
            messages = GWT.create(Messages.class);
        }
        return messages;
    }
}

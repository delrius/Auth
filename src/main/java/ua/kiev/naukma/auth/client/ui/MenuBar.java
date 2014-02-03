package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class MenuBar extends Composite {
    private static MenuBarUiBinder ourUiBinder = GWT.create(MenuBarUiBinder.class);
    @UiField
    MenuItem enMenu;
    @UiField
    MenuItem uaMenu;

    public MenuBar() {
        initWidget(ourUiBinder.createAndBindUi(this));
        enMenu.setScheduledCommand(new Scheduler.ScheduledCommand() {
            public void execute() {
                changeLocale("en");
            }
        });

        uaMenu.setScheduledCommand(new Scheduler.ScheduledCommand() {
            public void execute() {
                changeLocale("ua");
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    private void changeLocale(String locale) {
        Window.Location.assign(
                Window.Location.createUrlBuilder()
                        .setParameter(LocaleInfo.getLocaleQueryParam(), locale)
                        .buildString());
    }


    interface MenuBarUiBinder extends UiBinder<com.google.gwt.user.client.ui.MenuBar, MenuBar> {
    }
}
package ua.kiev.naukma.auth.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginPanel extends Composite {

    @UiField
    LoginEditor loginEditor;

    @Override
    public Widget asWidget() {
        driver.initialize(loginEditor);
        driver.edit(new LoginModel());
        return this;
    }

    interface Driver extends SimpleBeanEditorDriver<LoginModel, LoginEditor> {
    }

    Driver driver = GWT.create(Driver.class);

    interface LoginPanelUiBinder extends UiBinder<HorizontalPanel, LoginPanel> {
    }

    private static LoginPanelUiBinder ourUiBinder = GWT.create(LoginPanelUiBinder.class);

    public LoginPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}
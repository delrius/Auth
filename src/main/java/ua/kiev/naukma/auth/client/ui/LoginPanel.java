package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ua.kiev.naukma.auth.client.Messages;
import ua.kiev.naukma.auth.client.service.LoginServiceAsync;
import ua.kiev.naukma.auth.client.service.RegistrationServiceAsync;
import ua.kiev.naukma.auth.client.utils.FlowManager;
import ua.kiev.naukma.auth.client.utils.MD5;
import ua.kiev.naukma.auth.shared.ConstantResults;
import ua.kiev.naukma.auth.shared.model.LoginModel;

public class LoginPanel extends Composite {
    private RegistrationServiceAsync service = RegistrationServiceAsync.Util.getInstance();
    private LoginServiceAsync loginService = LoginServiceAsync.Util.getInstance();
    private Messages messages = GWT.create(Messages.class);

    @UiField
    LoginEditor loginEditor;

    @UiField
    Button signUpButton;

    @UiField
    Button signInButton;

    @Override
    public Widget asWidget() {
        driver.initialize(loginEditor);
        driver.edit(new LoginModel());

        return this;
    }

    interface Driver extends SimpleBeanEditorDriver<LoginModel, LoginEditor> {
    }

    Driver driver = GWT.create(Driver.class);

    interface LoginPanelUiBinder extends UiBinder<VerticalPanel, LoginPanel> {
    }

    private static LoginPanelUiBinder ourUiBinder = GWT.create(LoginPanelUiBinder.class);

    public LoginPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));

        signUpButton.setEnabled(false);
        signInButton.setEnabled(false);

        loginEditor.setCallback(new LoginEditor.CallBackImpl() {
            public void call() {
                if (signInButton != null) {
                    signInButton.setEnabled(loginEditor.isValid());
                }
                if (signUpButton != null) {
                    signUpButton.setEnabled(loginEditor.isValid());
                }
            }
        });

        signUpButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (!loginEditor.isValid()) {
                    return;
                }
                loginEditor.clearInvalid();
                LoginModel model = driver.flush();
                String hashed = MD5.hash(model.getPassword());

                service.register(model.getLogin(), hashed, new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(String result) {
                        if (result.equals(ConstantResults.alreadyRegistered)) {
                            loginEditor.setLoginInvalid(messages.userNameExists());
                        } else if (result.equals(ConstantResults.registered)){
                            FlowManager.switchToFlow(FlowManager.Flow.AFTER_SIGN_UP);
                        }
                    }
                });
            }
        });

        signInButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (!loginEditor.isValid()) {
                    return;
                }
                loginEditor.clearInvalid();
                LoginModel model = driver.flush();
                String hashed = MD5.hash(model.getPassword());

                loginService.login(model.getLogin(), hashed, new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(String result) {
                        if (result.equals(ConstantResults.noSuchUser)) {
                            loginEditor.setLoginInvalid(messages.userNotExists());
                        } else if (result.equals(ConstantResults.passwordIncorrect)) {
                            loginEditor.setPassInvalid(messages.passwordIncorrect());
                        } else if (result.equals(ConstantResults.logined)){
                            FlowManager.switchToFlow(FlowManager.Flow.AFTER_SIGN_IN);
                        }
                    }
                });
            }
        });
    }
}
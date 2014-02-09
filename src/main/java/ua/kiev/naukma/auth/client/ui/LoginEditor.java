package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ua.kiev.naukma.auth.client.Messages;
import ua.kiev.naukma.auth.client.resources.MyBundle;
import ua.kiev.naukma.auth.shared.model.LoginModel;

public class LoginEditor extends Composite implements Editor<LoginModel> {

    public interface Binder1 extends UiBinder<Widget, LoginEditor> {
    }

    private static Binder1 uiBinder = GWT.create(Binder1.class);
    private static MyBundle bundle = MyBundle.INSTANCE;
    private final Messages messages = GWT.create(Messages.class);

    @UiField(provided = true)
    @Ignore
    FlexTable flexTable;

    @Ignore
    Label loginLabel;
    @Ignore
    Label passwordLabel;
    @Ignore
    Image loginError;
    @Ignore
    Image passwordError;

    TextBox login;
    PasswordTextBox password;

    @UiConstructor
    public LoginEditor() {
        flexTable = new FlexTable();
        login = new TextBox();
        password = new PasswordTextBox();
        initWidget(uiBinder.createAndBindUi(this));
        initWidget();
    }

    public void initWidget() {
        loginLabel = new Label(messages.loginFieldLabel());
        passwordLabel = new Label(messages.passwordFieldLabel());

        loginError = new Image(bundle.errorIcon());
        loginError.setTitle("Password is required");

        passwordError = new Image(bundle.errorIcon());
        passwordError.setTitle("Login is required");

        login = new TextBox();
        login.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                validateField(login, loginError, messages.loginTooShort());
                if (callback != null) {
                    callback.call();
                }
            }
        });

        login.addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                validateField(login, loginError, messages.loginTooShort());
                if (callback != null) {
                    callback.call();
                }
            }
        });

        password = new PasswordTextBox();
        password.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                validateField(password, passwordError, messages.passwordTooShort());
                if (callback != null) {
                    callback.call();
                }
            }
        });

        password.addBlurHandler(new BlurHandler() {
            public void onBlur(BlurEvent event) {
                validateField(password, passwordError, messages.passwordTooShort());
                if (callback != null) {
                    callback.call();
                }
            }
        });

        flexTable.setWidget(0, 0, loginLabel);
        flexTable.setWidget(0, 1, login);
        flexTable.setWidget(1, 0, passwordLabel);
        flexTable.setWidget(1, 1, password);

        flexTable.setWidget(1, 2, passwordError);
        flexTable.setWidget(0, 2, loginError);

        passwordError.setVisible(false);
        loginError.setVisible(false);
    }

    public boolean isValid() {
        return isValid(login) && isValid(password);
    }

    public boolean isValid(TextBox tb) {
        int minLength = 4;
        return (tb.getValue() != null) && (tb.getValue().length() >= minLength);
    }

    public void validateField(TextBox tb, Image errorImage, String message) {
        clearInvalid(errorImage);
        if (!isValid(tb)) {
            setInvalid(errorImage, message);
        } else {
            clearInvalid(errorImage);
        }
    }

    public void clearInvalid(Image errorImage) {
        errorImage.setVisible(false);
    }

    public void clearInvalid() {
        loginError.setVisible(false);
        passwordError.setVisible(false);
    }

    public void setInvalid(Image errorImage, String text) {
        errorImage.setTitle(text);
        errorImage.setVisible(true);
    }

    public void setLoginInvalid(String text) {
        loginError.setTitle(text);
        loginError.setVisible(true);
    }

    public void setPassInvalid(String text) {
        passwordError.setTitle(text);
        passwordError.setVisible(true);
    }

    public void setCallback(CallBackImpl callback) {
        this.callback = callback;
    }

    private CallBackImpl callback;

    public interface CallBackImpl {
        public void call();
    }
}
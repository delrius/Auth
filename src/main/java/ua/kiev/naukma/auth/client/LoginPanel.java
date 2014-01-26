package ua.kiev.naukma.auth.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class LoginPanel extends Composite implements IsWidget {

    public LoginPanel() {
    }

    @Override
    public Widget asWidget() {
        LoginEditor editor = new LoginEditor();
        driver.initialize(editor);
        driver.edit(new LoginModel());
        return editor.asWidget();
    }

    interface Driver extends SimpleBeanEditorDriver<LoginModel, LoginEditor> {
    }

    // Create the Driver
    Driver driver = GWT.create(Driver.class);
}

class LoginEditor extends Composite implements Editor<LoginModel> {

    public interface Binder1 extends UiBinder<Widget, LoginEditor> {
    }

    private static Binder1 uiBinder = GWT.create(Binder1.class);
    private static MyBundle bundle = MyBundle.INSTANCE;
    private static Messages messages = GWT.create(Messages.class);

    @UiField
    @Ignore
    HorizontalPanel horizontalPanel;

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

    public LoginEditor() {
        flexTable = new FlexTable();
        login = new TextBox();
        password = new PasswordTextBox();
        initWidget(uiBinder.createAndBindUi(this));
    }

    public Widget asWidget() {
        loginLabel = new Label(messages.loginLabel());
        passwordLabel = new Label(messages.passwordLabel());

        loginError = new Image(bundle.errorIcon());
        loginError.setTitle("Password is required");

        passwordError = new Image(bundle.errorIcon());
        passwordError.setTitle("Login is required");

        flexTable.setWidget(0, 0, loginLabel);
        flexTable.setWidget(0, 1, login = new TextBox());
        flexTable.setWidget(1, 0, passwordLabel);
        flexTable.setWidget(1, 1, password = new PasswordTextBox());
        flexTable.setWidget(1, 2, passwordError);
        flexTable.setWidget(0, 2, loginError);
        return this;
    }
}
package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import ua.kiev.naukma.auth.client.Messages;
import ua.kiev.naukma.auth.client.utils.FlowManager;
import ua.kiev.naukma.auth.client.utils.Text;
import ua.kiev.naukma.auth.shared.model.LoginModel;
import ua.kiev.naukma.auth.shared.model.LoginModelProperties;

public class SignInSuccessPanel extends Composite {
    private static SignUpSuccessPanelUiBinder ourUiBinder = GWT.create(SignUpSuccessPanelUiBinder.class);
    private Messages messages = Text.getMessages();

    @UiField
    Anchor link;

    @UiField
    Button showUsersButton;

    @UiField(provided = true)
    DataGrid<LoginModel> dataGrid;

    @UiField(provided = true)
    SimplePager pager;

    public SignInSuccessPanel() {
        initialize();
    }

    private void initialize() {
        DataGridResources resources = GWT.create(DataGridResources.class);
        resources.dataGridStyle().ensureInjected();

        dataGrid = new DataGrid<LoginModel>(10, resources, LoginModelProperties.KEY_PROVIDER);
        dataGrid.setWidth("100%");
        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("empty user list"));

        initTableColumns();

        dataGrid.getColumnSortList().push(dataGrid.getColumn(0));

        AsyncDataProvider<LoginModel> dataProvider = LoginModelProperties.getProvider(dataGrid);
        dataProvider.addDataDisplay(dataGrid);

        ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(dataGrid);
        dataGrid.addColumnSortHandler(columnSortHandler);


        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(dataGrid);

        initWidget(ourUiBinder.createAndBindUi(this));

        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                FlowManager.switchToFlow(FlowManager.Flow.LOGIN);
            }
        });
        showUsersButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dataGrid.setVisible(true);
                pager.setVisible(true);
                dataGrid.setVisibleRangeAndClearData(dataGrid.getVisibleRange(), true);
            }
        });
    }

    private void initTableColumns() {
        Column<LoginModel, String> nameColumn = new Column<LoginModel, String>(new TextColoredCell()) {
            @Override
            public String getValue(LoginModel object) {
                return object.getLogin();
            }
        };
        nameColumn.setSortable(true);
        dataGrid.addColumn(nameColumn, messages.loginFieldLabel());

        Column<LoginModel, String> passwordColumn = new Column<LoginModel, String>(new TextColoredCell()) {
            @Override
            public String getValue(LoginModel object) {
                return object.getPassword();
            }
        };
        passwordColumn.setSortable(false);
        dataGrid.addColumn(passwordColumn, messages.passwordFieldLabel());
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    interface SignUpSuccessPanelUiBinder extends UiBinder<DockLayoutPanel, SignInSuccessPanel> {
    }
}
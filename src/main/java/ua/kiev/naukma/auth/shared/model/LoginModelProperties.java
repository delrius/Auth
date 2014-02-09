package ua.kiev.naukma.auth.shared.model;

import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import ua.kiev.naukma.auth.client.service.UserServiceAsync;

public class LoginModelProperties {

    public static final ProvidesKey<LoginModel> KEY_PROVIDER = new ProvidesKey<LoginModel>() {
        public Object getKey(LoginModel item) {
            return item == null ? null : item.getLogin().hashCode();
        }
    };

    private static UserServiceAsync userService = UserServiceAsync.Util.getInstance();

    public static AsyncDataProvider<LoginModel> getProvider(final DataGrid<LoginModel> dataGrid) {
        return new AsyncDataProvider<LoginModel>() {
            @Override
            protected void onRangeChanged(HasData<LoginModel> display) {

                final int start = display.getVisibleRange().getStart();
                int length = display.getVisibleRange().getLength();
                final ColumnSortList.ColumnSortInfo sortInfo = dataGrid.getColumnSortList().get(0);

                AsyncCallback<PagingResult<LoginModel>> callback = new AsyncCallback<PagingResult<LoginModel>>() {
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    public void onSuccess(PagingResult<LoginModel> result) {
                        updateRowCount(result.getAllLength(), true);
                        updateRowData(start, result.getResult());
                    }
                };
                // The remote service that should be implemented

                userService.getUsers(start, length, sortInfo.isAscending(), callback);
            }
        };
    }

}

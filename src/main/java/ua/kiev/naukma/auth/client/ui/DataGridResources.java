package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.user.cellview.client.DataGrid;

public interface DataGridResources extends DataGrid.Resources {
    @Source(value = {DataGrid.Style.DEFAULT_CSS,
            "DataGridStyle.css"})
    DataGrid.Style dataGridStyle();
}

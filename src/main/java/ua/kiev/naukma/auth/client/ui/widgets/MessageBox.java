package ua.kiev.naukma.auth.client.ui.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class MessageBox {

    public static void show(String header, String content) {
        final DialogBox box = new DialogBox();
        final VerticalPanel panel = new VerticalPanel();
        box.setText(header);
        panel.add(new Label(content));
        final Button buttonClose = new Button("Close", new ClickHandler() {
            public void onClick(final ClickEvent event) {
                box.hide();
            }
        });
// few empty labels to make widget larger
        final Label emptyLabel = new Label("");
        emptyLabel.setSize("auto", "25px");
        panel.add(emptyLabel);
        panel.add(emptyLabel);
        buttonClose.setWidth("90px");
        panel.add(buttonClose);
        panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_RIGHT);
        box.add(panel);
        box.show();
    }

}
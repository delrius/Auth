package ua.kiev.naukma.auth.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import ua.kiev.naukma.auth.client.utils.FlowManager;

public class SignUpSuccessPanel extends Composite {
    private static SignUpSuccessPanelUiBinder ourUiBinder = GWT.create(SignUpSuccessPanelUiBinder.class);
    @UiField
    Anchor link;

    public SignUpSuccessPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                FlowManager.switchToFlow(FlowManager.Flow.LOGIN);
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    interface SignUpSuccessPanelUiBinder extends UiBinder<VerticalPanel, SignUpSuccessPanel> {
    }
}
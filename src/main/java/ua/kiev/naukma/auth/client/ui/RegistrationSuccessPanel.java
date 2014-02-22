package ua.kiev.naukma.auth.client.ui;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import ua.kiev.naukma.auth.client.model.UserProxy;
import ua.kiev.naukma.auth.client.requestfactory.RequestFactoryGetter;
import ua.kiev.naukma.auth.client.requestfactory.UserRequestFactory;
import ua.kiev.naukma.auth.client.utils.FlowManager;

import java.util.List;

public class RegistrationSuccessPanel extends Composite {
    private static SignInSuccessPanelUiBinder ourUiBinder = GWT.create(SignInSuccessPanelUiBinder.class);

    @UiField
    Anchor link;

    @UiField
    Button showUsers;

    public RegistrationSuccessPanel() {
        initWidget(ourUiBinder.createAndBindUi(this));
        link.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                FlowManager.switchToFlow(FlowManager.Flow.LOGIN);
            }
        });
        showUsers.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final UserRequestFactory.UserRequestContext context = RequestFactoryGetter.getUserFactory().context();
                final Request<List<UserProxy>> all = context.getAll();
                all.fire(new Receiver<List<UserProxy>>() {
                    @Override
                    public void onSuccess(List<UserProxy> response) {
                        for (UserProxy user : response) {
                            Log.debug("User:" + "id - " + user.getId() + ", name - " + user.getUserName() + ", password - " + user.getPassword());
                        }
                    }
                });
            }
        });
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    interface SignInSuccessPanelUiBinder extends UiBinder<VerticalPanel, RegistrationSuccessPanel> {
    }
}
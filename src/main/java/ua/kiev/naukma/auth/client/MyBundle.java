package ua.kiev.naukma.auth.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface MyBundle extends ClientBundle {
    public static final MyBundle INSTANCE = GWT.create(MyBundle.class);

    @Source("resources/my.css")
    public MyCss css();

    @Source("resources/errorIcon.png")
    public ImageResource errorIcon();


    interface MyCss extends CssResource {
        public String myStyle();
    }
}
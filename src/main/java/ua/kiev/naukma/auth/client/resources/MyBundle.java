package ua.kiev.naukma.auth.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface MyBundle extends ClientBundle {
    public static final MyBundle INSTANCE = GWT.create(MyBundle.class);

    @Source("my.css")
    public MyCss css();

    @Source("errorIcon.png")
    public ImageResource errorIcon();


    interface MyCss extends CssResource {
        public String headerStyle();
    }
}
package com.techimage.projectjfx.util;

import com.techimage.projectjfx.MainApp;

import java.net.URL;

public class ResourceUtil {

    public static URL getRootUrl (String viewName) {
        return MainApp.class.getResource(viewName);
    }

    public static URL getViewUrl (String viewName) {
        return MainApp.class.getResource(String.format("views/%s", viewName));
    }

    public static URL getImageUrl (String imageName) {
        return MainApp.class.getResource(String.format("assets/img/%s", imageName));
    }

    public static URL getIconUrl (String iconName) {
        return MainApp.class.getResource(String.format("assets/icon/%s", iconName));
    }

    public static URL getCSSUrl (String cssName) {
        return MainApp.class.getResource(String.format("assets/css/%s", cssName));
    }

    public static URL getViewFormUrl (String formName) {
        return MainApp.class.getResource(String.format("views/forms/%s", formName));
    }
}

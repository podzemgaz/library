package resource;

import java.util.ResourceBundle;

public class MyConfigManager {
    private static final ResourceBundle RS = ResourceBundle.getBundle("config");

    private MyConfigManager() {
        //block
    }
    public static String getProperty(String key) {
        return RS.getString(key);
    }
}

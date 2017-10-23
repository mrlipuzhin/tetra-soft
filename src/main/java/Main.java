import info.AppInfo;

import java.io.IOException;
import java.util.prefs.InvalidPreferencesFormatException;

public class Main {
    public static void main(String[] args) {
        AppInfo appInfo = new AppInfo();
        appInfo.initialize();

        App app = new App();
        try {
            app.start();
        } catch (IOException | InvalidPreferencesFormatException e) {
            e.printStackTrace();
        }
    }
}

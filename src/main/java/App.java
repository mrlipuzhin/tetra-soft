import objects.Triangle;
import readers.TriangleReader;
import writers.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

class App {
    private static final String DATA_DIR = "data_dir";
    private static final String OUTPUT_DIR = "output_dir";

    private File dataDir;
    private File outputDir;

    void start() throws IOException, InvalidPreferencesFormatException {
        prepareLogger();
        loadConfig();

        final File[] files = dataDir.listFiles();
        if (files == null) {
            return;
        }

        List<Triangle> resultCollection = new ArrayList<>();
        for (File file : files) {
            TriangleReader tr = new TriangleReader(file);
            tr.read(resultCollection);
        }
        resultCollection.sort(Triangle::compareTo);

        Writer writer = new Writer(outputDir);
        writer.write(resultCollection);
    }

    private void prepareLogger() throws IOException {
        Logger.getGlobal().setUseParentHandlers(false);
        Logger.getGlobal().addHandler(new FileHandler());
    }

    private void loadConfig() throws InvalidPreferencesFormatException, IOException {
        Preferences prefs = Preferences.userRoot().node("tetrasoft");

        String defaultDataDir = new File(".").getCanonicalPath().concat("/src/main/resources/data");
        String dataDirPath = prefs.get(DATA_DIR,defaultDataDir);
        if (dataDirPath == null) {
            throw new InvalidPreferencesFormatException(String.format("%s must be set in app preferences", DATA_DIR));
        }

        String defaultOutputDir = System.getProperty("user.home");
        String outputDirPath = prefs.get(OUTPUT_DIR, defaultOutputDir);
        if (outputDirPath == null) {
            throw new InvalidPreferencesFormatException(String.format("%s must be set in app preferences", OUTPUT_DIR));
        }

        dataDir = new File(dataDirPath);
        outputDir = new File(outputDirPath);
    }
}

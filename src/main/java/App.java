import objects.Triangle;
import readers.TriangleReader;
import writers.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

class App {
    private File dataDir;
    private File outputDir;

    private void loadConfig() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        dataDir = new File(prop.getProperty("dataDir"));
        outputDir = new File(prop.getProperty("outputDir"));
    }

    void start() throws IOException {
        loadConfig();

        List<Triangle> resultCollection = new ArrayList<>();

        final File[] files = dataDir.listFiles();
        for (File file : files) {
            Collection<Triangle> collection;
            TriangleReader tr = new TriangleReader(file);
            collection = tr.read();
            resultCollection.addAll(collection);
        }

        resultCollection.sort((Triangle t1, Triangle t2) -> {
            if (t1.getAngle1() == t2.getAngle1()) {
                if (t1.getAngle2() == t2.getAngle2()) {
                    return t1.getAngle3() - t2.getAngle3();
                } else {
                    return t1.getAngle2() - t2.getAngle2();
                }
            } else {
                return t1.getAngle1() - t2.getAngle1();
            }
        });

        Writer writer = new Writer(outputDir);
        writer.write(resultCollection);
    }
}

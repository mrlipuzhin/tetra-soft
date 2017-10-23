package writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

public class Writer {
    private File outputFile;

    public Writer(File outputDir) throws IOException {
        this.outputFile = getOutputFileName(outputDir);
    }

    public <T> void write(Collection<T> collection) throws IOException {
        try (BufferedWriter bf = new BufferedWriter(new java.io.FileWriter(outputFile, true))) {
            for (T object : collection) {
                if (object == null) {
                    continue;
                }

                bf.write(object.toString());
                bf.newLine();
            }

            bf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private File getOutputFileName(File outputDir) throws IOException {
        Long currentTimestamp = new Date().getTime();
        String fileName = String.format("%s.txt", currentTimestamp);

        if (!outputDir.exists()) {
            if (!outputDir.mkdirs()) {
                throw new IOException(String.format("Failed to create directory %s", outputDir));
            }
        }

        return new File(outputDir, fileName);
    }
}

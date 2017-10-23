package readers;

import objects.Triangle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class TriangleReader {
    private File file;

    public TriangleReader(File file) {
        this.file = file;
    }

    public Collection<Triangle> read() throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            int c;
            ArrayList<Triangle> list = new ArrayList<>();

            List<Integer> angles = new ArrayList<>(3);
            while ((c = bf.read()) != -1) {
                if (c == 0x03 && angles.size() == 3) {
                    try {
                        Triangle triangle = Triangle.create(angles.get(0), angles.get(1), angles.get(2));
                        list.add(triangle);
                    } catch (IllegalArgumentException e) {
                        Logger.getGlobal().warning(e.getMessage());
                    }
                    angles.clear();
                    continue;
                } else if (c == 0x03) {
                    angles.clear();
                    continue;
                }
                angles.add(c);
            }
            bf.close();

            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

package cellAdjustment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    private File file;

    public FileHandler(File file) {
        this.file = file;
    }

    public String getFileName(){
        return file.getName();
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public List<String> readFromFile(){
        List<String> lines;
        Path path= Paths.get(file.getAbsolutePath());
        try {
            lines= Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file: "+path, ioe);
        }
        return lines;
    }
}

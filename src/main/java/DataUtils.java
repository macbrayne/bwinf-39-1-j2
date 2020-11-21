import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public final class DataUtils {

    public static File getFromResourceDataPath(String file) {
        try {
            return new File(ClassLoader.getSystemClassLoader().getResource(file).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException(file + " cannot be accessed", e);
        }
    }

    public static String[] loadStrings(String file) {
        try {
            return Files.readAllLines(getFromResourceDataPath(file).toPath()).toArray(new String[] {});
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(file + " cannot be read", e);
        }
    }
}

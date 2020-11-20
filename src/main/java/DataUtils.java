import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public final class DataUtils {

    public static File getFromResourceDataPath(String file) {
        try {
            return new File(ClassLoader.getSystemClassLoader().getResource(file).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            System.err.println(file);
            e.printStackTrace();
        }
        throw new NullPointerException(file + " cannot be accessed");
    }

    public static String[] loadStrings(String file) {
        try {
            return Files.readAllLines(getFromResourceDataPath(file).toPath()).toArray(new String[] {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

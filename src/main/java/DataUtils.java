import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 * Hilfsfunktionen zum Einlesen von Dateien
 */
public final class DataUtils {

    /**
     * Gibt die gefundene Datei zurück. Bei Fehlern wird das weitere Ausführen des Programms verhindert
     * @param file Pfad der Datei
     * @return Gefundene Datei
     */
    public static File getFromResourceDataPath(String file) {
        try {
            return new File(ClassLoader.getSystemClassLoader().getResource(file).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            throw new RuntimeException(file + " cannot be accessed", e);
        }
    }

    /**
     * Liest eine Datei ein und gibt deren Inhalt zurück. Bei Fehlern wird das weitere Ausführen des Programms verhindert
     * @param file Pfad der Datei
     * @return Inhalt der Datei
     */
    public static String[] loadStrings(String file) {
        try {
            return Files.readAllLines(getFromResourceDataPath(file).toPath()).toArray(new String[] {});
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(file + " cannot be read", e);
        }
    }
}

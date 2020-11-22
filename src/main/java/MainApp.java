public class MainApp {
    /**
     * Das Muster nach welchem im Feld gesucht wird
     */
    final boolean[][] pattern = new boolean[][] {
            { true, true, true },
            { true, false, true },
            { true, false, true },
            { true, true, true }
    };

    /**
     * Das Zeichen welches in den Rohdaten einen Maulwurfbau kennzeichnet
     */
    final char symbol = 'X';

    public static void main(String[] args) {
        var app = new MainApp();
        for(int i = 0; i <= 6; i++) {
            app.findBurrows("karte" + i + ".txt");
        }
    }

    /**
     * Liest ein Feld ein, sucht nach Baulwurfbauen und gibt ihre Anzahl aus.
     * @param fileName Name der Datei mit dem Feld
     */
    private void findBurrows(String fileName) {
        boolean[][] field = parseData(fileName);

        // Durchlaufen des gesamten Spielfeldes, um nach Baulwurfbauen zu suchen
        int burrowCount = 0;
        for (int rowCounter = 0; rowCounter < field.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < field[0].length; columnCounter++) {
                if(containsPattern(field, rowCounter, columnCounter)) {
                    burrowCount++;
                }
            }
        }
        System.out.println("Baulwurfbauanzahl in " + fileName + ": " + burrowCount);
    }

    /**
     * Prüft, ob in einem Areal das Baulwurfbaumuster gefunden werden kann
     * @param field Das Feld in welchem gesucht werden soll
     * @param startRow Die Reihe in der angefangen werden soll das Muster zu prüfen
     * @param startColumn Die Spalte in der angefangen werden soll das Muster zu prüfen
     * @return Die Präsenz eines Baulwurfbaus
     */
    private boolean containsPattern(boolean[][] field, int startRow, int startColumn) {
        // Wenn, nicht das gesamte Areal geprüft werden kann, kann dort auch kein Baulwurfbau sein
        if(startRow + pattern.length > field.length || startColumn + pattern[0].length > field[0].length) {
            return false;
        }
        // Durchlaufen und Prüfen des Musters
        for(int i = 0; i < pattern.length; i++) {
            for(int j = 0; j < pattern[0].length; j++) {
                // Wenn eine Abweichung erkannt wurde, muss nicht weiter gesucht werden:
                // Es kann dort keinen Baulwurfbau geben.
                if(field[i + startRow][j + startColumn] != pattern[i][j]) {
                    return false;
                }
            }
        }
        // Wenn das Muster gefunden wurde, wird es aus dem Feld entfernt, um mögliche Überschneidungen zu verhindern
        for(int i = 0; i < pattern.length; i++) {
            for(int j = 0; j < pattern[0].length; j++) {
                // Wenn ein Feld untersucht wurde
                field[i + startRow][j + startColumn] = false;
            }
        }
        // Wenn alle Planquadrate übereinstimmen, muss es ein Baulwurfbau sein
        return true;
    }

    /**
     * @param fileName Name der Datei mit dem Feld
     * @return Ein zwei-dimensionales boolean-Array als Feld
     */
    private boolean[][] parseData(String fileName) {
        String[] file = DataUtils.loadStrings(fileName); // Einlesen der Datei
        int columns  = Integer.parseInt(file[0]); // Breite des Felds
        int rows = Integer.parseInt(file[1]); // Höhe des Feldes

        // Verarbeiten der Datei

        // Vorbereiten des Rückgabewertes
        boolean[][] data = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            // Umwandeln der Zeile in Zeichen
            char[] lineCharacters = file[i+2].toCharArray();
            for(int j = 0; j < columns; j++) {
                // Konvertierung in ein Boolean zur Vereinfachung der weiteren Verarbeitung
                data[i][j] = lineCharacters[j] == symbol;
            }
        }

        return data;
    }
}

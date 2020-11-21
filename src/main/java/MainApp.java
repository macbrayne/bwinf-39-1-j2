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
     * Das Zeichen welches in den Rohdaten einen Maulwurfhügel kennzeichnet
     */
    final char symbol = 'X';

    public static void main(String[] args) {
        var app = new MainApp();
        for(int i = 0; i <= 6; i++) {
            app.findBurrows("karte" + i + ".txt");
        }
    }

    /**
     * Liest ein Feld ein, sucht nach Baulwurfhügeln und gibt ihre Anzahl aus.
     * @param fileName Name der Datei mit dem Feld
     */
    private void findBurrows(String fileName) {
        boolean[][] field = parseData(fileName);

        int burrowCount = 0;
        for (int rowCounter = 0; rowCounter < field.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < field[0].length; columnCounter++) {
                if(containsPattern(field, rowCounter, columnCounter)) {
                    burrowCount++;
                }
            }
        }
        System.out.println("Baulwurfhügelanzahl in " + fileName + ": " + burrowCount);
    }

    private boolean containsPattern(boolean[][] data, int startRow, int startColumn) {
        if(startRow + pattern.length > data.length || startColumn + pattern[0].length > data[0].length) {
            return false;
        }
        for(int i = 0; i < pattern.length; i++) {
            for(int j = 0; j < pattern[0].length; j++) {
                if(data[i + startRow][j + startColumn] != pattern[i][j]) {
                    return false;
                }
            }
        }
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

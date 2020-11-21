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

    private void findBurrows(String fileName) {
        // einlesen des feldes
        // in dem array "feld" stehen "x" und " " wie in der eingabedatei
        //name der datei als uebergabenparameter eingeben
        boolean[][] field = getData(fileName);

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

    private boolean[][] getData(String fileName){
        String[] file = DataUtils.loadStrings(fileName);
        int columns  = Integer.parseInt(file[0]);
        int rows = Integer.parseInt(file[1]);

        boolean[][] data = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            char[] lineCharacters = file[i+2].toCharArray();
            for(int j = 0; j < columns; j++) {
                data[i][j] = lineCharacters[j] == symbol;
            }
        }

        return data;
    }
}

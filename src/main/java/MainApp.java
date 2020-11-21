public class MainApp {
    final int[][] pattern = new int[][] {
            { 1, 1, 1 },
            { 1, 0, 1 },
            { 1, 0, 1 },
            { 1, 1, 1 }
    };
    final char symbol = 'X';

    public static void main(String[] args) {
        var app = new MainApp();
        for(int i = 0; i < 6; i++) {
            app.findBurrows(i);
        }
    }

    private void findBurrows(int fileNumber) {
        // einlesen des feldes
        // in dem array "feld" stehen "x" und " " wie in der eingabedatei
        //name der datei als uebergabenparameter eingeben
        int[][] field = getData("karte" + fileNumber + ".txt");


        // feld testweise ausgeben
        int burrowCount = 0;
        for (int rowCounter = 0; rowCounter < field.length; rowCounter++) {
            for (int columnCounter = 0; columnCounter < field[0].length; columnCounter++) {
                // System.out.print(feld[rowCounter][columnCounter] == 1 ? "X" : " ");
                if(containsPattern(field, rowCounter, columnCounter)) {
                    burrowCount++;
                }
            }
            // System.out.println();
        }
        System.out.println("Dateinummer: " + fileNumber + ", Gefundene Bauten: " + burrowCount + "");

    }

    private boolean containsPattern(int[][] data, int startRow, int startColumn) {
        if(startRow + pattern.length > data.length || startColumn + pattern[0].length > data[0].length) {
            return false;
        }
        for(int i = 0; i < pattern.length - 1; i++) {
            for(int j = 0; j < pattern[0].length - 1; j++) {
                if(data[i + startRow][j + startColumn] != pattern[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] getData(String fileName){
        String[] file = DataUtils.loadStrings(fileName);
        int columns  = Integer.parseInt(file[0]);
        int rows = Integer.parseInt(file[1]);

        int[][] data = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            char[] lineCharacters = file[i+2].toCharArray();
            for(int j = 0; j < columns; j++) {
                data[i][j] = lineCharacters[j] == symbol ? 1 : 0;
            }
        }

        return data;
    }
}

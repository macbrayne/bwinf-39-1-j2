import java.util.Arrays;

public class MainApp {
    int[][] pattern = new int[][] {
            { 1, 1, 1 },
            { 1, 0, 1 },
            { 1, 0, 1 },
            { 1, 1, 1 }
    };
    char symbol = 'X';

    public static void main(String[] args) {
        var app = new MainApp();
        app.findBurrows(0);
    }

    private void findBurrows(int fileNumber) {
        // einlesen des feldes
        // in dem array "feld" stehen "x" und " " wie in der eingabedatei
        //name der datei als uebergabenparameter eingeben
        int[][] feld = getData("karte" + fileNumber + ".txt");


        // feld testweise ausgeben
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[0].length; j++) {
                System.out.print(feld[i][j] == 1 ? "X" : " ");
            }
            System.out.println();
        }

        System.out.println(containsPattern(feld, 1, 11));
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

    private boolean containsPattern(int[][] data, int startColumn, int startRow) {
        for(int i = 0; i < pattern.length - 1; i++) {
            for(int j = 0; j < pattern[0].length - 1; j++) {
                if(data[i + startColumn][j + startRow] != pattern[i][j]) {
                    System.out.println(i + " " + j);
                    return false;
                }
            }
        }
        return true;
    }
}

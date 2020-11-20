import java.util.Arrays;

public class MainApp {
    String fileName = ("data/ks_4_0");

    public static void main(String[] args) {
        var app = new MainApp();
        app.findBurrows();
    }

    private void findBurrows() {
        // einlesen des feldes
        // in dem array "feld" stehen "x" und " " wie in der eingabedatei
        //name der datei als uebergabenparameter eingeben
        String[][] feld = getData("karte1.txt");


        // feld testweise ausgeben
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[0].length; j++) {
                System.out.print(feld[i][j]);
            }
            System.out.println();
        }
    }

    private String[][] getData(String dateiname){

        int zeilen;
        int spalten;

        String[] s = DataUtils.loadStrings(dateiname);
        spalten = Integer.parseInt(s[0]);
        zeilen  = Integer.parseInt(s[1]);
        String[][] feld = new String[zeilen][spalten];
        for (int i = 0; i < zeilen; i++) {
            feld[i] = s[i+2].split("");
        }

        return feld;

    }


}

public class MainApp {
    public static void main(String[] args) {
        var app = new MainApp();
        app.findBurrows(1);
    }

    private void findBurrows(int fileNumber) {
        // einlesen des feldes
        // in dem array "feld" stehen "x" und " " wie in der eingabedatei
        //name der datei als uebergabenparameter eingeben
        String[][] feld = getData("karte" + fileNumber + ".txt");


        // feld testweise ausgeben
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < feld[0].length; j++) {
                System.out.print(feld[i][j]);
            }
            System.out.println();
        }
    }

    private String[][] getData(String fileName){
        String[] file = DataUtils.loadStrings(fileName);
        int rows  = Integer.parseInt(file[0]);
        int columns = Integer.parseInt(file[1]);


        String[][] data = new String[columns][rows];
        for (int i = 0; i < columns; i++) {
            data[i] = file[i+2].split("");
        }

        return data;

    }


}

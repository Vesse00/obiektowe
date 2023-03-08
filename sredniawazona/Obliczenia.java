package obiektowe.clientserver.sredniawazona;

import java.util.List;

public class Obliczenia {
    private List<Integer> ocenyLista;
    private List<Integer> wagaLista;

    public Obliczenia(List<Integer> ocenyLista, List<Integer> wagaLista) {
        this.ocenyLista = ocenyLista;
        this.wagaLista = wagaLista;
    }

    //oblicza srednia wazona
    public double getSredniaWaz() {
        double ocenaV = 0;
        double wagaSuma = 0;
        for (int i = 0; i < ocenyLista.size(); i++) {
            int ocena = ocenyLista.get(i);
            int waga = wagaLista.get(i);

            ocenaV += ocena*waga;
            wagaSuma += waga;
        }
        double srednia = ocenaV/wagaSuma;
        return srednia;
    }

    //oblicza odchylenie wazone
    public double getOdchylenieWaz() {
        double wagaSuma = 0;        
        double wynik = 0;
        for (int i = 0; i < ocenyLista.size(); i++) {
            int ocena = ocenyLista.get(i);
            int waga = wagaLista.get(i);

            wynik += (ocena*ocena)*waga;
            wagaSuma += waga;
        }
        double srednia = getSredniaWaz();
        System.out.println(wynik+"/"+wagaSuma+"-"+srednia);
        double res = Math.sqrt(wynik/wagaSuma - srednia); 
        return res;
    }
    //oblicza srednia arytmetyczna
    public double getSredniaArtym() {
        double sum = 0;
        int i = 0;
        for (i = 0; i < ocenyLista.size(); i++) {
            int ocena = ocenyLista.get(i);

            sum += ocena;
        }
        return sum / i;
    }

    //oblicza odchylenie normalne
    public double getOdchylenie() {
        double avg = getSredniaArtym();
        double wynik = 0;
        int i;
        for (i = 0; i < ocenyLista.size(); i++) {
            int ocena = ocenyLista.get(i);

            wynik += (ocena*ocena);
        }

        return Math.sqrt((wynik/i)-(avg*avg));
    }
}

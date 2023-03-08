package obiektowe.clientserver.sredniawazona;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            Socket socket = server.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            List<Integer> ocenyLista = new ArrayList<>();
            List<Integer> wagaLista = new ArrayList<>();
            out.writeUTF("Podaj liczby [ocena;waga] : ");
            String wiad = "OCeny: ";
            while (true) {
                String ans = in.readUTF();
                System.out.println(ans);
                

                if (ans.equals("wynik")) {
                    //z pliku obliczenia oblicza jak wynik sie wypisze to to obliczy
                    Obliczenia oblicz = new Obliczenia(ocenyLista, wagaLista);
                    double srednia = oblicz.getSredniaWaz();
                    double odchylenieWazone = oblicz.getOdchylenieWaz();
                    double odchylenie = oblicz.getOdchylenie();

                    //daje do wiad oceny z waga format: ocena [waga] ,...
                    for (int i = 0; i < ocenyLista.size(); i++) {
                        wiad += ocenyLista.get(i)+" ["+wagaLista.get(i)+"] , ";
                    }
                    //wypisuje oceny srednia waz odchylenie to wazone i to normalne 
                    out.writeUTF(wiad+"\nŚrednia ważona: "+srednia+"\nOdchylenie ważone: "+odchylenieWazone+"\nOdchylenie standardowe: "+odchylenie);
                } else {

                    //pobiera oceny i je wydupia ';'
                    String[] numbs = ans.split(";");
                    int ocena = Integer.parseInt(numbs[0]);
                    int waga = Integer.parseInt(numbs[1]);

                    //zeby oceny nie byly zle tylko dobre
                    boolean ocenaErr = true;
                    boolean wagaErr = true;
                    if(ocena >= 1 && ocena <= 6) ocenaErr = false;
                    if(waga >= 1 && waga <= 5) wagaErr = false;

                    //tu wypisuje ze blad jesli blad jest
                    if(ocenaErr && wagaErr) out.writeUTF("Ocena i waga nieprawidłowe");
                    else if(ocenaErr){
                        out.writeUTF("Dobrze wpisz ta ocene");
                    }else if(wagaErr){
                        out.writeUTF("Waga oceny nie taka");
                    } 

                    //jesli bez bledow to dodaje
                    if(!ocenaErr && !wagaErr) {
                        ocenyLista.add(ocena);
                        wagaLista.add(waga);
                        
                        //pisze co to robu
                        out.writeUTF("Dodano ocene \nNapisz kolejna ocene [ocena;waga] lub napisz 'wynik', aby wyswietlic wynik");
                    }
                }              
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
}

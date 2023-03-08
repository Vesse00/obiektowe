package obiektowe.clientserver.zad2;

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
            out.writeUTF("Jaki wyraz zapisac?: ");
            List<String> list = new ArrayList<>();

            while (true) {
                String ans = in.readUTF().toLowerCase();
                System.out.println(ans);

                if(ans.equals("exit")) break;

                if(ans.equals("wyswietl")) {
                    out.writeUTF("Lista: "+list.toString()+"\nPodaj wyraz który zapisać \'exit\' aby zakończyć program: ");
                } else {
                    //same litery
                    String alphabetOnly = "aąbcćdeęfghijklłmnńoóprsśtuwyzźżx";
                    boolean isNumber = false;
                    //tak jakby literyje wyraz xd
                    for (int i = 0; i < ans.length(); i++) {
                        String ch = Character.toString(ans.charAt(i)) ;

                        //jesli ma numer w sobie zmienie isNumber na true
                        if(alphabetOnly.indexOf(ch) == -1) {
                            isNumber = true;
                        }
                    }
                    //jeslie isNumber->true pyta o poprawne słowo
                    if(!isNumber) {
                        if(list.contains(ans)) out.writeUTF("To już zapisałem B)");
                        else {
                            out.writeUTF("Dodano słowo, jeśli chcesz wyświetlić listę słów wpisz \'wyswietl\' lub podaj inne słowo: ");
                            list.add(ans);
                        }
                    } else out.writeUTF("Mozna uzywac tylko liter! Podaj inne slowo");
                }
                System.out.println(list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package obiektowe.clientserver.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(55555);
            Socket socket = server.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            out.writeUTF("Witaj . Podaj liczby [liczba1;liczba2] : ");
            while (true) {
                String ans = in.readUTF();
                System.out.println(ans);

                String[] numbs = ans.split(";");
                int numb1 = Integer.parseInt(numbs[0]);
                int numb2 = Integer.parseInt(numbs[1]);
                
                int suma = numb1+numb2;
                int roznica = numb1-numb2;
                int iloczyn = numb1*numb2; 

                out.writeUTF("suma: "+suma+"\nroznica: "+roznica+"\niloczyn: "+iloczyn);                
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}

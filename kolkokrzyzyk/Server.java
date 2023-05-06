import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(11111);
            Socket socket = server.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            File f = new File("history.txt");
            out.writeUTF("Połączono z serwerem, wykonaj pierwszy ruch \n np[1a,b,c | 2a,b,c | 3a,b,c]");
            System.out.println("Oczekuj na pierwszy ruch klienta");
            Board board = new Board();
            String signServer;
            String signClient;
            Random rand = new Random();

            //jesli random -> 0 server gra "X", jesli random->1 server gra O
            int random = rand.nextInt(1); // 0-1
            if(random == 0) {
                signServer = "X";
                signClient = "O";
            }else{
                signServer = "O";
                signClient = "X";
            }
            while (true) {
                
                String ans = in.readUTF();
                System.out.println(ans);
                board.setSignAt(board.transferNumber(ans), signClient);

                //wynik 
                if(board.checkIfGameCanEnd()){
                    if(board.getWinner().equals(" ")){
                        System.out.println("REMIS"); 
                    }

                    System.out.println("Wygrał gracz ze znakiem: "+board.getWinner());
                    out.writeUTF("Wygrał gracz ze znakiem: "+board.getWinner());

                    //zapisuje do pliku kto wygrał
                    FileWriter fw = new FileWriter(f, true);
                    try {
                        fw.write("\nWygrał: "+board.getWinner()+" Server:"+signServer+" Client:"+signClient);
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("ERR: "+e);
                        
                    }
                    
                    socket.close();
                }

                //plansza
                System.out.println(board);



                ans = sc.nextLine();
                board.setSignAt(board.transferNumber(ans), signServer);
                if(board.checkIfGameCanEnd()){
                    if(board.getWinner().equals(" ")){
                        System.out.println("REMIS"); 
                    }

                    System.out.println("Wygrał gracz ze znakiem: "+board.getWinner());
                    out.writeUTF("Wygrał gracz ze znakiem: "+board.getWinner());
                    FileWriter fw = new FileWriter(f, true);
                    try {
                        fw.write("\nWygrał: "+board.getWinner()+" Server:"+signServer+" Client:"+signClient);
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("ERR: "+e);
                    }
                    
                    socket.close();
                }
                System.out.println(board);
                out.writeUTF(""+board);

                
            } 
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
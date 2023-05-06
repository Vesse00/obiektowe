public class Board {
    private String board[] = {" ", " ", " ", " ", " ", " ", " ", " ", " "}; 
    private String winner = "";


    //plansza
    @Override
    public String toString(){
        String res = "  | a | b | c \n"+
                     "--------------\n"+
                     "1 | "+board[0]+" | "+board[1]+" | "+board[2]+"\n"+
                     "--------------\n"+
                     "2 | "+board[3]+" | "+board[4]+" | "+board[5]+"\n"+
                     "--------------\n"+
                     "3 | "+board[6]+" | "+board[7]+" | "+board[8]+"\n";
        return res;
    }

    //wybor co kto itd
    public int transferNumber(String sign){
        if(sign.equalsIgnoreCase("1a") || sign.equalsIgnoreCase("a1")){
            return 0;
        }else if(sign.equalsIgnoreCase("1b") || sign.equalsIgnoreCase("b1")){
            return 1;
        }else if(sign.equalsIgnoreCase("1c") || sign.equalsIgnoreCase("c1")){
            return 2;
        }else if(sign.equalsIgnoreCase("2a") || sign.equalsIgnoreCase("a2")){
            return 3;
        }else if(sign.equalsIgnoreCase("2b") || sign.equalsIgnoreCase("b2")){
            return 4;
        }else if(sign.equalsIgnoreCase("2c") || sign.equalsIgnoreCase("c2")){
            return 5;
        }else if(sign.equalsIgnoreCase("3a") || sign.equalsIgnoreCase("a3")){
            return 6;
        }else if(sign.equalsIgnoreCase("3b") || sign.equalsIgnoreCase("b3")){
            return 7;
        }else if(sign.equalsIgnoreCase("3c") || sign.equalsIgnoreCase("c3")){
            return 8;
        }else{
            return -1;
        }
    }
    public boolean setSignAt(int index, String sign){
        
        if(!sign.equals("X") && !sign.equals("O")) return false;
        if(index < 0 || index > 8) return false;
        if(!board[index].equals(" ")) return false; 
        board[index] = sign;

        return true;
    }

    public String getSignAt(int index){
        if(index > 8 && index < 0) return null;
        return board[index];
    }

    //jak wygrać? ... szybko
    public boolean checkIfGameCanEnd(){
        //rzad
        if(checkLine(0, 1, 2)) return true;
        if(checkLine(3, 4, 5)) return true;
        if(checkLine(6, 7, 8)) return true;
        //kolumna
        if(checkLine(0, 3, 6)) return true;
        if(checkLine(1, 4, 7)) return true;
        if(checkLine(2, 5, 8)) return true;
        //przekatone
        if(checkLine(0, 4, 8)) return true;
        if(checkLine(2, 4, 6)) return true;
        //remis
        if(checkEmptySpaces()) return true;
        
        return false;
    }
    private boolean checkLine(int a, int b, int c){
        if(board[a].equals(" ") || board[b].equals(" ") || board[c].equals(" ")) return false;
        
        if(board[a].equals(board[b]) && board[b].equals(board[c])) {
            winner = board[a];
            return true;
        }
        return false;
    }

    private boolean checkEmptySpaces(){
        for (String position : board) {
            if(position.equals(" ")) {
                winner = " ";
                return false;
            }
        }
        return true;
    }
    
    public String getWinner() {
        return winner;
    }
}
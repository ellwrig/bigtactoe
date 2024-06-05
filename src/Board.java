public class Board {
    static String[][][] board = {
                        {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}},
                        {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}},
                        {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}}, {{"","",""},{"","",""},{"","",""}}
                        };
    static String player = "X";
    static int count = 0;

     static public String getBoard(){
         String all = "";
         for(int i = 0; i < board.length; i++){
             for(int j = 0; j < board[0].length; j++){
                 for(int k = 0; k < board[0][0].length; k++){
                     all += "|" + board[i][j][k] + "|";
                 }
                 all += "\n";
             }
           all += "\n";
         }
         return all;
    }

    static public String getSmall(int loc){
        String all = "";
            for(int j = 0; j < board[0].length; j++){
                for(int k = 0; k < board[0][0].length; k++){
                    all += "" + board[loc][j][k] + "";
                }
                all += "\n";
            }
            all += "\n";
        return all;
    }

    static public boolean isWon(int loc){
         int all = 0;
        for(int j = 0; j < board[0].length; j++){
            for(int k = 0; k < board[0][0].length; k++){
                if(!board[loc][j][k].equals("")){
                    all++;
                }
            }
        }
        System.out.println("Win " + all);
        return all == 9;
    }

    public static void swapPlayer(){
        if(player.equals("X")){
            player = "O";
        }
        else{
            player = "X";
        }
    }

    public static void setButton(int loc, int row, int col){
        board[loc][row][col] = player;
    }


    public boolean squareCheck(int row, int col, int loc){
        return !board[loc][row][col].isEmpty();
    }
    public boolean boardCheck(int loc){
        for(int row = 0; row < board[loc].length; row++){
            for(int col = 0; col < board[loc][0].length; col++){
                if(board[loc][row][col].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean allCheck(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                for(int k = 0; k < board[0][0].length; k++){
                    if(board[i][j][k].isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    static public boolean winSmall(int loc, int x, int y){
        if(board[loc][0][y].equals(player) && board[loc][1][y].equals(player) && board[loc][2][y].equals(player)){
            return true;
        }
        if(board[loc][x][0].equals(player) && board[loc][x][1].equals(player) && board[loc][x][2].equals(player)){
            return true;
        }
        if(board[loc][0][0].equals(player) && board[loc][1][1].equals(player) && board[loc][2][2].equals(player)){
            return true;
        }
        if(board[loc][0][2].equals(player) && board[loc][1][1].equals(player) && board[loc][2][0].equals(player)){
            return true;
        }
        return false;
    }
    static public boolean winBig(){
        return (winSmall(0,0,0) && winSmall(1,0,0) && winSmall(2,0,0)) || (winSmall(3,0,0) && winSmall(4,0,0) && winSmall(5,0,0)) || (winSmall(6,0,0) && winSmall(7,0,0) && winSmall(8,0,0)) || (winSmall(0,0,0) && winSmall(3,0,0) && winSmall(6,0,0)) || (winSmall(1,0,0) && winSmall(4,0,0) && winSmall(7,0,0)) || (winSmall(2,0,0) && winSmall(5,0,0) && winSmall(8,0,0)) || (winSmall(0,0,0) && winSmall(4,0,0) && winSmall(8,0,0)) || (winSmall(2,0,0) && winSmall(4,0,0) && winSmall(6,0,0));
    }
}

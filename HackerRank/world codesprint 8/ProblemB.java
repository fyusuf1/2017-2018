import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProblemB{
   public static int[][] board;
   
   public static void main(String[] args) throws FileNotFoundException{
      Scanner infile = new Scanner(new File("input.txt"));
      while(infile.hasNext()){
         int size = infile.nextInt();
         board = new int[size][2]; //col 0 = score, col 1 = ranking
         for(int x = 0; x < size; x++){
            board[x][0] = infile.nextInt();
         }
         board[0][1] = 1;
         for(int y = 1; y < board.length; y++){ //setting up rankings
            if(board[y][0] == board[y-1][0]){
               board[y][1] = board[y-1][1];
            }
            else{
               board[y][1] = board[y-1][1] + 1;
            }
         }
         System.out.println(board.length-1);
         int levels = infile.nextInt();
         
         board[board.length-1][0] = infile.nextInt();
         board[board.length-1][1] = board[board.length-2][1] + 1; //setting up alice's pos
         
         levels--;
         
         int thing = moveUp(infile.nextInt(), board.length-1);
         
         levels--;
         
         for(int a = 0; a < levels; a++){
            thing = moveUp(infile.nextInt(), thing);
         }
      }
         
   }
   
   public static int moveUp(int score, int index){  //the returned number is alice's new pos
      
      board[index][0] = score;
      int insert = -1;
      for(int x = 0; x < board.length; x++){
         if(board[x][0] == board[index][0]){
            board[index][1] = board[x][1];
            insert = x+1;
            break;
         }
         else if(board[x][0] < board[index][0]){
            board[index][1] = board[x][1];
            insert = x;
            break;
         }
      }
      
      if(insert == index){
         return index;
      }
      
      int[][] temp = new int[board.length+1][2];
      for(int y = 0; y < insert; y++){
         temp[y][0] = board[y][0];
         temp[y][1] = board[y][1];
      }
      for(int z = insert+1; z < board.length; z++){
         temp[z][0] = board[z-1][0];
         temp[z][1] = board[z-1][1];
      }
      temp[insert+1][1] = temp[insert+1][1] + 1;
      temp[insert][0] = board[index][0];
      temp[insert][1] = board[index][1];
      board = temp;
      
      temp = new int[board.length-1][2];
      for(int c = 0; c < temp.length; c++){
         temp[c][0] = board[c][0];
         temp[c][1] = board[c][1];
         }
      System.out.println(board[insert][1]);
      return insert;
   }
     
}
      
       

    
  

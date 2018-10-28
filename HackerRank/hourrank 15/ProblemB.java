import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProblemB{
   
   public static void main(String[] args) throws FileNotFoundException{
      
      Scanner infile = new Scanner(new File("input.txt"));
      int f = infile.nextInt();
      for(int x = 0; x < f; x++){
         int[] num = new int[infile.nextInt()];
         for(int y = 0; y < num.length; y++){
            num[y] = infile.nextInt();
            }
         int count = 0;
         while(num.length > 0){
            int mindex = 0;
            int max = num[0];
            for(int z = 0; z < num.length; z++){
               if(num[z] > max){
                  mindex = z;
                  max = num[z];
                  }
               }
            int[] temp = new int[mindex];
            for(int a = 0; a < temp.length; a++){
               temp[a] = num[a];
               }
            num = temp;
            count++;
            }
        int winner = count%2;
        if(winner == 1){
           System.out.println("BOB");
           }
        else if(winner == 0){
           System.out.println("ANDY");
           }
      }
      
   }
   
}
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProblemA{
   
   public static void main(String[] args) throws FileNotFoundException{
      Scanner infile = new Scanner(new File("input.txt"));
      int n = infile.nextInt();
      for(int x = 0; x < n; x++){
         int a = infile.nextInt();
         int b = infile.nextInt();
         int c = infile.nextInt();
         
         if(Math.abs(c - a) > Math.abs(c - b)){
            System.out.println("Cat B");
         }
         else if(Math.abs(c - b) > Math.abs(c - a)){
            System.out.println("Cat A");
         }
         else if(Math.abs(c-b) == Math.abs(c-a)){
            System.out.println("Mouse C");
         }
      }
        
      infile.close();
   
   } 
}
         
      

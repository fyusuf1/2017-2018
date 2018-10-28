import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProblemA{
   
   public static void main(String[] args) throws FileNotFoundException{
      Scanner infile = new Scanner(new File("input.txt"));
      while(infile.hasNext()){
         String s = infile.next();
         char[] array = s.toCharArray();
         int count = 0;
         for(int x = 0; x < array.length; x++){
            if(array[x] == '_'){
               count++;
               }
            }
         System.out.println(count+1);
         }
   }
   
}
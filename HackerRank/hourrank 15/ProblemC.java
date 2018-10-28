import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ProblemC{
   public static int count = 0;
   public static void main(String[] args) throws FileNotFoundException{
      
      Scanner infile = new Scanner(new File("input.txt"));
      int q = infile.nextInt();
      for(int x = 0; x < q; x++){
         int size = infile.nextInt();
         int[] array = new int[size];
         for(int y = 0; y < array.length; y++){
            array[y] = infile.nextInt();
         }
         /*int temp;
         for (int i = 1; i < array.length; i++) {
            for(int j = i ; j > 0 ; j--){
               if(array[j] < array[j-1]){
                  temp = array[j];
                  array[j] = array[j-1];
                  array[j-1] = temp;
               }
            }
         }*/
         permute(array, 0);
         System.out.println(count%(Math.pow(10, 9)+7));
         count = 0;
      }
      infile.close();
   }
     
     
   public static void permute(int[] array, int k){
      boolean btfl = true;
      for(int b = 0; b < array.length-1; b++){
         if(array[b] - array[b+1] == 0){
            btfl = false;
            break;
         }
      }
      
      if(btfl == true){
         count++;
      }
      
      for(int i = k; i < array.length; i++){
         swap(array, i, k);
         permute(array, k+1);
         //swap(array, k, i);
      }
      
      if(k == array.length-1){
         for(int x = 0; x < array.length; x++){
            System.out.print(array[x] + " ");
         }
         System.out.println();
      }
   }
   
   public static void swap(int[] array, int x, int y){
      int temp = array[x];
      array[x] = array[y];
      array[y] = temp;
   }
   
}
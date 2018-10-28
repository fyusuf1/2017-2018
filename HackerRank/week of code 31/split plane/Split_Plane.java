import java.util.Scanner;

public class Split_Plane {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in); 
      int q = in.nextInt();
      for(int x = 0; x < q; x++){
         int n = in.nextInt();
         int[][] lines = new int[n][4];
         for(int r = 0; r < n; r++){
            for(int c = 0; c < 4; c++){
               lines[r][c] = in.nextInt();
            }
         }
         
         in.close();
      }
   
   }

   class Line {
   
      private int xSP;
      private int ySP;
      private int xEP;
      private int yEP;
      private int nI;
      private int[][] ints;
   
      public Line(int xsp, int ysp, int xep, int yep){
         xSP = xsp;
         ySP = ysp;
         xEP = xep;
         yEP = yep;
         nI = 0;
         ints = new int[4][2];
      }
      public boolean onLine(int x, int y){
         if(xSP == xEP && x == xSP){
            return true;
         }
         if(ySP == yEP && y == ySP){
            return true;
         }
         return false;
      }
      public void addInt(int x, int y){
         ints[nI][0] = x;
         ints[nI][1] = y;
         nI++;
      }
   }
}
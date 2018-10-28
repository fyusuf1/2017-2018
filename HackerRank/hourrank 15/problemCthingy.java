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
         swap(array, k, i);
      }
      if(k == array.length-1){
         for(int x = 0; x < array.length; x++){
            System.out.print(array[x] + " ");
         }
         System.out.println();
      }
   }
   
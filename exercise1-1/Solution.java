/**
 * Exercise 1-1
 */

 public class Solution{
     public static void main(String[] args) {
         int[] array1 = {1,2,3,4,5};
         int[] array2 = {3,1,4,1,5,9,2,6};

         showReversedArray(array1);
         showReversedArray(array2);
     }

     /**
      * Reverse an array and pint it in the standard output
      */
      public static void showReversedArray(int[] a){
          for (int i = 0; i < a.length / 2; ++i) {
              a[i] = a[i]^a[a.length-i-1];
              a[a.length-i-1] = a[i]^a[a.length-i-1];
              a[i] = a[i]^a[a.length-i-1];
          }
          for (int num : a){
              System.out.print(num + " ");
          }
          System.out.println();
      }
 }
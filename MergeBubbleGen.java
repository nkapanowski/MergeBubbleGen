import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class MergeBubbleGen {

   public MergeBubbleGen() {}

   public static Integer[] createRandomArray(int size) {
      Integer[] array = new Integer[size];
      Random random = new Random();

      for (int i = 0; i < size; i++) {
         array[i] = random.nextInt(101);
      }

      return array;
   }

   public static <T extends Comparable<T>> void bubbleSort(T[] array) {
      for (int i = 0; i < array.length - 1; i++) {
         for (int j = 0; j < array.length - i - 1; j++) {
            if (array[j].compareTo(array[j + 1]) > 0) {
               T temp = array[j];
               array[j] = array[j + 1];
               array[j + 1] = temp;
            }
         }
      }
   }

   public static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right, Class<T> clazz) {
      if (left < right) {
         int middle = (left + right) / 2;
         mergeSort(array, left, middle, clazz);
         mergeSort(array, middle + 1, right, clazz);
         merge(array, left, middle, right, clazz); //match merge method
      }
   }
   @SuppressWarnings("unchecked")
   public static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right, Class<T> clazz) {
      int n1 = middle - left + 1;
      int n2 = right - middle;

      T[] leftArray = (T[]) Array.newInstance(clazz, n1);
      T[] rightArray = (T[]) Array.newInstance(clazz, n2);

      for (int i = 0; i < n1; i++) {
         leftArray[i] = array[left + i];
      }

      for (int j = 0; j < n2; j++) {
         rightArray[j] = array[middle + 1 + j];
      }

      int i = 0, j = 0;
      int k = left;

      while (i < n1 && j < n2) {
         if (leftArray[i].compareTo(rightArray[j]) <= 0) {
            array[k] = leftArray[i];
            i++;
         } else {
            array[k] = rightArray[j];
            j++;
         }
         k++;
      }

      while (i < n1) {
         array[k] = leftArray[i];
         i++;
         k++;
      }

      while (j < n2) {
         array[k] = rightArray[j];
         j++;
         k++;
      }
   }

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter the length of the array: ");
      int length = scanner.nextInt();
      Integer[] array = createRandomArray(length);

      System.out.println("Choose a sorting method:");
      System.out.println("1. Bubble Sort");
      System.out.println("2. Merge Sort");
      int choice = scanner.nextInt();

      if (choice == 1) {
         bubbleSort(array);
         System.out.println("Array sorted using Bubble Sort.");
      } else if (choice == 2) {
         mergeSort(array, 0, array.length - 1, Integer.class);
         System.out.println("Array sorted using Merge Sort.");
      } else {
         System.out.println("Choose either 1 or 2.");
      }

      System.out.println("Sorted array:");
      for (int element : array) {
         System.out.print(element + " ");
      }

      System.out.println();
      scanner.close();
   }
}

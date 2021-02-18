/* [CompareBubbleSelection.java]
 * This program is a template for a comparing sorting algorithms
 * Random numbers are generated and tested with various sorting algorithms
 */

import java.util.Random;
import java.util.Arrays;

class CompareSorts {
  
   /** Main method *******************************************
     * @param arguments
     */ 
   public static void main(String[] args) {     
     
     int data[] = generateNumberArray(5000);
     int[] tempArray; // a temp holder for data as it is passed to methods
     long startTime, endTime;
     double elapsedTime;
  
     System.out.println("Array generated: ");
     //displayArray(data);
     
     //Testing Selection Sort -----------------
     System.out.println("\nSorting with Selection sort:");
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = selectionSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
     
     //Testing Bubble Sort -----------------
     System.out.println("\nSorting with Bubble sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = bubbleSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
      //Testing Insertion Sort -----------------
     System.out.println("\nSorting with Insertion sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = insertionSort(tempArray);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
     //Testing Quick Sort -----------------
     System.out.println("\nSorting with Quick sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = quickSort(tempArray, 0, tempArray.length - 1);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
      //Testing Merge Sort -----------------
     System.out.println("\nSorting with Merge sort:");  
     tempArray = Arrays.copyOf(data,data.length); //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = mergeSort(tempArray, 0, tempArray.length - 1);
     
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");     
          
     //Testing Arrays.sort -----------------
     System.out.println("\nSorting with Arrays.sort sort:");
     tempArray = Arrays.copyOf(data,data.length);  //to keep original arrays safe from modification
     startTime = System.nanoTime();     //start time
     
     tempArray = javaBuiltInSort(tempArray);
    
     endTime = System.nanoTime();  //end time
     elapsedTime = (endTime - startTime) / 1000000.0;
     
     //displayArray(tempArray);
     System.out.println("The sort took: " + elapsedTime + "ms");
     
   } //end of main method
   
   
   /** generateNumberArray *******************************************
     * Creates a random array on integers
     * @param size of array
     * @return the generated integer array
     */
   public static int[] generateNumberArray(int numOfElements) { 
     
     int[] generated = new int[numOfElements];
     
     //add unique numbers into array in order
     for (int i = 0 ; i< generated.length;i++)
       generated[i]=i;
     
     //shuffle the numbers randomly
     Random randomNumber = new Random();
     for (int i = 0 ; i< generated.length;i++) { 
       //swap to random positions
       int temp;       
       int first = randomNumber.nextInt(generated.length);
       int second = randomNumber.nextInt(generated.length);
       temp = generated[first];
       generated[first]=generated[second];
       generated[second]=temp;
     }
     
     return generated;
   } //end of generateNumberArray
   
   
     /** displayArray *******************************************
     * Sorts a random array on integers using selection sort
     * @param the  integer array
     */
   public static void displayArray(int[] numbers) { 
     for (int i = 0 ; i< numbers.length;i++) {
       System.out.print(numbers[i]+" ");
     }
     System.out.println("");
   }
   
   /** selectionSort *******************************************
     * Sorts a random array on integers using selection sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] selectionSort(int[] numbers) { 
      //******* Your code here *************     
     int minIndex;
     int tempNum;
     
     for (int i = 0; i < numbers.length; i++) {
       minIndex = i;
       for (int j = i + 1; j < numbers.length; j++) {
         if (numbers[minIndex] > numbers[j]) {
           minIndex = j;
         }
       }
       tempNum = numbers[minIndex];
       numbers[minIndex] = numbers[i];
       numbers[i] = tempNum;
     }
     return numbers;
   }
   
   /** bubbleSort *******************************************
     * Sorts a random array on integers using bubble sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] bubbleSort(int[] numbers) {    
     //******* Your code here *************
     int temp;
     boolean changed;
     
     for (int i = 0; i < numbers.length; i++) {
       changed = false;
       for (int j = 0; j < numbers.length - 1 - i; j++) {
         if (numbers[j] > numbers[j + 1]) {
           temp = numbers[j];
           numbers[j] = numbers[j + 1];
           numbers[j + 1] = temp;
           changed = true;
         }
       }
       if (!changed) {
         break;
       }
     }
     return numbers;     
   }

   /** insertionSort *******************************************
     * Sorts a random array on integers using insertion sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] insertionSort(int[] numbers) {    
     //******* Your code here *************
     int temp;
     int index;
     
     for (int i = 1; i < numbers.length; i++) {
       temp = numbers[i];
       index = i;
       
       while (index >= 1) {
         if (numbers[index - 1] > temp) {
           numbers[index] = numbers[index - 1];
         } else {
           break;
         }
         index--;
       }
       numbers[index] = temp;
     }
     return numbers;     
   }
   
   /** mergeSort *******************************************
     * Sorts a random array on integers using merge sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] mergeSort(int[] numbers, int start, int end) {    
     //******* Your code here *************
     int middle;
     
     if (start < end) {
       middle = (start + end) / 2;
       mergeSort(numbers, start, middle);
       mergeSort(numbers, middle + 1, end);
       merge(numbers, start, middle, end);
     }
     return numbers;
   }
   
   public static void merge(int[] numbers, int start, int middle, int end) {
     int[] leftArray = new int[(middle - start) + 1];
     int[] rightArray = new int[end - middle];
     
     for (int i = 0; i < leftArray.length; i++) {
       leftArray[i] = numbers[start + i];
     }
     
     for (int i = 0; i < rightArray.length; i++) {
       rightArray[i] = numbers[middle + 1 + i];
     }
     
     int leftPointer = 0;
     int rightPointer = 0;
     
     for (int i = start; i < end + 1; i++) {       
       if ((leftPointer < leftArray.length) && (rightPointer < rightArray.length)) {
         if (leftArray[leftPointer] < rightArray[rightPointer]) {
           numbers[i] = leftArray[leftPointer];
           leftPointer++;
         }
         
         else {
           numbers[i] = rightArray[rightPointer];
           rightPointer++;
         }
       }
       
       else if (leftPointer < leftArray.length) {
         numbers[i] = leftArray[leftPointer];
         leftPointer++;
       }
     
       else if (rightPointer < rightArray.length) {
         numbers[i] = rightArray[rightPointer];
         rightPointer++;
       }
     }
   }
   
   /** quickSort *******************************************
     * Sorts a random array on integers using quick sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] quickSort(int[] numbers, int start, int end) { 
     //******* Your code here *************
     if (start >= end) {
       return numbers;
     }
     
     int pivotIndex = start + (end - start) / 2;
     int pivot = numbers[pivotIndex];
     int i = start;
     int j = end - 1;
     int temp;
     
     numbers[pivotIndex] = numbers[end];
     numbers[end] = pivot;
     
     while (i < j) {
       while ((numbers[i] < pivot) && (i < (end - 1))) {
         i++;
       }
       
       while ((numbers[j] > pivot) && (j > start)) {
         j--;
       }       
       
       if (i < j) {
         temp = numbers[i];
         numbers[i] = numbers[j];
         numbers[j] = temp;
       }       
     } 
     
     if (i == j){
       if ((numbers[i] > pivot) && (j == start)){
         pivotIndex = start;
         numbers[end] = numbers[start];
         numbers[start] = pivot;
       }
       if ((numbers[i] < pivot) && (i == end-1)){
         pivotIndex = end;
       }
     }
     
     else if (numbers[i] > pivot) {
       pivotIndex = i;
       numbers[end] = numbers[i];
       numbers[i] = pivot;
     }
     
     else if ((numbers[j] < pivot)) { 
       pivotIndex = j;
       numbers[end] = numbers[j];
       numbers[j] = pivot;
     }

     quickSort(numbers, start, pivotIndex - 1);
     quickSort(numbers, pivotIndex + 1, end);
     return numbers;
   }
   
   /** javaBuiltInSort *******************************************
     * Sorts a random array on integers using Arrays.sort
     * @param the unsorted integer array
     * @return the sorted integer array
     */
   public static int[] javaBuiltInSort(int[] numbers) { 
     Arrays.sort(numbers);  //sort
     return numbers;
   }  
}
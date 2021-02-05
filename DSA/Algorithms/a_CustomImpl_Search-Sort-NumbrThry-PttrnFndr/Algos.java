// %%%%%%%%%%%% ALGOS PERTAINING TO ====> SEARCHING (LINEAR/BINARY) / SORTING (QUICKSORT/MERGESORT) / NUMBER THEORY / PATTERN MATCHING %%%%%%%%%%%% //



import java.util.*;


//import java.util.Random;
public class Algos {


    //SIEVE OF ERATOSTHENES                                                                                             // In mathematics, the sieve of Eratosthenes is an ancient algorithm for finding all prime numbers up to any given limit.
                                                                                                                        // It does so by iteratively marking as composite (i.e., not prime) the multiples of each prime, starting with the first prime number, 2.

    public static int[] sieveOfEratosthenes(int limit) {

        char[] b = new char[limit + 1];
        b[0] = 'N';
        b[1] = 'N';
        b[2] = 'P';
        int count = 0;

        for (int i = 2; i <= limit; i++) {
            if (b[i] != 'C') {
                b[i] = 'P';
                for (int j = i * i; j <= limit; j = j + i) {                                                            // Marking all multiples of prime numbers (starting with 2) up to given limit as 'C' i.e. Composite & remaining as 'P' i.e. Prime
                    b[j] = 'C';
                }
                count++;
            }
        }

        int prime[] = new int[count];                                                                                   //Counting all primes up to given limit
        int j = 0;
        for (int i = 2; i <= limit; i++) {
            if (b[i] == 'P') {
                prime[j] = i;
                j++;
            }
        }
        return prime;
    }


//LINEAR SEARCH

    public static int linearSearch(int a[], int val) {                                                                  // Returns -1 if not found & returns position of element (i.e. index) if found

        int index=-1;
        // add logic
        for (int i = 0; i < a.length; i++) {
            if (a[i] == val) {
                index = i;
                break;
            }
        }
        return index;
    }

//BINARY SEARCH

    public static int binarySearch(int a[], int val) {                                                                  //NOTE: Binary Search can only be performed on sorted arrays  //Iteratively compares search value with array's middle element value & restricts further search to left/right half of array depending on lesser-than/greater-than.

        int u = a.length - 1;
        int l = 0;
        int mid = (u + l) / 2;

        while (a[mid] != val) {
            if (a[mid] > val) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
            mid = (u + l) / 2;
        }
        return mid;
    }


//CUSTOM SORT   (SIMILAR TO SELECTION SORT)

    public static int[] customSort(int[] arr) {                                                 //Takes around 50 seconds for sorting a 10 element array //Extremely inefficient

        int len = arr.length;
        int count = 1;

        //Checking for requirement of additional iterations
        while (count != len) {                                                                                          //Assuming entered array is unsorted

            //Iterating across each array element
            for (int i = 0; i <= (len - 2); i++) {
                //Checking each index for unsorted element
                if (arr[i] > arr[i + 1]) {
                    //SWAPPING LOGIC
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }

            //Checking for unsorted elements remaining
            for (int i = 0; i <= (len - 2); i++) {
                if (arr[i] < arr[i + 1]) {
                    count += 1;
                }
            }
        }
        return arr;
    }





//SELECTION SORT (Simplest Sorting Algo) - Basically a NESTED FOR LOOP where OUTER_LOOP=> transverses each element in array(START -> END) ||| INNER_LOOP => For each element in focus - performs comparison with every remaining element (if a[j] < a[i] then swap) & swaps if its found to be smaller . Thus progressing thru entire outer loop guarantees that for array of size n, by reaching (n-2)th index itself the array would be completely sorted in ascending order => NOTE : descending would oly required comparison statement to switch < with >.

//BUBBLE SORT -  Basically an outer ForLoop (for n-1 passes) with inner For/While Loop (for comparison of focused element with adjacent element in each pass) thru if(comparison){Swap} => Number of iterative passes made from START-> END of array. While ascending (i.e checking if a[i]>a[i+1] then swap) with each pass the largest element in the unsorted half(left) will bubble up to the highest index in the sorted half(right).

//INSERTION SORT - Basically a ForLoop (which stores each element as ->temp + its index as ->hole) containing a While loop ( which moves the hole backwards by swapping it with each backward-element along the way EITHER TILL IT REACHES ZERO OR TILL THE COMPARISON_CONDITION FAILS -> on failing hole is filled with *temp* )





// QUICK SORT (Recursion)

    //Advantages => 1)In place sorting (Constant space complexity) , 2)Randomized version of this algo avoids worst case scenarios of O(n^2) & gives a consistent O(nlogn)
    //  3)Randomized quick sort implementation is usually what is provided in most language libraries as the default sorting algo


    public static int[] quickSort(int[] a, int l, int h) {

        if (l < h) {
            //Partition given array around pivot
            int j = partition2(a, l, h);
            //Run same logic (partition) recursively on either partitioned array
            quickSort(a, l, j - 1);
            quickSort(a, j + 1, h);
        }
        //Return array with every element individually pivoted (& therefore sorted)
        return a;
    }



    //Partitioning array around Pivot and returning Pivot position
    public static int partition(int[] arr, int low, int high) {

        //Setting Pivot as first element   ( Other implementations also possible with pivot being picked at the end/ from the middle. **In this impl, choose to pick from start only***. Thus while dealing with sorted arrays(to avoid worst case scenario), after generating random position we swap that position's element with first element to set it as pivot instead of changing the pivot position itself )
        int pivot = arr[low];
        //Setting iterators at each end of array
        int i = low;
        int j = high;
        int big = 0;
        int smol = 0;

        //Till i and j meet,
        while (i <= j) {

            //iterate i along one direction to check for all values > pivot
            if (arr[i] > pivot) {
                big = arr[i];
            } else {
                i++;
                big = arr[i];
            }
            //iterate j along opposite direction to check for all values <= pivot
            if (arr[j] <= pivot) {
                smol = arr[j];
            } else {
                j--;
                smol = arr[j];
            }

            //If the above conditions satisfied at any given instance => swap both
            if (arr[i] > pivot && arr[j] <= pivot && i < j) {
                arr[i] = smol;
                arr[j] = big;
            }
        }

        //When i and j finally meet, swap pivot at that point.
        arr[low] = arr[j];
        arr[j] = pivot;

        //return pivot position
        return j;
    }



    //ALTERNATIVE PARTITION FUNCTION
    public static int partition2(int[] arr, int low, int high) {

        //Setting Pivot as LAST element
        int pivot = arr[high];
        //Setting iterator only at front of array
        int pIndex = low;

        for (int i = low; i <= high - 1; i++) {
            if (arr[i] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[pIndex];
                arr[pIndex] = temp;
                pIndex++;
            }
        }
        int temp = arr[pIndex];
        arr[pIndex] = arr[high];
        arr[high] = temp;
        return pIndex;
    }


                                                                                                                        //****NOTE : IN CASES WHERE THE INPUT ARRAY IS ALREADY SORTED, THE QUICKSORT CAN EXHIBIT POOR PERFORMANCE AS WORST CASE time complexity is O(n^2),****
                                                                                                                        // In such cases the array can be deliberately unsorted using Randomizer code block before every partition & Sort => which randomly chooses a position in the array, and swaps it with the first element of the array before designating it as the pivot

                                                                                                                        //***RANDOMIZED QUICK SORT***
                                                                                                                        //    public static int[] quickSort(int[] a, int l, int h) throws ArrayIndexOutOfBoundsException{
                                                                                                                        //        int len=a.length;
                                                                                                                        //        if (l < h) {
                                                                                                                        //
                                                                                                                        //            //Deliberately unsorting array by moving element selected at random position & swapping it with element at l (pivot position)  [ this is done to minimize occurrence of worst case scenarios with O(n^2) -> when array is already sorted ]
                                                                                                                        //                                                                      // java.util.Random random=new Random();
                                                                                                                        //                                                                       // int rand=random.ints(1,low,high+1).findFirst().getAsInt();
                                                                                                                        //            int random = java.util.concurrent.ThreadLocalRandom.current().nextInt(l, h);
                                                                                                                        //            int temp = a[random];
                                                                                                                        //            a[random] = a[l];
                                                                                                                        //            a[l] = temp;
                                                                                                                        //
                                                                                                                        //            int j = partition(a, l, h);
                                                                                                                        //
                                                                                                                        //            quickSort(a, l, j - 1);
                                                                                                                        //            quickSort(a, j + 1, h);
                                                                                                                        //
                                                                                                                        //        }
                                                                                                                        //        return a;
                                                                                                                        //    }
                                                                                                                        //}

                                                                                                                        // NOTE : SINCE ABOVE IMPLEMENTATION ( applies randomizer at every level which we wanna avoid -> randomization once at outermost level is sufficient ) GIVING ARRAY OUT OF BOUNDS EXCEPTION -> We can replace the logic using stack data structure where the level of recursion/function call can be tracked and we can selectively apply if condition on the randomizer code block to be used when level==1;





    //MERGE SORT
    //Advantages of Merge Sort => 1) Handle Large Lists  2) Suitable for Linked Lists 3) External Sorting (can handle large merges without needing equivalent RAM) 4) Stable (relative ordering of similar elements are preserved)
    //Drawbacks => 1) Not in-place sorting - space complexity = O(n) i.e. extra space required proportional to size of input (requires an extra array to hold merged results[i.e unlike quicksort (in-place algo), in mergesort elements not swapped in existing array itself])     2) In some cases (with small input sizes), insertion/bubble sort perform faster.




    public static int[] mergeSort(int[] a) {

        int len = a.length;
        //If array/sub-array CANNOT be broken down further
        if (len == 1) ;
            //If array/sub-array CAN be broken down further,
        else {
            //Break down along middle
            int mid = len / 2;

            //Create arrays to store either side
            int[] l = new int[mid];
            int[] r = new int[len - mid];

            //Fill em up
            for (int i = 0; i <= mid - 1; i++) {
                l[i] = a[i];
            }
            for (int i = mid; i <= len - 1; i++) {
                r[i - mid] = a[i];
            }

            //Recursive call to break down all child left[] sub=arrays (Until resolution=1 element per array)
            mergeSort(l);
            //Once left sub-arrays completely resolved (till 1 element), it exits above recursive method (thru len==1) & moves to recursive method below [which then tries to break down its complement right side which again returns out (thru len==1)]
            mergeSort(r);
            //Both single element left and right sub-arrays (bottom-most) are sent into the MERGE FUNCTION where the SORTING OCCURS (based on comparison) whilst the parent array is filled resulting in a SORTED PARENT.
            //This process recursively repeated at each level (stack-frame) yields the finished SORTED array at the topmost main level.
            merge(a, l, r);
        }
        //Return completely sorted array at main level.
        return a;
    }



    private static int[] merge(int a[], int left[], int right[]) {

        //Initializing iterators and their bounds for each array
        int i = 0;
        int j = 0;
        int k = 0;
        int leftlen = left.length;
        int rightlen = right.length;

        while (i < leftlen && j < rightlen) {
            //Iterate to Compare each element on either side & fill into main array (in sorted order)
            if (left[i] < right[j]) {           //USE < or > symbol in the comparison to obtain ascending or descending order respectively
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }
        //Filling up any remaining elements on either side into the main array [once the other is exhausted]
        while (i < leftlen) {
            a[k] = left[i];
            i++;
            k++;
        }
        while (j < rightlen) {
            a[k] = right[j];
            j++;
            k++;
        }

        return a;
    }



                                                                                                                     /*   public static int patternFinder(String pattern, String data) {

                                                                                                                            //LOCAL VARIABLES
                                                                                                                            int plen = pattern.length();
                                                                                                                            int dlen = data.length();

                                                                                                                            int matchIndex = -1;                                                                                            //returns -1 if no match found
                                                                                                                            int j = 0;                                                                                                      //initializing pattern-array index

                                                                                                                            //CONVERT INPUT STRINGS -> CHAR ARRAYS
                                                                                                                            char[] p = pattern.toCharArray();
                                                                                                                            char[] d = new char[dlen]; for (int k = 0; k < dlen; k++) { d[k] = data.charAt(k); }


                                                                                                                            //PATTERN MATCHING LOGIC
                                                                                                                            for (int i = 0; i < dlen; i++) {
                                                                                                                                while (j < plen && d[i] == p[j]) {
                                                                                                                                    i++;
                                                                                                                                    j++;
                                                                                                                                    if (j == plen) { matchIndex = i - plen; break; }
                                                                                                                                }
                                                                                                                                j=0;
                                                                                                                                i--;
                                                                                                                            }
                                                                                                                            return matchIndex;
                                                                                                                        }


                                                                                                                        public static ArrayList<Integer> patternsFinder(String pattern, String data) {

                                                                                                                            //LOCAL VARIABLES
                                                                                                                            int plen = pattern.length();
                                                                                                                            int dlen = data.length();

                                                                                                                            int count=0;
                                                                                                                            ArrayList<Integer> matchIndex = new ArrayList<>();                                                       //returns -1 if no match found
                                                                                                                            int j = 0;                                                                                                      //initializing pattern-array index

                                                                                                                            //CONVERT INPUT STRINGS -> CHAR ARRAYS
                                                                                                                            char[] p = pattern.toCharArray();
                                                                                                                            char[] d = new char[dlen]; for (int k = 0; k < dlen; k++) { d[k] = data.charAt(k); }


                                                                                                                            //PATTERN MATCHING LOGIC
                                                                                                                            for (int i = 0; i < dlen; i++) {
                                                                                                                                while (j < plen && d[i] == p[j]) {
                                                                                                                                    i++;
                                                                                                                                    j++;
                                                                                                                                    if (j == plen ) { count++; matchIndex.add(i - plen); i-- ; }
                                                                                                                                }
                                                                                                                            }//assuming j resets to 0 here
                                                                                                                            if(count==0) { matchIndex.add(-1); }
                                                                                                                            return matchIndex;
                                                                                                                        }*/


//PATTERN MATCHING ALGORITHMS



    public static int patternFinder(String pattern, String data) {                                                      // FINDS IF INPUT STRING PATTERN EXISTS WITHIN PROVIDED STRING DATA & RETURNS THE INDEX IF FOUND
                                                                                                                        // Can be used as a pattern matching Algorithm like for finding a particular gene (pattern) in a DNA Strand (data)

        //LOCAL VARIABLES
        int plen = pattern.length();
        int dlen = data.length();

            int matchIndex = -1;                                                                                        //returns -1 if no match found
            int j = 0;                                                                                                  //initializing pattern-array index

            //CONVERT INPUT STRINGS -> CHAR ARRAYS  (Via Alternative Ways)
            char[] p = pattern.toCharArray();
            char[] d = new char[dlen]; for (int k = 0; k < dlen; k++) { d[k] = data.charAt(k); }

        //PATTERN MATCHING LOGIC
        for (int i = 0; i < dlen; i++) {
            if (d[i] == p[j])
            {
                if (j < plen - 1) { j++; }
                else {
//                    count++; j=0;
                    if (i > 0) { matchIndex = i+1 - plen; /*matchIndex.add(i-plen)*/}
                    break; }
            }
            else {
                if(j>0){i--;}
                j=0;
            }
        }
        return matchIndex;
    }




    public static ArrayList<Integer> patternsFinder(String pattern, String data) {

        //LOCAL VARIABLES
        int plen = pattern.length();
        int dlen = data.length();

        ArrayList<Integer> matchIndex = new ArrayList<>();                                                              //returns -1 if no match found
        int count=0;
        int j = 0;                                                                                                      //initializing pattern-array index

        //CONVERT INPUT STRINGS -> CHAR ARRAYS
        char[] p = pattern.toCharArray();
        char[] d = new char[dlen]; for (int k = 0; k < dlen; k++) { d[k] = data.charAt(k); }


        //PATTERN MATCHING LOGIC
        for (int i = 0; i < dlen; i++) {
            if (d[i] == p[j])
            {
                if (j < plen - 1) { j++; }
                else {
                    count++; j=0;
                    if (i > 0) { matchIndex.add(i+1 - plen); }
                }
            }
            else {
                if(j>0){i--;}
                j=0;
            }
        }
        if(count==0) { matchIndex.add(-1); }
        return matchIndex;
    }
}




class bruh{

    public static void main(String[] args){

        //CONCEPT OF CREATING COMPLEX TYPE ARRAYS
        Mem a[]=new Mem[10];
        Mem b=new Mem(2,"Bob",240);
        Mem c=new Mem(3,"Psych",350);
        a[0]=b;
        a[1]=c;

//                                                                          //LINKED-LIST
//                                                                                    LinkedList linky=new LinkedList<Mem>();
//                                                                                    linky.add(b);
//                                                                                    linky.add(new Mem(3,"Psych",350));
//                                                                                    Iterator itr=linky.iterator();
//                                                                                    while(itr.hasNext()){ if((Mem)itr.next()==b){ System.out.println("found it"); } }

        for (int i = 0; i < a.length; i++) {
            if(a[i]!=null){
            System.out.println(a[i].toString());
            }
        }


        String gene= "ACCGT";
        String DNA="ACCGTCTGCTACGTTTGCTGACCGTAACGTCGACCGT";


//        int index=Algos.patternFinder(gene,DNA);
//
//        System.out.println("The gene with the pattern  ");
//        for (int i = index; i < (index+gene.length()); i++) {
//            System.out.print(DNA.charAt(i)+"  ");
//        }
//        System.out.println("is present in the given DNA at index "+index);


        ArrayList<Integer> index=Algos.patternsFinder(gene,DNA);

        System.out.println("The gene with the pattern  ");
        System.out.println(gene);
        System.out.println("is present in the given DNA at index "+index);





/*
//        int a[]={2,1,4,7,9,3,6,3,2,0};

//        int a[]={11,23,45,67,83,93,100,120,134,157};
//        int a[]={120,45,11,157,67,100,83,23,134,93};

//        int a[]=new int[]{218,321,430,468,1247,1568,1571,1627,1664,1752,1839,1845,2187,2380,2514,2555,2626,2706,2747,3273,3676,4216,4279,4643,4707,4831,4967,5008,5469,5570,5857,6442,6547,6553,6762,7072,7386,7401,7735,7836,8261,8657,8705,8962,9452,9644,9653,9659,9717,9784};
        int a[] = new int[]{1752, 4643, 1845, 2626, 8657, 4279, 9717, 9452, 6553, 430, 9653, 2706, 7401, 9784, 1571, 4707, 6442, 2187, 8962, 7836, 7735, 2555, 2747, 1664, 218, 9659, 3273, 2380, 1839, 5857, 7072, 4967, 1568, 3676, 7386, 1627, 8705, 5469, 468, 1247, 8261, 4216, 6547, 5570, 5008, 321, 9644, 2514, 6762, 4831};

//        int a[]=new int[]{35,2,8};

        long startTime = System.currentTimeMillis();
//        int pos=Algos.binarySearch(a,134);                                                                          //SEARCH ALGO - Takes 1 millisecond to find position of given element in a 10 element array
//        int [] b=Algos.customSort(a);                                                                               //SORT ALGO - Takes around 50 seconds for sorting a 10 element array //Extremely inefficient
//        int [] b=Algos.quickSort(a,0,49);                                                                           //SORT ALGO - Takes 2 milliseconds for sorting a 10 element array     // Efficient
//        int[] b = Algos.mergeSort(a);                                                                                 //SORT ALGO - Takes 1 milliseconds for sorting a 10 element array     //Extremely Efficient
        int [] b=Algos.sieveOfEratosthenes(20000);
        long endTime = System.currentTimeMillis();

//        for (int i = 0; i <=(a.length-1); i++) {
//            System.out.println("BinarySearch => Index of given value in the array is "+pos);
//          }


//   for (int i = 0; i <=(a.length-1); i++) {
//      System.out.println("CustomSort => Sorted array value at position "+(i+1)+" is = "+b[i]);
//      }

//  System.out.println("QuickSorted Array = ");
//        System.out.print("{");
//        for (int i = 0; i <=(a.length-1); i++) { System.out.print(b[i]+","); }
//        System.out.println("}");

//    System.out.println("MergeSorted Array = ");
//        System.out.print("{");
//        for (int i = 0; i <=(a.length-1); i++) { System.out.print(b[i]+","); }
//        System.out.println("}");

        System.out.println("Sieve produces "+b.length+" primes in the given limit = ");
        System.out.print("{");
        for (int i = 0; i <=(b.length-1); i++) { System.out.print(b[i]+","); }
        System.out.println("}");

        System.out.println("Took "+(endTime-startTime) +" ms");*/


    }
}


















// Binary Search Extensive test

//class bruh{

//    public static int randomFill(){
//
//        Random rand = new Random();
//        int randomNum = rand.nextInt();
//        return randomNum;
//    }

//    public static void main(String[] args) {

//        int a[]=new int[]{1752,
//            4643,
//            1845,
//            2626,
//            8657,
//            4279,
//            9717,
//            9452,
//            6553,
//            430,
//            9653,
//            2706,
//            7401,
//            9784,
//            1571,
//            4707,
//            6442,
//            2187,
//            8962,
//            7836,
//            7735,
//            2555,
//            2747,
//            1664,
//            218,
//            9659,
//            3273,
//            2380,
//            1839,
//            5857,
//            7072,
//            4967,
//            1568,
//            3676,
//            7386,
//            1627,
//            8705,
//            5469,
//            468,
//            1247,
//            8261,
//            4216,
//            6547,
//            5570,
//            5008,
//            321,
//            9644,
//            2514,
//            6762,
//            4831};
//
////        for(int i=0;i<a.length;i++)
////        {
////            a[i] = randomFill();
////           System.out.println("The value at position "+i+" of the array is =" +a[i]+ "\n");
////
////        }
//
//    int x=Algos.binarySearch(a,1664);
//        System.out.println(x);

//}
//}







////Sorting entire array        //NOTE: CANT USE THIS WITH RECURSION CONCEPT since child method calls will not know their own bounds
//
//    public static int partition(int[] arr){
//
//        int len=arr.length;
//        int pivot = arr[0];
//        int i = 0;
//        int j = len-1;
//        int big = 0;
//        int smol = 0;
//
//        while (i < j) {
//
//            if (arr[i] > pivot) {
//                big = arr[i];
//            } else {
//                i++;
//            }
//
//            if (arr[j] <= pivot) {
//                smol = arr[j];
//            } else {
//                j--;
//            }
//
//            if (arr[i] > pivot && arr[j] <= pivot) {
//                arr[i] = smol;
//                arr[j] = big;
//            }
//        }
//
//        arr[0]=arr[j];
//        arr[j]=pivot;
//
//        return j;
//    }
//
//    public static int[] quickSort(int[] a){

//        int j = partition(a);
                    //Replace these with non-recursive logic -
                        //        quickSort(a, 0, j - 1);
                        //        quickSort(a, j + 1, len-1);
//        return a;

//    }
//
//}


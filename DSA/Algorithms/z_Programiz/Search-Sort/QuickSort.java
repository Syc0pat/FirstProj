// Quick sort in Java

/*Quicksort is implemented when

the programming language is good for recursion
time complexity matters
space complexity matters




------Quick Sort Algorithm------

quickSort(array, leftmostIndex, rightmostIndex)
  if (leftmostIndex < rightmostIndex)
    pivotIndex <- partition(array,leftmostIndex, rightmostIndex)
    quickSort(array, leftmostIndex, pivotIndex)
    quickSort(array, pivotIndex + 1, rightmostIndex)

partition(array, leftmostIndex, rightmostIndex)
  set rightmostIndex as pivotIndex
  storeIndex <- leftmostIndex - 1
  for i <- leftmostIndex + 1 to rightmostIndex
  if element[i] < pivotElement
    swap element[i] and element[storeIndex]
    storeIndex++
  swap pivotElement and element[storeIndex+1]
return storeIndex + 1

*/


import java.util.Arrays;

class QuickSort {

  // Function to partition the array on the basis of pivot element
  int partition(int array[], int low, int high) {
    
    // Select the pivot element
    int pivot = array[high];
    int i = (low - 1);

    // Put the elements smaller than pivot on the left and 
    // greater than pivot on the right of pivot
    for (int j = low; j < high; j++) {
      if (array[j] <= pivot) {
        i++;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }
    int temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;
    return (i + 1);
  }

  void quickSort(int array[], int low, int high) {
    if (low < high) {

      // Select pivot position and put all the elements smaller 
      // than pivot on left and greater than pivot on right
      int pi = partition(array, low, high);
      
      // Sort the elements on the left of pivot
      quickSort(array, low, pi - 1);

      // Sort the elements on the right of pivot
      quickSort(array, pi + 1, high);
    }
  }

  // Driver code
  public static void main(String args[]) {
    int[] data = { 8, 7, 2, 1, 0, 9, 6 };
    int size = data.length;
    QuickSort qs = new QuickSort();
    qs.quickSort(data, 0, size - 1);
    System.out.println("Sorted Array in Ascending Order: ");
    System.out.println(Arrays.toString(data));
  }
}







/*# Quick sort in Python


# Function to partition the array on the basis of pivot element
def partition(array, low, high):

    # Select the pivot element
    pivot = array[high]
    i = low - 1

    # Put the elements smaller than pivot on the left and greater 
    #than pivot on the right of pivot
    for j in range(low, high):
        if array[j] <= pivot:
            i = i + 1
            (array[i], array[j]) = (array[j], array[i])

    (array[i + 1], array[high]) = (array[high], array[i + 1])

    return i + 1


def quickSort(array, low, high):
    if low < high:

        # Select pivot position and put all the elements smaller 
        # than pivot on left and greater than pivot on right
        pi = partition(array, low, high)

        # Sort the elements on the left of pivot
        quickSort(array, low, pi - 1)

        # Sort the elements on the right of pivot
        quickSort(array, pi + 1, high)


data = [8, 7, 2, 1, 0, 9, 6]
size = len(data)
quickSort(data, 0, size - 1)
print('Sorted Array in Ascending Order:')
print(data)*/
import java.util.*;

public class SortingArrays {
	int myArray[]; // The array to be sorted
	int num[]; // For BucketSort

	SortingArrays() {
	}

	SortingArrays(int arr[]) {
		myArray = new int[arr.length];
		num = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			myArray[i] = arr[i];
			num[i] = 0;
		}
	}

	void printAll() {
		for (int i = 0; i < myArray.length; i++)
			System.out.println(myArray[i]);
	}

	void sortUsingMerge() {
		mergeSort(myArray, 0, myArray.length - 1);
	}

	void sortUsingQuick() {
		quicksort(myArray, 0, myArray.length - 1);
	}

	void sortUsingBucket() {
		bucketSort(myArray, num);
	}

	static void merge(int arr[], int a, int mid, int b) {
		// PRE: the array portions A[a..mid-1]
		// and A[mid..b] are sorted
		// POST: The array portion A[a..b] are sorted
		if (a <= b && mid >= a && mid <= b) {
			int[] tA = new int[b - a + 1];
			int i = a;
			int j = mid;
			int ti = 0;
			// INVARIANT for all the loops:
			// tA[0.. (i-a) + (j-mid) -1] is the sorted merge
			// of A[a..i-1] and A[mid..j-1]
			while (i < mid && j < b + 1) {
				if (arr[i] > arr[j]) {
					tA[ti] = arr[j];
					j++;
				} else {
					tA[ti] = arr[i];
					i++;
				}
				ti++;
			}
			if (i < mid) {
				for (; i < mid; i++) {
					tA[ti] = arr[i];
					ti++;
				}
			} else {
				for (; j < b + 1; j++) {
					tA[ti] = arr[j];
					ti++;
				}
			}
			// copy back to A
			for (int k = a; k <= b; k++) {
				arr[k] = tA[k - a];
			}
		}
	}

	static void mergeSort(int arr[], int first, int last) {
		// POST: sorts the array portion A[first..last]
		if (first < last) { // just swap the nodes;
			mergeSort(arr, first, (first + last) / 2);
			mergeSort(arr, (first + last) / 2 + 1, last);
			// PRE for MERGE: the array portions A[first..(first+last)/2]
			// and A[(first+last)/2+1..last] are sorted
			merge(arr, first, (first + last) / 2 + 1, last);
		}
	}

	int partition(int arr[], int first, int last) {
		// Take A[first] as the item to be placed in correct
		// sorted order. Set pivotIndex to that item and return it.

		int p = arr[first]; // Initialisation to satisfy the invariant.
		int lastS1 = first;
		int firstUnknown = first + 1;
		int temp;

		while (firstUnknown <= last) {
			// INVARIANT:
			// The items in myArray[first+1..LastS1] are all strictly less than p,
			// and the items in myArray[LastS1+1.. FirstUnknown-1] at least p meaning >= p
			if (arr[firstUnknown] < p) { // Case: need to add to S1
				lastS1++; // Increment LastS1
				temp = arr[firstUnknown];
				arr[firstUnknown] = arr[lastS1];
				arr[lastS1] = temp;
				firstUnknown++;
			} else {
				firstUnknown++;
			} // Case: Need to add to S2
		}
		temp = arr[first];
		arr[first] = arr[lastS1];
		arr[lastS1] = temp;
		firstUnknown++; // I tested I few data it looks we do not need this line
		return lastS1;
	}

	void swap(char arr[], int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	void quicksort(int arr[], int first, int last) {
		if (first < last) {
			// Always choose p to be A[first].
			int pivotIndex = partition(arr, first, last); // update pivotIndex;
			quicksort(arr, first, pivotIndex - 1);
			quicksort(arr, pivotIndex + 1, last);
		}
	}

	void bucketSort(int arr[], int num[]) {
		// For simplicity we assume that A consists of integers, 0... Num.length-1
		// PRE: The items in A must have value no more that Num.length-1
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			// INVARIANT: # entries value k in A[0..i-1] is Num[k].
			// Declare a structure for the �buckets�

			int k = arr[i]; // Increment k'th bucket
			num[k]++;
		}

		/*
		 * int m= 0; //This is very complicated
		 * int sofar= 0;
		 * for (int j = 0; j < Num.length; j++){
		 * // Invariant: A[0..sofar] is sorted,
		 * // containing all the elements up to j
		 * for(; m< sofar + Num[j]; m++)
		 * A[m]= j;
		 * sofar= m;
		 * }
		 */

		// Reconstruct the array in sorted order
		int m = 0; // Index for placing elements in A
		for (int j = 0; j < num.length; j++) { // Iterate over each possible number
			for (int count = 0; count < num[j]; count++) { // Place Num[j] copies of j in A
				arr[m] = j;
				m++;
			}
		}

	}

	public static void main(String[] args) {

		int[] arr = { 5, 8, 12, 7, 3, 48, 33, 23};

		SortingArrays st = new SortingArrays(arr);
		System.out.println();
		System.out.print("arr before: " + Arrays.toString(arr));
		System.err.println();

		
		st.sortUsingMerge(); // Choose a sorting algorithm. //This is for merge sort testing

		System.out.println();
		System.out.println();
		System.out.print("arr after: " + Arrays.toString(st.myArray));
		System.out.println();

		/*
		 * st.sortUsingQuick();
		 * 
		 * System.out.println();
		 * System.out.println();
		 * System.out.print("After: ");
		 * for(int i=0; i<A.length; i++) {
		 * System.out.print("  " + st.myArray[i]);
		 * }
		 */

		/*
		 * st.sortUsingBucket();
		 * System.out.println();
		 * System.out.print("After: ");
		 * for(int i= 0; i< A.length; i++)
		 * System.out.print("  "+st.myArray[i]+"");
		 */
	}

}

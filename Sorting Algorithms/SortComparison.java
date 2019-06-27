//testing
// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Amy Pierce
 * @version HT 2019
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		// todo: implement the sort
		double swap;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					swap = a[j];
					a[j] = a[j - 1];
					a[j - 1] = swap;
				}
			}
		}
		return a;
	}// end insertionsort

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		// todo: implement the sort
		Sort(a, 0, a.length - 1);
		return a;

	}

	 static void Sort(double[] arr, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);
			Sort(arr, begin, partitionIndex - 1);
			Sort(arr, partitionIndex + 1, end);
		}
	}

	 static int partition(double[] arr, int begin, int end) {
		double pivot = arr[end];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (arr[j] <= pivot) {
				i++;

				double swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}
		double swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;

		return i + 1;
	}// end quicksort

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	 
	   public static double[] mergeSortIterative(double[] a) {
	        int n = a.length;
	        double[] aux = new double[n];
	        for (int len = 1; len < n; len *= 2) {
	            for (int lo = 0; lo < n-len; lo += len+len) {
	                int mid  = lo+len-1;
	                int hi = Math.min(lo+len+len-1, n-1);
	                merge(a, aux, lo, mid, hi);
	            }
	        }
	        return a;
	    }
	   
	   private static void merge(double[] a, double[] aux, int lo, int mid, int hi) {

	        
	        for (int k = lo; k <= hi; k++) {
	            aux[k] = a[k]; 
	        }

	        int i = lo, j = mid+1;
	        for (int k = lo; k <= hi; k++) {
	            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
	            else if (j > hi)               a[k] = aux[i++];
	            else if ((aux[j]<aux[i])) a[k] = aux[j++];
	            else                           a[k] = aux[i++];
	        }

	    }// end mergesortIterative

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	
	
	 static double[] mergeSortRecursive(double[] array) {
		
		if (array.length > 1) {
			int mid = array.length / 2;
			double[] left = new double[mid];
			for (int i = 0; i < mid; i++) {
				left[i] = array[i];
			}
			double[] right = new double[array.length - mid];
			for (int i = mid; i < array.length; i++) {
				right[i - mid] = array[i];
			}
			mergeSortRecursive(left);
			mergeSortRecursive(right);
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < left.length && j < right.length) {
				if (left[i] < right[j]) {
					array[k] = left[i];
					i++;
				} else {
					array[k] = right[j];
					j++;
				}
				k++;
			}
			while (i < left.length) {
				array[k] = left[i];
				i++;
				k++;
			}
			while (j < right.length) {
				array[k] = right[j];
				j++;
				k++;
			}
		}
		return array;
	}// end mergesortRecursive

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		// todo: implement the sort
		double swap;
		double smallest;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				smallest = a[i];
				if (a[j] < smallest) {
					swap = a[i];
					a[i] = a[j];
					a[j] = swap;
				}
			}
		}
		return a;
	}// end selectionsort

	//public static void main(String[] args) {

		// todo: do experiments as per assignment instructions
		
	//}

	public static String toString(double a[]) {
		String result = "";
		for (int i = 0; i < a.length; i++) {
			result += a[i] + ", ";
		}
		return result;

	}
}// end class

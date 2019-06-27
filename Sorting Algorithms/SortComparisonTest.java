import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Amy Pierce
 *  @version HT 2019
 */
/*										          time in ms
 *
 * 						| insert	|	quick	| merge recursive	|	merge iterative 	|	selection	|
 * ----------------------------------------------------------------------------------------------------------
 * 10 random			|0.4230		|0.0077		|0.0146				|0.0714					|0.0216			|
 * ----------------------------------------------------------------------------------------------------------
 * 100 random			|0.9314		|0.2093		|0.2730				|0.4268					|0.8244			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 random			|7.1998		|2.3567		|2.5862				|2.9299					|9.8849			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 few unique		|10.5388	|3.7280		|2.2730				|3.3379					|8.7581			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 nearly ordered	|3.3926		|0.9687		|4.1505				|2.3270					|9.4476			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 reverse order	|11.6590	|1.7758		|2.2277				|3.1825					|8.8767			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 sorted			|0.4474		|0.6664		|3.1115				|1.8953					|7.2395			|
 * ----------------------------------------------------------------------------------------------------------
 * 
 * Questions:
 * a)Which of the sorting algorithms does the order of input have an impact on? Why? 
 * 		Insertion sort and quick sort are impacted the by the order as they have worse running times when sorting nearly sorted and reverse order arrays
 * 		compared to sorting random arrays. They are all effected to a certain extent but quick and insertion are impacted the most.
 * 
 * 
 * 
 * b)Which algorithm has the biggest difference between the best and worst performance, based
	on the type of input, for the input of size 1000? Why?
			insertion sort has the biggest difference between worst and best run times.... best was 0.4474ms with the 1000 sorted input file
			and the worst was 11.6590ms with the 1000 reverse order input file. That is  difference of 11.2116 ms
			
 * c)Which algorithm has the best/worst scalability, i.e., the difference in performance time
	based on the input size? Please consider only input files with random order for this answer. 
		Best:Quick sort as for all 3 random input files the run times are within 2ms which means it is good at scaling
		Worst:Selection sort for the 3 random input files the runntime gets longer as the file size increases
		
 * d)Did you observe any difference between iterative and recursive implementations of merge
	sort?
	There is a slight difference in the run times of recursive and iterative merge sorts. However the difference is not anything of significance,
	recursive is faster some times and iterative is faster for others but only by 1 millisecond in each case
		
   e)Which algorithm is the fastest for each of the 7 input files? 
   	---------------------------------------------
  	|input file			|Fastest algorithm		|
   	---------------------------------------------
   	|random10			|the input size is so small that the difference between the algorithms runtime are the same	|
   	---------------------------------------------
	|random100			|the input size is so small that the difference between the algorithms runtime are the same	|
	---------------------------------------------
	|random1000			|Quick sort				|
	 --------------------------------------------
	|fewUnique1000		|Merge sort recursive	|
	 --------------------------------------------
	|nearlyOrdered1000	|Quick sort				|
	 --------------------------------------------
	|reverse1000		|Merge sort recursive	|
	 --------------------------------------------
	|sorted1000			|Insertion iterative	|
	 --------------------------------------------
 */
@RunWith(JUnit4.class)
public class SortComparisonTest 
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new SortComparison();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty()
	{
		String expected="";

		double[] test= {};
		double[] result=SortComparison.insertionSort(test);
		assertEquals(expected,SortComparison.toString(result));

		double[] test2= {}; 	
		double[] result2=SortComparison.quickSort(test2);
		assertEquals(expected,SortComparison.toString(result2));

		double[] test3= {};   	
		double[] result3=SortComparison.mergeSortIterative(test3);
		assertEquals(expected,SortComparison.toString(result3));

		double[] test4= {};
		double[] result4=SortComparison.mergeSortRecursive(test4);
		assertEquals(expected,SortComparison.toString(result4));

		double[] test5= {};
		double[] result5=SortComparison.selectionSort(test5);
		assertEquals(expected,SortComparison.toString(result5));
	}


	// TODO: add more tests here. Each line of code and ech decision in Collinear.java should
	// be executed at least once from at least one test.

	// ----------------------------------------------------------

	@Test
	public void testInsertionSort()
	{
		double[] test= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.insertionSort(test);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(test));
	}

	@Test
	public void testSelertionSort()
	{
		double[] test= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.selectionSort(test);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(test));
	}

	@Test
	public void testQuickSort()
	{
		double[] test= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.quickSort(test);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(test));
	}

	@Test
	public void testMergeSortIterative()
	{
		double[] test= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.mergeSortIterative(test);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(test));
	}

	@Test
	public void testMergeSortRecursive()
	{
		double[] test= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.mergeSortRecursive(test);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(test));
	}
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 * @throws IOException 
	 *
	 */

	public static void main(String[] args) throws IOException   
	{

		double[] random10=readFile(10,"numbers10.txt");
		double[] random100=readFile(100,"numbers100.txt");
		double[] random1000=readFile(1000,"numbers1000.txt");
		double[] fewUnique1000=readFile(1000,"numbers1000Duplicates.txt");
		double[] nearlyOrdered1000=readFile(1000,"numbersNearlyOrdered1000.txt");
		double[] reverse1000=readFile(1000,"numbersReverse1000.txt");
		double[] sorted1000=readFile(1000,"numbersSorted1000.txt");

		print("random 10: ",runTests(random10));
		print("random 100: ",runTests(random100));
		print("random 1000: ",runTests(random1000));
		print("few unique 1000: ",runTests(fewUnique1000));
		print("nearly Ordered 1000: ",runTests(nearlyOrdered1000));
		print("reverse 1000: ",runTests(reverse1000));
		print("sorted 1000: ",runTests(sorted1000));

	}
	public static double[] runTests(double[] arr) {
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
		double[]cpy4=new double[arr.length];
		System.arraycopy(arr,0,cpy4,0,arr.length);
		double[]cpy5=new double[arr.length];
		System.arraycopy(arr,0,cpy5,0,arr.length);
		double[] results=new double[5];
		results[0]=testInsertion(cpy1);
		results[1]=testQuick(cpy2);
		results[2]=testMergeRecursive(cpy3);
		results[3]=testMergeIterative(cpy4);
		results[4]=testSelection(cpy5);
		
		return results;
	}
	public static double testInsertion(double[] arr) {
		
		double result = 0;
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
		
			double start1=System.nanoTime();
			SortComparison.insertionSort(cpy1);
			double end1=System.nanoTime();
			result+=(end1-start1);
			double start2=System.nanoTime();
			SortComparison.insertionSort(cpy2);
			double end2=System.nanoTime();
			result+=(end2-start2);
			double start3=System.nanoTime();
			SortComparison.insertionSort(cpy3);
			double end3=System.nanoTime();
			result+=(end3-start3);
			double x=result/1000000;
		return (x/3);

	}
	public static double testQuick(double[] arr) {
		double result = 0; 
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
			double start1=System.nanoTime();
			SortComparison.quickSort(cpy1);
			double end1=System.nanoTime();
			result+=(end1-start1);
			double start2=System.nanoTime();
			SortComparison.quickSort(cpy2);
			double end2=System.nanoTime();
			result+=(end2-start2);
			double start3=System.nanoTime();
			SortComparison.quickSort(cpy3);
			double end3=System.nanoTime();
			result+=(end3-start3);
			double x=result/1000000;
		return (x/3);

	}
	public static double testSelection(double[] arr) {
		double result = 0;
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
			double start1=System.nanoTime();
			SortComparison.selectionSort(cpy1);
			double end1=System.nanoTime();
			result+=(end1-start1);
			double start2=System.nanoTime();
			SortComparison.selectionSort(cpy2);
			double end2=System.nanoTime();
			result+=(end2-start2);
			double start3=System.nanoTime();
			SortComparison.selectionSort(cpy3);
			double end3=System.nanoTime();
			result+=(end3-start3);
			double x=result/1000000;
		return (x/3);

	}
	
	
	public static double testMergeIterative(double[] arr) {
		double result = 0;
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
			double start1=System.nanoTime();
			SortComparison.mergeSortIterative(cpy1);
			double end1=System.nanoTime();
			result+=(end1-start1);
			double start2=System.nanoTime();
			SortComparison.mergeSortIterative(cpy2);
			double end2=System.nanoTime();
			result+=(end2-start2);
			double start3=System.nanoTime();
			SortComparison.mergeSortIterative(cpy3);
			double end3=System.nanoTime();
			result+=(end3-start3);
			double x=result/1000000;
		return (x/3);
	}
	public static double testMergeRecursive(double[] arr) {
		double result = 0;
		double[]cpy1=new double[arr.length];
		System.arraycopy(arr,0,cpy1,0,arr.length);
		double[]cpy2=new double[arr.length];
		System.arraycopy(arr,0,cpy2,0,arr.length);
		double[]cpy3=new double[arr.length];
		System.arraycopy(arr,0,cpy3,0,arr.length);
			double start1=System.nanoTime();
			SortComparison.mergeSortRecursive(cpy1);
			double end1=System.nanoTime();
			result+=(end1-start1);
			double start2=System.nanoTime();
			SortComparison.mergeSortRecursive(cpy2);
			double end2=System.nanoTime();
			result+=(end2-start2);
			double start3=System.nanoTime();
			SortComparison.mergeSortRecursive(cpy3);
			double end3=System.nanoTime();
			result+=(end3-start3);
			double x=result/1000000;
		return (x/3);
	}



	public static double[] readFile(int length,  String string) throws IOException {
		File f=new File(string);
		Scanner in =  new Scanner(f);
		double[] list = new double[length];
		int x = 0;
		while(in.hasNextDouble()){
			list[x++] = in.nextDouble();
		}
		in.close();
		return list;
	}

	public static void print(String string,double[] arr) {
		System.out.print(string);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.print("\n");
	}


}

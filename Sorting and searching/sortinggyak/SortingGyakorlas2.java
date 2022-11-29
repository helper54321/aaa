package sortinggyak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingGyakorlas2 {

	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		int[] intArray2 = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 }; // 7.-hez (counting)
		int[] intArray3 = { 4725, 4586, 1330, 8792, 1594, 5729 }; // 8.-hoz (radix)
		int[] intArray4 = { 54, 46, 83, 66, 95, 92, 43 }; // 9.-hez (bucket)
		
		
		int[] intArray5 = {-22, -15, 1, 7, 20, 35, 55};
		
		System.out.println(binarySearch(intArray5, -22));
		
//		bubble(intArray);
//		selection(intArray);
//		insertion(intArray);
//		shell(intArray);
//		mergeSort(intArray, 0, intArray.length);
//		quick(intArray, 0, intArray.length);
//		counting(intArray2, 1, 10);
//		radix(intArray3, 10, 4);
//		bucket(intArray4);

		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}
	}
	
	private static int binarySearch(int[] input, int element) {
		
		int start = 0;
		int end = input.length;
		
		while(start < end) {
			int mid = (start + end) / 2;
			
			if(input[mid] == element) {
				return mid;
			}else if(input[mid] < element) {
				start = mid + 1;
			}else{
				end = mid;
			}
		}
		
		return -1;
	}
	

	
	
}

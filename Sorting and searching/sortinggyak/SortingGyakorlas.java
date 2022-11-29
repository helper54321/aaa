package sortinggyak;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortingGyakorlas {

	public static void main(String[] args) {
		int[] intArray = { 20, 35, -15, 7, 55, 1, -22 };
		int[] intArray2 = { 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 }; // 7.-hez (counting)
		int[] intArray3 = { 4725, 4586, 1330, 8792, 1594, 5729 }; // 8.-hoz (radix)
		int[] intArray4 = { 54, 46, 83, 66, 95, 92, 43 }; // 9.-hez (bucket)
		
//		bubbleSort(intArray);
//		selectionSort(intArray);
//		insertionSort(intArray);
//		shellSort(intArray);
//		countingSort(intArray2);
//		radixSort(intArray3, 10, 4);
//		bucketSort(intArray4);
		
//		mergeSort(intArray, 0, intArray.length);
		quickSort(intArray, 0, intArray.length);
		
		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}
	}
	
	
	private static void bubbleSort(int[] items) {
		
		for(int i = 0; i < items.length; i++) {
			for(int j = 1; j < items.length - i; j++) {
				if(items[j] < items[j - 1]) {
					int temp = items[j];
					items[j] = items[j - 1];
					items[j - 1] = temp;
				}
			}
		}
	}
	
	private static void selectionSort(int[] items) {
		
		for(int i = 0; i < items.length; i++) {
			int max = 0;
			for(int j = 0; j < items.length - i; j++) {
				if(items[j] > items[max]) {
					max = j;
				}
			}
			
			int temp = items[items.length - i - 1];
			items[items.length - i - 1] = items[max];
			items[max] = temp;
		}
	}
	
	
	//20, 35, -15, 7, 55, 1, -22
	//-15, 20, 35, 7
	private static void insertionSort(int[] items) {
	
		for(int i = 1; i < items.length; i++) {
			int nextItem = items[i];
			
			int j = i;
			
			for(j = i; j > 0; j--) {
				if(items[j - 1] < nextItem) {
					break;
				}
				
				items[j] = items[j - 1];
			}
			
			items[j] = nextItem;
		}
	}
	
	//20, 35, -15, 7, 55, 1, -22
	//nem saját
	private static void shellSort(int[] items) {
		
		for(int gap = items.length / 2; gap > 0; gap /= 2) {
			
			for(int i = gap; i < items.length; i++) {
				
				int newItem = items[i];
				
				int j = i;
				
				while(j >= gap && items[j - gap] > newItem) {
					items[j] = items[j - gap];
					j -= gap;
				}
				
				items[j] = newItem;
			}
		}
	}
	
	//{ 2, 5, 9, 8, 2, 8, 7, 10, 4, 3 }
	private static void countingSort(int[] items) {
		int min = items[0];
		int max = items[0];
		
		for(int i = 1; i < items.length; i++) {
			if(items[i] < min) min = items[i];
			if(items[i] > max) max = items[i];
		}
		
		int[] helper = new int[max - min + 1];

		for(int i = 0; i < items.length; i++) {
			int current = items[i];
			helper[current - min] = helper[current - min] + 1;
		}
		
		int index = 0;
		for(int i = 0; i < helper.length; i++) {
			while(helper[i] > 0) {
				items[index] = min + i;
				index++;
				helper[i] = helper[i] - 1;
			}
		}
	}
	
	//4725, 4586, 1330, 8792, 1594, 5729
	//Nem hibátlan, de majdnem jó
	private static void radixSort(int[] items, int numOfPossibleValues, int length) {
		
		int[] temp = new int[items.length];
		
		//Minden számjegyre, azért megyünk végig (visszafelé haladva, tehát elõször az utolsó számjegy, úgy van megírva a getdigit)
		for(int i = 0; i < length; i++) {
			int[] helper = new int[numOfPossibleValues];
			
			for(int j = 0; j < items.length; j++) {
				int mod = getDigit(items[j], i, numOfPossibleValues);
				helper[mod] = helper[mod] + 1;
			}
			
			int[] modified = new int[numOfPossibleValues];
			
//			modified[0] = helper[0];
			modified = helper;
			
			for(int j = 1; j < helper.length; j++) {
				modified[j] = helper[j] + modified[j - 1];
			}
			
			for(int j = items.length - 1; j >= 0; j--) {
				int mod = getDigit(items[j], i, numOfPossibleValues);
				
				modified[mod] = modified[mod] - 1;
				temp[modified[mod]] = items[j];
			}
		}
		System.arraycopy(temp, 0, items, 0, items.length);
	}
	
	private static int getDigit(int number, int indexFromEnd, int numOfPossibleValues) {
		return (number / (int) (Math.pow(numOfPossibleValues, indexFromEnd)) % numOfPossibleValues);
	}
	
	private static void bucketSort(int[] items) {
		List<Integer>[] buckets = new List[10];
		
		for(int i = 0; i < 10; i++) {
			buckets[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < items.length; i++) {
			int mod = items[i] / 10;
			buckets[mod].add(items[i]);
		}
		
		for(List l : buckets) {
			Collections.sort(l);
		}
		
		int index = 0;
		for(int i = 0; i < buckets.length; i++) {
			for(int j = 0; j < buckets[i].size(); j++) {
				items[index] = buckets[i].get(j);
				index++;
			}
		}
	}
	
	
	private static void quickSort(int[] input, int start, int end) {
		
		if(end - start < 2) return;
		
		int pivotIndex = partition(input, start, end);
		quickSort(input, start, pivotIndex);
		quickSort(input, pivotIndex + 1, end);
	}
	
	private static int partition(int[] input, int start, int end) {
		
		int pivot = input[start];
		int i = start;
		int j = end;
		
		while(i < j) {
			
			while(i < j && input[--j] >= pivot);
			
			if(i < j) {
				input[i] = input[j];
			}
			
			while(i < j && input[++i] <= pivot);
			
			if(i < j) {
				input[j] = input[i];
			}
		}
		
		input[j] = pivot;
		return j;
	}
	
	
	
	
	
}

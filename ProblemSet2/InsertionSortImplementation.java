import java.util.Arrays;

public class InsertionSortImplementation { 
	
	public static void main(String[] args) {
		
		String[] A = {"x","zebra","cat","dog","apple", "abe" , "aaa", "zz"};
		
		InsertionSort(A);
		
		System.out.println(Arrays.toString(A));
		
	}
	
	public static void InsertionSort(String[] A) {
		
		for(int j = 1; j < A.length; j++) {
			
			String key = A[j];
			int i = j - 1;
			while(i >= 0 && A[i].compareTo(key) >= 0) {
				
				A[i+1] = A[i];
				i--;
			}
			A[i+1] = key;
		}
	}

}

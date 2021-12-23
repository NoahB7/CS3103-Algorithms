import java.util.Arrays;

public class MergeSortImplementation {

	public static void main(String[] args) {

		String[] A = {"zebra","cat","dog","apple", "abe" , "aaa"};

		mergeSort(A, 0, A.length - 1);

		System.out.println(Arrays.toString(A));

	}

	public static void mergeSort(String[] A, int p, int r) {

		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q); 
			mergeSort(A, q + 1, r); 
			merge(A, p, q, r); 
		}
	}

	public static void merge(String[] A, int p, int q, int r) {

		int n1 = q - p + 1;
		int n2 = r - q;

		String[] L = new String[n1 + 1];
		String[] R = new String[n2 + 1];

		for (int i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = A[q + j + 1];
		}

		L[n1] = "zzz";
		R[n2] = "zzz";

		int i = 0, j = 0;

		for (int k = p; k <= r; k++) {

			if (L[i].compareTo(R[j]) < 0) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

}

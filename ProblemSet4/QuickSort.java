import java.util.Arrays;

public class QuickSort {
	
	public String[] A = {"1","2","6","2","6","8","3","4"};
	
	public static void main(String[] args) {
		
		QuickSort B = new QuickSort();
		B.QuickSort(B.A, 0, B.A.length-1);
		
		System.out.println(Arrays.toString(B.A));
		
	}
	
	
	public void QuickSort(String[] A, int p, int r) {
		if(p < r) {
			int q = Partition(A,p,r);
			QuickSort(A,p,q-1);
			QuickSort(A,q+1,r);
		}
	}
	
	public int Partition(String[] A,int p, int r){
		String x = A[selectPivot(A,p,r)];
		Swap(r,(p+r)/2);
		int i = p-1;
		for(int j = p; j < r; j++) {
			if(A[j].compareTo(x) <= 0) {
				i = i+1;
				Swap(i,j);
			}
		}
		Swap(i+1,r);
		return i + 1;
	}
	
	public static int selectPivot(String[] A,int p, int r) {
		int q = (p+r)/2;
		if(A[p].compareTo(A[r]) >= 0 && A[r].compareTo(A[q]) >= 0) {
			return r;
		} else if(A[p].compareTo(A[q]) >= 0 && A[q].compareTo(A[r]) >= 0) {
			return q;
		} else {
			return p;
		}
	}
	
	public void Swap(int x, int y) {
		String hold = A[x];
		A[x] = A[y];
		A[y] = hold;
		
	}


}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UASort {
	
	/*  Student Name:	Noah Buchanan
	 *  Username:		ua505		<--- this needs to be correct
	 *  Date:			September 16
	 *  Class:          CS 3103 - Algorithm Design
	 *  Filename:       UASort.java
	 */

	String[] A;

	public static void main(String[] args) throws IOException {

		if (args.length < 4) {
			System.out.println(
					"Incorrect arguements, try: java UASort (input destination) (output destination) (numeric/text) (ascending/descending)");

		} else {

			if (args[2].equals("numeric") && args[3].equals("descending")) {

				UASort s = new UASort();
				File[] x = new File(args[0]).listFiles();
				
				for (int i = 0; i < x.length; i++) {
					
					s.loadData(x[i].getAbsolutePath());
					s.QuickSortNumerics(s.A, 0, s.A.length - 1);
					s.Flip(s.A);
					File y = new File("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
					y.createNewFile();
					s.writeData("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
				}
			} else if (args[2].equals("numeric") && args[3].equals("ascending")) {

				UASort s = new UASort();
				File[] x = new File(args[0]).listFiles();
				
				for (int i = 0; i < x.length; i++) {
					
					s.loadData(x[i].getAbsolutePath());
					s.QuickSortNumerics(s.A, 0, s.A.length - 1);
					File y = new File("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
					y.createNewFile();
					s.writeData("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
				}
			} else if (args[2].equals("text") && args[3].equals("descending")) {
				
				UASort s = new UASort();
				File[] x = new File(args[0]).listFiles();
				
				for (int i = 0; i < x.length; i++) {
					
					s.loadData(x[i].getAbsolutePath());
					s.QuickSortText(s.A, 0, s.A.length - 1);
					s.Flip(s.A);
					File y = new File("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
					y.createNewFile();
					s.writeData("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
				}
			} else if (args[2].equals("text") && args[3].equals("ascending")) {
				
				UASort s = new UASort();
				File[] x = new File(args[0]).listFiles();
				
				for (int i = 0; i < x.length; i++) {
					
					s.loadData(x[i].getAbsolutePath());
					s.QuickSortText(s.A, 0, s.A.length - 1);
					File y = new File("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
					y.createNewFile();
					s.writeData("/home/ua505/ps4/" + args[1] + "/" + x[i].getName());
				}
			} else {
				System.out.println("Arguements not recognized");
			}

		}
	}

	public void writeData(String output) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(output));

		for (int i = 0; i < A.length; i++) {
			bw.write(A[i] + " ");
		}
		bw.close();
	}

	public void loadData(String input) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(input));

		String line = br.readLine();

		A = line.split(" ");
		
		br.close();
	}

	public void QuickSortText(String[] A, int p, int r) {
		if (p < r) {
			int q = PartitionText(A, p, r);
			QuickSortText(A, p, q - 1);
			QuickSortText(A, q + 1, r);
		}
	}

	public int PartitionText(String[] A, int p, int r) {
		String x = A[selectPivotText(A, p, r)];
		Swap(r, selectPivotText(A, p, r));
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j].compareTo(x) <= 0) {
				i = i + 1;
				Swap(i, j);
			}
		}
		Swap(i + 1, r);
		return i + 1;
	}

	public void QuickSortNumerics(String[] A, int p, int r) {
		if (p < r) {
			int q = PartitionNumerics(A, p, r);
			QuickSortNumerics(A, p, q - 1);
			QuickSortNumerics(A, q + 1, r);
		}
	}

	public int PartitionNumerics(String[] A, int p, int r) {
		int x = Integer.parseInt(A[selectPivotNumerics(A, p, r)]);
		Swap(r, selectPivotNumerics(A, p, r));
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (Integer.parseInt(A[j]) <= x) {
				i = i + 1;
				Swap(i, j);
			}
		}
		Swap(i + 1, r);
		return i + 1;
	}

	public int selectPivotNumerics(String[] A, int p, int r) {
		int q = (p + r) / 2;
		if (Integer.parseInt(A[p]) < Integer.parseInt(A[r]) && Integer.parseInt(A[r]) < Integer.parseInt(A[q])) {
			return r;
		} else if (Integer.parseInt(A[p]) < Integer.parseInt(A[q]) && Integer.parseInt(A[q]) < Integer.parseInt(A[r])) {
			return q;
		} else {
			return p;
		}
	}

	public int selectPivotText(String[] A, int p, int r) {
		int q = (p + r) / 2;
		if (A[p].compareTo(A[r]) >= 0 && A[r].compareTo(A[q]) >= 0) {
			return r;
		} else if (A[p].compareTo(A[q]) >= 0 && A[q].compareTo(A[r]) >= 0) {
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

	public void Flip(String[] x) {
		String temp;
		for (int i = 0; i < x.length / 2; i++) {
			temp = x[i];
			x[i] = x[x.length - 1 - i];
			x[x.length - 1 - i] = temp;
		}
	}

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class UASpellCheck {

	String[] dictionary;

	public UASpellCheck(String filename) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line = "";
		String concat = "";

		while ((line = br.readLine()) != null) {

			concat += line + " ";
		}
		String[] dictionary = concat.split(" ");
		this.dictionary = dictionary;

	}

	public static void main(String[] args) throws Exception {

		UASpellCheck c = new UASpellCheck(args[0]);

		ArrayList<String> list = c.checkSpelling(args[1]);

		System.out.println("Word\t\tDistance");
		System.out.println("--------------  ---------------");

		for (int i = 0; i < list.size(); i++) {

			System.out.println(c.editDistance(args[1], list.get(i), args[1].length(), list.get(i).length()));

		}

	}

	public boolean isValid(String word) {

		boolean valid = false;

		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i]))
				valid = true;

		}

		return valid;
	}

	public ArrayList<String> checkSpelling(String word) {

		ArrayList<String> list = new ArrayList<String>();

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < dictionary.length; i++) {

			int val = editDistance(word, dictionary[i], word.length(), dictionary[i].length());
			if (val < min) {

				list.clear();
				list.add(dictionary[i]);
				min = val;
			} else if (val == min) {

				list.add(dictionary[i]);
			}
		}
		if (word.length() >= 1) {
			return list;
		} else {
			return null;
		}

	}

	public int editDistance(String s1, String s2, int x, int y) {

		int table[][] = new int[x + 1][y + 1];

		for (int i = 0; i <= x; i++) {

			for (int j = 0; j <= y; j++) {

				if (i == 0) {

					table[i][j] = j;
				} else if (j == 0) {

					table[i][j] = i;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

					table[i][j] = table[i - 1][j - 1];
				} else {

					if (minimum(table[i - 1][j - 1], table[i - 1][j], table[i][j - 1]) == table[i - 1][j - 1]) {

						table[i][j] = minimum(table[i - 1][j - 1], table[i - 1][j], table[i][j - 1]) + 2;

					} else {

						table[i][j] = minimum(table[i - 1][j - 1], table[i - 1][j], table[i][j - 1]) + 1;

					}

				}
			}
		}
		return table[x][y];
	}

	public int minimum(int a, int b, int c) {

		if (a <= b && a <= c) {
			return a;
		} else if (b <= a && b <= c) {
			return b;
		} else {
			return c;
		}

	}

}

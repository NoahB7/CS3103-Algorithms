import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UASpellCheckGraphical {

	String[] dictionary;

	public UASpellCheckGraphical(String filename) throws Exception {

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

		UASpellCheckGraphical c = new UASpellCheckGraphical(args[0]);
		int redo = 1;
		redo = JOptionPane.showConfirmDialog(null, "Would you like to use the Noah Buchanan's custom spell checker™?");
		if(redo == 0) {

			String message = JOptionPane.showInputDialog("Enter a word you would like to check against our dictionary below \n ");
			redo = 1;
			int lengthToAvoid = -1;

			while (redo != 0) {

				ArrayList<String> list = c.checkSpelling(message, lengthToAvoid);
				StringBuilder display = new StringBuilder();
				lengthToAvoid = c.editDistance(message, list.get(0), message.length(), list.get(0).length());

				display.append("Did you mean any of these word(s)?\nEdit distance for these words: " + lengthToAvoid
						+ "\n--------------------------------------------------\n ");

				for (int i = 0; i < list.size(); i++) {

					if (i == list.size() - 1) {
						display.append(list.get(i));
					} else {
						display.append(list.get(i) + ", ");
					}
					if (i % 10 == 0 && i != 0) {
						display.append("\n");
					}

				}

				redo = JOptionPane.showConfirmDialog(null, display);
				if (redo == 1) {
					JOptionPane.showMessageDialog(null,
							"Uh oh, lets try and fix that, we will recommend a new set of words excluding all previous words given");

				}

			}
			JOptionPane.showMessageDialog(null, "Thank you for using our spell checker");
		} else {
			redo = JOptionPane.showConfirmDialog(null, "Are you sure?");
			
			if(redo == 1) {

				String message = JOptionPane.showInputDialog("Enter a word you would like to check against our dictionary below \n ");
				int lengthToAvoid = -1;

				while (redo != 0) {

					ArrayList<String> list = c.checkSpelling(message, lengthToAvoid);
					StringBuilder display = new StringBuilder();
					lengthToAvoid = c.editDistance(message, list.get(0), message.length(), list.get(0).length());

					display.append("Did you mean any of these word(s)?\nEdit distance for these words: " + lengthToAvoid
							+ "\n--------------------------------------------------\n ");

					for (int i = 0; i < list.size(); i++) {

						if (i == list.size() - 1) {
							display.append(list.get(i));
						} else {
							display.append(list.get(i) + ", ");
						}
						if (i % 10 == 0 && i != 0) {
							display.append("\n");
						}

					}

					redo = JOptionPane.showConfirmDialog(null, display);
					if (redo == 1) {
						JOptionPane.showMessageDialog(null,
								"Uh oh, lets try and fix that, we will recommend a new set of words excluding all previous words given");

					}

				}
				JOptionPane.showMessageDialog(null, "Thank you for using our spell checker");
			}
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

	public ArrayList<String> checkSpelling(String word, int avoid) {

		ArrayList<String> list = new ArrayList<String>();

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < dictionary.length; i++) {

			int val = editDistance(word, dictionary[i], word.length(), dictionary[i].length());
			if (val < min && val > avoid) {

				list.clear();
				list.add(dictionary[i]);
				min = val;
			} else if (val == min && val > avoid) {

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

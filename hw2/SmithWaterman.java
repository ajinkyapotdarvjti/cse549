import java.io.*;
import java.util.*;

public class SmithWaterman {

	//public static int MAXLEN = 11;
	public static int MATCH = 0;
	public static int INSERT = 1;
	public static int DELETE = 2;
	
	class Cell {
		int cost;
		int parent;
	}

	String s = "";
	String t = "";
	Cell[][] m;

	public List<Integer> goalCell;

	public SmithWaterman(String s, String t) {

		this.s = s;
		this.t = t;

		m = new Cell[s.length()+1][t.length()+1];

		for (int i=0; i<(s.length()+1); i++) {
			for (int j=0; j<((t.length()+1)); j++) {
				m[i][j] = new Cell();
			}
		}

		goalCell = new ArrayList<Integer>();

	}
	
	public void doSmithWaterman() {

		int i, j, k;
		int opt[] = new int[3];

		for (i = 0; i <= s.length(); i++) {
			for (j = 0; j <= t.length(); j++) {
				cell_init(i, j);
			}
		}

		for (i = 1; i <= s.length(); i++) {
			for (j = 1; j <= t.length(); j++) {
				//System.out.println(i + ", " + j);
				int sIndex = i-1;
				int tIndex = j-1;
				opt[MATCH] = m[i - 1][j - 1].cost + match(s.charAt(sIndex), t.charAt(tIndex));
				opt[INSERT] = m[i][j - 1].cost + indel(t.charAt(tIndex));
				opt[DELETE] = m[i - 1][j].cost + indel(s.charAt(sIndex));

				m[i][j].cost = 0;
				m[i][j].parent = -1;
				for (k = MATCH; k <= DELETE; k++) {
					if (opt[k] > m[i][j].cost) {
						m[i][j].cost = opt[k];
						m[i][j].parent = k;
					}
				}
			}
		}

		goalCell = goal_cell();
	}

	private void cell_init(int i, int j) {
		m[i][j].cost = 0;
		m[i][j].parent = -1;
	}

	int match(char c, char d) {
		if (c == d)
			return 5;
		else
			return -4;
	}

	int indel(char c) {
		return -4;
	}

	public List<Integer> goal_cell() {

		int x = 0, y = 0;
		int max = 0;

		for(int i=0; i<(s.length()+1); i++) {
			for (int j=0; j<((t.length()+1)); j++) {
				if (m[i][j].cost > max) {
					x = i;
					y = j;
					max = m[i][j].cost;
				}
			}
		}

		List<Integer> result = new ArrayList<Integer>();
		result.add(x);
		result.add(y);

		return result;

	}

	private String reconstructPath(String s, String t, int i, int j) {
		if (m[i][j].parent == -1) {
			return "";
		}

		if (m[i][j].parent == MATCH) {
			return reconstructPath(s,t,i-1,j-1) + (s.charAt(i-1)==t.charAt(j-1)?"M":"S");
			//alignment += (s.charAt(i-1)==t.charAt(j-1)?"M":"S");
			//return;
		}
		if (m[i][j].parent == INSERT) {
			return reconstructPath(s,t,i,j-1) + "I";
			//alignment += "I";
			//return;
		}
		if (m[i][j].parent == DELETE) {
			return reconstructPath(s,t,i-1,j) + "D";
			//alignment += "D";
			//return;
		}
		return null;
	}

	public void displayCostMatrix() {

		System.out.println("Cost Matrix: ");

		for(int i=0; i<((s.length()+1)); i++) {
			for (int j=0; j<(t.length()+1); j++) {
				System.out.print(m[i][j].cost + "\t");
			}
			System.out.println();
		}
	}

	public void displayParentMatrix() {

		System.out.println("Parent Matrix: ");

		for(int i=0; i<(s.length()+1); i++) {
			for (int j=0; j<(t.length()+1); j++) {
				System.out.print(m[i][j].parent + "\t");
			}
			System.out.println();
		}
	}

	public int getAlignmentScore(int i, int j) {
		return m[i][j].cost;
	}

	public static void main(String[] arg) throws FileNotFoundException {

		String str1;
		String str2;

		// read two Genome Sequences
		/*
		str1 = "aaabb";
		str2 = "aaabbbccc";
		*/

		Scanner scanner1 = new Scanner(new FileInputStream(
					"human_seq"));
		Scanner scanner2 = new Scanner(new FileInputStream(
					"chimpanzee_seq"));
		try {

			str1 = " " + scanner1.nextLine();
			str2 = " " + scanner2.nextLine();
		} finally {
			scanner1.close();
			scanner2.close();
		}

		str1 = str1.substring(0, 600);
		str2 = str2.substring(0, 600);

		System.out.println("s = " + str1);
		System.out.println("t = " + str2);

		// Find the similarity
		SmithWaterman sw = new SmithWaterman(str1, str2);
		sw.doSmithWaterman();

		//sw.displayCostMatrix();
		//sw.displayParentMatrix();

		System.out.println("alignment for complete String = " + sw.reconstructPath(str1, str2, sw.goalCell.get(0), sw.goalCell.get(1)));
		System.out.println("alignment score = " + sw.getAlignmentScore(sw.goalCell.get(0), sw.goalCell.get(1)));

	}
}

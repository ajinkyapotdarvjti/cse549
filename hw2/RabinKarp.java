import java.util.*;

public class RabinKarp {
	private List<String> symbols = new ArrayList<String>();
	private long patternHash;
	private int m;
	private long q;
	private int d;
	//private long RM;
	private String pattern;

	public RabinKarp(String pat) {

		symbols.add("a");
		symbols.add("c");
		symbols.add("g");
		symbols.add("t");

		pattern=pat;
		d = 4;
		m = pattern.length();
		q = 1000037;

		//RM = 1;
		//for (int i = 1; i <= M-1; i++)
		//	RM = (R * RM) % Q;

		patternHash = hash(pattern);
	}

	private long getHashAbsolute(String key, int i) {
		long h = 0;
		for (int j = 0; j < m; j++)
			h = (d * h + symbols.indexOf(key.charAt(i+j)+""));

		return h;
	}

	private long hash(String str) {
		long h = getHashAbsolute(str, 0);
		return h%q;
	}

	private boolean check(String txt, int i) {
		for (int j = 0; j < m; j++)
			if (pattern.charAt(j) != txt.charAt(i + j))
				return false;
		return true;
	}

	public int search(String text) {


		int n = text.length();
		if (n < m)
			return -1;

		long textHash = hash(text);

		if ((patternHash == textHash) && check(text, 0))
			return 0;


		for (int i = m; i < n; i++) {
			textHash = d*getHashAbsolute(text, i-m) + symbols.indexOf(text.charAt(i)+"") - (long)((Math.pow((double)d, (double)m)))*symbols.indexOf(text.charAt(i-m)+"");
			textHash = textHash % q;

			int offset = i - m + 1;
			if ((patternHash == textHash) && check(text, offset))
				return offset;
		}

		return -1;
	}

        public  static String getRandomSequence(int length) {

                char[] bases = {'a', 'c', 't', 'g'};

                StringBuffer strBuff = new StringBuffer(length+2);

                for (int i=0; i<length; i++) {
                        int r = (int)(Math.random() * 4);
                        strBuff.append(bases[r]);
                }

                return strBuff.toString();

        }

	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		String pattern= "agttactttggaaaagggtgact";
		//String text = getRandomSequence(1000);
		//String text = "tagt";
		String text = "accacatcaaatgaaaatgggtccattgattgccccttcaattcactacttcagcctcactcgagaggcactattgttcaggcttaggaccgcgagacgatcgggtccagggggctgtggcacgcctctcgcaatgaacagaaacaacgtcaaatgtactatgaagttgctcggaagtataacgtgtaaaaatctgctgacccgccgggggaggaggatgggcataaggcaaccgctatcgcagcaccaaggcttttagatcggtgtaagcggaaaaagcaattcaaccatagacgaccagatacgattctgggcagagtgtaaactttcgctcctaaccaccggcgcgaccagaccggctcgtggcagagtcttgatacacaatcgagactactatacttgatttaatgggtatatgcctgtaggcttttttcagtttgtttcggagagtccctcggtgatacaaagatcctatcaatgcacaggcccattcgtccgtagcactagattctatacaaactgattaatctgtttgggaacctctacacctatgattacgtacctcgcggcatagagtacactagccggtcatttaatagtatcgcatattggacgagtaccggtacagattgatcgcgggctccatgtctggcgacgcttcaaaagccgcaacggacgaagtcttatcgtaacgtatataaatccataatctctagaaacttcgcgcctgaataaaattgggtatgcgatggggcgatttggcacacttccctggagttgctctaggcgcaccccgacagcggtcctccttgcagtgggccgcctaatcgtaatcttaaggaggagggttagctgtccctgtggccaatctttcactcgcctgcacaccggtagcattgatgagtcatggctatcaagacatacggccaggactcaaggctcaaagaagtgtggtgagcataaagatgtcctccaacagcccggtgagtatgctcgggta";

		RabinKarp patternSearcher = new RabinKarp(pattern);
		int offset = patternSearcher.search(text);

		//System.out.println("text: " + text);

		//System.out.println("pattern: " + pattern);
		System.out.println("offset: " + offset);

		long end = System.currentTimeMillis();

		System.out.println("Time required = " + (end-start));
	}
}

import java.util.*;
public class ORF {

	public static List<Integer> getORFLength(String sequence) {

		List<Integer> result = new ArrayList<Integer>();

		String start_symbol = "atg";

		String[] stop_symbols = {"taa", "tga", "tag"};
		for (int i=sequence.indexOf(start_symbol); i != -1; i=sequence.indexOf(start_symbol, i+1)) {

			//System.out.println("i = " + i);
			String sequence_remaining = sequence.substring(i);

			int j = -1;
			int min = 1000000;
			for(String stop_symbol: stop_symbols){
				if ((j=sequence_remaining.indexOf(stop_symbol, 3)) != -1) {
					//System.out.println("end symbol = " + stop_symbol + ", j = " + j);
					if (min > j-3) {
						min = j-3;
					}

				}
			}
			if (min < 1000000) {
				result.add(min);
			}
		}

		return result;
	}

	public static void main(String[] arg) {

		String virus_sequence = "atgagtcttctaaccgaggtcgaaacgtatgttctctctatcgttccgtcaggccccctcaaagccgagatagcacagagactagaagacgtttttgcagggaaaaacaccgatcttgaggctctcatggaatggctaaagacaagaccaatcctgtcacctctgactaaggggattttagggtttgtgttcacgctcaccgtgcccagtgagcgaggactgcagcgtagacgctttgtccagaatgccctcaatgggaatggtgacccgaataacatggacaaagcggtcaaactgtacaggaaacttaaaagagaaataacattccatggggccaaagaagtagcgctcagttactctgctggttcacttgccagttgcatgggcctcatatacaacagaatggggactgtcaccactgaggtggcctttggtctagtatgcgcaacctgtgaacagattgctgattcccagcatcgatctcacagacaaatggtgacaacaaccaatccactaatcaggcacgagaacagaatggtaatagccagcacaacagctaaagccatggaacaaatggctggatcgagcgaacaagcagcagaggctatggaggttgccagccaggcgagacaaatgatacaggcaatgagaacaattgggactcaccctagttccagcgctggtctaaaagatgatcttcttgaaaatttacaggcctatcagaaacggatgggagtgcaaatgcaacgattcaaatgatcctctcattgctgccgcaaacatcattgggattttgcacctgatattgtggattcttgatcgtctttttttcaaatgcatttaccgtcgctttaaatacggtctgcaaagaggaccttctacggaaggagtacctgagtccatgagggaagaatatcgacagaaacagcagagtgctgtggatgttgacgatggtcattttgtcaacatagtgctagagtaa";

		List<Integer> results = getORFLength(virus_sequence);

		System.out.println("ORF Lengths for the virus sequence: ");
		for (Integer result: results) {
			System.out.print(result + "\t");
		}

		System.out.println();


		String bacteria_sequence = "cggatggcatgacagtaagagaattatgcagtgctgccataaccatgagtgataacactgctgccaacttacttctgacaacgatcggaggaccgaaggagctaaccgcttttttgcacaacatgggggatcatgtaactcgccttgatcgttgggaaccggagctgaatgaagccataccaaacgacgagcgtgacaccacgatgcctgcagcaatggcaacaacgttgcgcaaactattaactggcgaactacttactctagcttcccggcaacaattaatagactggatggaggcggataaagttgcaggaccacttctgcgctcggcccttccggctggctggtttattgctgataaatctggagccggtgagcgtgggtctcgcggtatcattgcagcactggggccagatggtaagccctcccgtatcgtagttatctacacgacggggagtcaggcaactatggatgaacgaaatagacagatcgctgagataggtgcctcactgattaagcattggtaactgtcagaccaagtttactcatatatactttagattgatttaaaacttcatttttaatttaaaaggatctaggtgaagatcctttttgataatctcatgaccaaaatcccttaacgtgagttttcgttccactgagcgtcagaccccttatggcagagcagggaaaggaattgccgggctatgtgcaacgggaatttgaagaatttctccaatgcgggcggctggagcatggctttctacgggttcgctgcgagtcttgccacgccgagcacctggtcgctttcagctgtaagcgtcgcggtttctgcccgagctgtggggcgcggcggatggccgaaagtgccgccttgctggttgatgaagtactgcctgaacaacccatgcgtcagtgggtgttgagcttcccgtttcagctgcgtttcctgtttggggtcgtttgcgggaaggggcggaatcctacgctaaggctttggccagcgatattctccggtgagattgatgtgttcccaggggataggagaagggcactgttgcaaatagtcggtggtgataaacttatcatccccttttgctgatggagctgcacatgaacccattcaaaggccggcattttcagcgtgacatcattctgtgggccgtacgctggtactgcaaatacggcatcagttaccgtgagctgcaggagatgctggctgaacgcggagtgaatgtcgatcactccacgatttaccgctgggttcagcgttatgcgcctgaaatggaaaaacggctgcgctggtactggcgtaacccttccgatctttgcccgtggcacatggatgaaacctacgtgaaggtcaatggccgctgggcgtatctgtaccgggccgtcgacagccggggccgcactgtcgatttttatctctcctcccgtcgtaacagcaaagctgcataccggtttctgggtaaaatcctcaacaacgtgaagaagtgg";

		results = getORFLength(bacteria_sequence);

                System.out.println("ORF Lengths for the baceria sequence: ");
                for (Integer result: results) {
                        System.out.print(result + "\t");
                }
		System.out.println();

		String human_sequence = "gttcccgcagcaggaacatggtactctgctgcagtcttggaaaattagagttttgccgcaatctgaagcttgagcatacatagaaaaagcatgactgtgtgtgctccactagaatggcagtaatacaagagcaagtgtgatggttagttttatatgccaacttgacttggttatgaggtgcccgggtacttagtcaaacattattctgggtgtttggga";

               results = getORFLength(human_sequence);

                System.out.println("ORF Lengths for the human sequence: ");
                for (Integer result: results) {
                        System.out.print(result + "\t");
                }
                System.out.println();

                String random_sequence = getRandomSequence(1000);

               results = getORFLength(random_sequence);

                System.out.println("ORF Lengths for the random sequence: ");
                for (Integer result: results) {
                        System.out.print(result + "\t");
                }
                System.out.println();


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
	
}

//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Scanner;

public final class AverageTimes {

	public static void main(String[]args) {

		Scanner in = new Scanner(System.in);

		double sum = 0;

		while(in.hasNext()) {

			String temp = in.next();

			if (temp.equals("real")) {

				temp = in.next();

				temp = temp.substring(temp.indexOf("m") + 1, temp.indexOf("s"));

				sum += Double.parseDouble(temp);
			}
		}

		double average = sum / 3;

		System.out.printf("%.3f seconds\n", average);
 
	}
}
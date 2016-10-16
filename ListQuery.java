package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ListQuery {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer> list = readList(n, scanner);
		int q = scanner.nextInt();
		performQueries(scanner, q, list);
		System.out.println(pretty(list));
		scanner.close();
	}

	private static String pretty(List<Integer> list) {
		return list.toString().replace(", ", " ").replace("[", "").replace("]", "");
	}

	private static List<Integer> readList(int n, Scanner scanner) {
		
		List<Integer> list = new ArrayList<>();
		int count = 0;
		while (count < n) {
			list.add(scanner.nextInt());
			count++;
		}
		return list;
	}

	private static void performQueries(Scanner scanner, int q, List<Integer> list) {
		
		for (int i = 0; i < 2*q; i++) {
			if (! scanner.hasNextLine()) {
				System.out.println("has no next line!!!");
			} 
			else {
				String request = scanner.nextLine();
				if (request.equalsIgnoreCase("Insert")) {
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					list.add(x, y);
				}
				
				if (request.equalsIgnoreCase("Delete")) {
					int x = scanner.nextInt();
					list.remove(x);
				}
			}
		}
	}

}

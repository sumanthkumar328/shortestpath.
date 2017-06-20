
//This is a java program to find the shortest path between source vertex and destination vertex
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Short {
	static int row = 0;
	static int column = 0;
	static int current = 1000;
	static int total_cost = 0;
	static boolean finish = false;
	static int top = 1000;
	static int middle = 1000;
	static int bottom = 1000;
	static int temp_cost = 0;
	static int adjacency_matrix[][];
	static int number_of_rows;
	static int number_of_columns;
	static int source = 0, destination = 0;
	static boolean traversed = false;

	public static void main(String... arg) {

		Scanner scan = new Scanner(System.in);
		try {
			//method to get the input
			getInput(scan);

			//method to find the first element
			List<Integer> path = findFirstElement();

			//method to find the shortest path in the first column
			findShortestPath(path);

			//method to print the output  
			printoutput(scan, path);
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Invalid Matrix.");
			main();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printoutput(Scanner scan, List<Integer> path) {
		List<Integer> number = IntStream.rangeClosed(1, number_of_rows).boxed().collect(Collectors.toList());

		System.out.println("Path: " + path);
		compareArrays(number, path);

		System.out.println("total_cost:" + total_cost);
		// System.out.println("Path: "+path);

		scan.close();
	}


	private static void findShortestPath(List<Integer> path) {
		while (!finish) {

			if (column + 1 >= number_of_columns) {
				finish = true;
				break;
			}

			if (number_of_columns - 1 >= column + 1) {
				middle = adjacency_matrix[row][column + 1];
				// total_cost=total_cost+middle;
			} else {
				middle = 1000;
			}

			if (number_of_columns - 1 >= column + 1 && number_of_rows - 1 >= row + 1) {
				bottom = adjacency_matrix[row + 1][column + 1];
				// total_cost=total_cost+bottom;
			} else {
				bottom = 1000;
			}

			if (middle < bottom) {
				System.out.println("A is the smallest");
				current = middle;
				temp_cost = total_cost;
				total_cost = total_cost + middle;

				if (total_cost >= 50) {
					List<Integer> number = IntStream.rangeClosed(1, number_of_rows).boxed()
							.collect(Collectors.toList());
					// System.out.println("Path: "+path);
					// temp_path = new ArrayList<Integer>(101);
					// Collections.copy(temp_path,path);
					System.out.println("Path: " + path);
					compareArrays(number, path);
					System.out.println("total_cost:" + temp_cost);
					// System.out.println("Path: "+path);
					System.exit(0);
				}

				path.add(row + 1);
				column = column + 1;
				// row = row + 1;
			} else {
				System.out.println("B is the smallest");
				current = bottom;
				temp_cost = total_cost;
				total_cost = total_cost + bottom;

				if (total_cost >= 50) {
					List<Integer> number = IntStream.rangeClosed(1, number_of_rows).boxed()
							.collect(Collectors.toList());
					System.out.println("Path: " + path);
					compareArrays(number, path);
					System.out.println("total_cost:" + temp_cost);
					System.exit(0);
				}

				path.add(row + 2);
				column = column + 1;
				row = row + 1;
			}
		}
	}

	private static List<Integer> findFirstElement() {
		for (int i = 0; i < number_of_rows; i++) {
			int current1 = adjacency_matrix[i][0];
			if (current1 != 0 && (current != current1)) {
				if (current < current1) {

				} else {
					current = current1;
					row = i;
				}
			}
		}

		if (current > 50) {
			System.out.println("No");
			System.out.println("total_cost: 0");
			System.out.println("path: {}");
			System.exit(0);
		}

		total_cost = total_cost + current;
		if (number_of_columns == 1) {

			System.out.println("total_cost:" + total_cost);
			System.exit(0);
		}
		if (number_of_columns - 1 >= column + 1) {
			middle = adjacency_matrix[row][column + 1];
			// total_cost=total_cost+middle;
		}
		if (number_of_columns - 1 >= column + 1 && number_of_rows - 1 >= row + 1) {
			bottom = adjacency_matrix[row + 1][column + 1];
			// total_cost=total_cost+bottom;
		}
		List<Integer> path = new ArrayList<Integer>(100);
		path.add(row + 1);

		if (middle < bottom) {
			current = middle;
			column = column + 1;
			path.add(row + 1);

			temp_cost = total_cost;
			total_cost = total_cost + middle;
			if (total_cost >= 50) {
				List<Integer> number = IntStream.rangeClosed(1, number_of_rows).boxed().collect(Collectors.toList());
				// System.out.println("Path: "+path);
				// temp_path = new ArrayList<Integer>(101);
				// Collections.copy(temp_path,path);
				System.out.println("Path: " + path);
				compareArrays(number, path);

				System.out.println("total_cost:" + temp_cost);
				// System.out.println("Path: "+path);
				System.exit(0);
			}

		} else {
			current = bottom;
			column = column + 1;
			row = row + 1;
			path.add(row + 1);
			temp_cost = total_cost;
			total_cost = total_cost + bottom;
			if (total_cost >= 50) {
				List<Integer> number = IntStream.rangeClosed(1, number_of_rows).boxed().collect(Collectors.toList());
				System.out.println("Path: " + path);
				compareArrays(number, path);

				System.out.println("total_cost:" + temp_cost);
				// System.out.println("Path: "+path);
				System.exit(0);
			}
		}
		return path;
	}

	private static void getInput(Scanner scan) {
		System.out.println("Enter the number of rows");
		number_of_rows = scan.nextInt();

		System.out.println("Enter the number of columns");
		number_of_columns = scan.nextInt();

		adjacency_matrix = new int[number_of_rows][number_of_columns];

		System.out.println("Enter the Weighted Matrix for the graph");
		for (int i = 0; i < number_of_rows; i++) {
			for (int j = 0; j < number_of_columns; j++) {
				adjacency_matrix[i][j] = scan.nextInt();
			}
		}
	}

	public static void compareArrays(List<Integer> a, List<Integer> b) {
		// Check for sizes and nulls

		Set<Integer> hs = new HashSet<>();
		hs.addAll(b);
		b.clear();
		b.addAll(hs);

		if ((a.size() != b.size()) || (a == null && b != null) || (a != null && b == null)) {
			System.out.println("No");
			return;
		}

		// Sort and compare the two lists
		Collections.sort(a);
		Collections.sort(b);
		if (a.equals(b)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}

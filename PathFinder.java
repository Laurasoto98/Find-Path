package lab11;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PathFinder {

	static Node[][] nodes;
	static int startX;
	static int startY;
	static int goalX;
	static int goalY;
	static String result;

	public static void solveMaze(String inputFile, String outputFile) {
		try {
			readFile(inputFile);
			writeFile(outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void writeFile(String outfile) throws Exception {
		PrintWriter answ = new PrintWriter(new FileWriter(outfile));
		answ.println(nodes[0].length + " " + nodes.length);
		answ.println(result);
		answ.close();
	}

	public static void readFile(String infile) throws Exception {

		BufferedReader input = new BufferedReader(new FileReader(infile));

		String[] dimensions = input.readLine().split(" ");

		int height = Integer.parseInt(dimensions[0]);

		int width = Integer.parseInt(dimensions[1]);

		nodes = new Node[width][height];

		String current = input.readLine();

		int j = 0;

		while (current != null) {

			for (int i = 0; i < width; i++) {

				char value = current.charAt(i);

				if (value == 'S') {

					startX = i;
					startY = j;

				}

				if (value == 'G') {
					goalX = i;
					goalY = j;
				}

				nodes[i][j] = new Node(i, j, value);

			}

			j++;

			current = input.readLine();

		}

		input.close();

		backToReturn(nodes[startX][startY], nodes[goalX][goalY]);
		path(nodes[startX][startY], nodes[goalX][goalY]);
		printMaze(nodes);

	}

	public static Node[] next(Node current) {

		int i = current.row;
		int j = current.col;
		Node up = nodes[i][j - 1];
		Node down = nodes[i][j + 1];
		Node east = nodes[i - 1][j];
		Node west = nodes[i + 1][j];

		Node[] neighbors = { west, up, down, east };

		for (int l = 0; l < 4; l++) {
			if (neighbors[l].isWall() || neighbors[l].isVisited()) {
				neighbors[l] = null;
			}
		}

		return neighbors;

	}

	public static void backToReturn(Node start, Node goal) {

		Queue<Node> Q;
		Q = new LinkedList<Node>();
		start.setVisited();
		Q.add(start);
		while (!Q.isEmpty()) {
			Node current = Q.remove();
			if (current == goal) {
				return;
			}

			for (Node next : next(current)) {
				if (next != null) {
					next.setVisited();
					next.parent = current;
					Q.add(next);
				}

			}

		}

	}

	public static void path(Node start, Node goal) {

		while (goal.parent != start && goal.parent != null) {
			goal.parent.content = '.';
			goal = goal.parent;
		}
	}

	public static void printMaze(Node[][] nodes) {
		String output = "";
		System.out.println(nodes[0].length + " " + nodes.length);
		for (int i = 0; i < nodes[0].length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				output += nodes[j][i].content;
			}
			output += "\n";

		}
		System.out.print(output);
		result = output;
	}

	public static void main(String[] args) {

		solveMaze("mazes/tinyOpen.txt", "mazes/final1.txt");
		solveMaze("mazes/bigMaze.txt", "mazes/final2.txt");
		solveMaze("mazes/classic.txt", "mazes/final3.txt");
		solveMaze("mazes/demoMaze.txt", "mazes/final4.txt");
		solveMaze("mazes/mediumMaze.txt", "mazes/final5.txt");
		solveMaze("mazes/randomMaze.txt", "mazes/final6.txt");
		solveMaze("mazes/tinyMaze.txt", "mazes/final7.txt");
		solveMaze("mazes/turn.txt", "mazes/final8.txt");
		solveMaze("mazes/unsolvable.txt", "mazes/final9.txt");
		solveMaze("mazes/straight.txt", "mazes/final10.txt");

	}

}

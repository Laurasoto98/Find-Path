package lab11;

public class Node {
	
	boolean visited;
	int col;
	int row;
	Node parent;
	char content;
	
	
	public Node(int X, int Y, char content) {
		row= X;
		col= Y;
		this.content = content;
	}
	
	public void setVisited() {
		visited= true;
	}
	
	public boolean isWall() {
		if(content == 'X') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isVisited() {
		if(visited == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}

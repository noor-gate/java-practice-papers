package noughtsandcrosses;

import java.util.Iterator;

public class GameTree implements GameTreeInterface {
	
	private GameTreeNode root;      //reference to the root board of a game tree
	
	public GameTree(Board board){
		this.root = new GameTreeNode(board);
	}
	
	//post: Returns the board at the root of a game tree. 
	public Board getRootItem() {
		return root.getBoard();
	}
	
	//post: Returns the number of boards stored in a game tree.
	//	    It uses a recursive auxiliary method.
	public int size(){
		return sizeTree(root, 0)+1;
	}
	
	//YOU ARE ASKED TO IMPLEMENT THIS METHOD
	//post: Returns the number of boards stored in a game tree, excluded the root.
	private int sizeTree(GameTreeNode node, int size){
		if (node.getChildren().isEmpty()) {
			return 1;
		}
		for (GameTreeNode child : node.getChildren()) {
			size += 1 + sizeTree(child, size);
		}
		return size;
	}	
	
	//post: Expands the game tree fully by adding all possible boards in the game.
	//      It uses a recursive auxiliary method.
	public void expand(){
		expandTree(root);
	}
	
	//YOU ARE ASKED TO IMPLEMENT THIS METHOD
	//post: Expands the game tree from the given node by adding 
	//      all the possible moves that the computer and the user player
	//      can make, until the game is finished, from the given node onwards.
	private void expandTree(GameTreeNode node){
		Board board = node.getBoard().makeCopy();
	 	if (!board.isFinished()) {
	 		char mark = board.getTurn();

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board.getMark(i + j) == ' ') {
						board.setMark(i + j, mark);
						board.setLastMarkPosition(i + j);
						GameTreeNode newNode = new GameTreeNode(board);
						node.getChildren().add(node.getChildren().size() + 1, newNode);
						expandTree(newNode);
					}
				}
			}
		}
	}
	
	//pre:  The game tree is fully expanded.
	//post: Assigns a score to each board in the game tree. 
	//      It uses a recursive auxiliary method.
 	public void assignScores(){
 		char player = (root.getBoard()).getTurn();
 		assignScoresTree(root, player);
 	}


	//post: Assigns scores to each board in a game tree for the computer player. 
 	private void assignScoresTree(GameTreeNode node, char player){
 		
 		Board board = node.getBoard();
 			
 		if (board.isFinished()){
 			//base case of recursion
 			
 			//score 3 for a winning board for the given player, score 2 for a draw baord,
 			//score 1 for a losing board for the given player
 			char winner = board.getWinnerMark();
 			if (winner == Board.EMPTY) {
				//this is a draw!
				node.setScore(2);
			} else { node.setScore(winner == player ? 3 : 1);}
		}
		
		else {
		
		    //tries to assign the scores to all the children boards first, recursively
			int minScore = Integer.MAX_VALUE;
			int maxScore = Integer.MIN_VALUE;
		
			GenericList<GameTreeNode> children = node.getChildren();
			
			for (Iterator<GameTreeNode> it = children.iterator(); it.hasNext(); ){
				
				GameTreeNode child = it.next();
				assignScoresTree(child,player);
				
				//keeps track of the maximum and minimum scores of the children boards so far
				int childScore = child.getScore();
				if (childScore > maxScore) maxScore = childScore;
				if (childScore < minScore) minScore = childScore;
			}
		
			//Assigns score to the current board in the recursion according to the player's turn
			if (board.getTurn() == player) {
				// get the maximum score as the player wants to win
				node.setScore(maxScore);}
			else {
				//get the minimum score (as the player wants the opponent to lose;)
				node.setScore(minScore);
			}
		}
	}
	
	//pre:  Each board in the game tree has a score.
	//post: Computes an array of positions (1..9) optimal available moves. 
	//      These are the last mark positions in the children boards that have the highest score. 
	public int[] BestMoves(){
		
		int maxScore = Integer.MIN_VALUE;
		GenericList<GameTreeNode> highestScoreBoards = new GenericList<GameTreeNode>();
		GenericList<GameTreeNode> children = root.getChildren();
		
		for (Iterator<GameTreeNode> it = children.iterator(); it.hasNext();) {
			
			GameTreeNode nextBoard = it.next();
			int curScore = nextBoard.getScore();
			
			if (maxScore < curScore) {
				
				maxScore = curScore;
				highestScoreBoards.clear();
				highestScoreBoards.add(1,nextBoard);
				
			} else if (maxScore == curScore) {
				highestScoreBoards.add(1,nextBoard);
			}
		}

		int[] moves = new int[highestScoreBoards.size()];
		for (int i = 0; i < moves.length; i++) {
			Board board = (highestScoreBoards.get(i+1)).getBoard();
			moves[i] = board.getLastMarkPosition();
		}

		return moves;
	}
}

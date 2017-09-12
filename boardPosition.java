import java.util.ArrayList;
import java.util.List;

public class boardPosition {
	String position;
	int value;
	int number;
	static Mills mills = new Mills();
	int alpha;
	int beta;
	
	public boardPosition(String position){
		this.position = "";
		this.position += position;
	}
	
	public boardPosition(){
		value = 0;
		number = 0;
		this.alpha = Integer.MIN_VALUE;
		this.beta = Integer.MAX_VALUE;
	}
	
	public void setAlpha(int alpha){
		this.alpha = alpha;
	}
	
	public void setBeta(int beta){
		this.beta = beta;
	}
	
	public int getAlpha(){
		return this.alpha;
	}
	
	public int getBeta(){
		return this.beta;
	}
	
	public static String swap(String board){
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == 'W'){
				board = board.substring(0, i) + "B" + board.substring(i + 1);
			}
			else if(board.charAt(i) == 'B'){
				board = board.substring(0, i) + "W" + board.substring(i + 1);
			}
		}
		return board;
	}

}

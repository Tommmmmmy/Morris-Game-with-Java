import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {
	static int win = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        String opening = "xxxxxxxxxxxxxxxxxxxxxxx";
        for(int i = 0; i < 9; i++){
        	opening = minimaxOpening.opening(opening, 2, 'W').position;
        	opening = minimaxOpening.opening(opening, 2, 'B').position;
        }
        for(int i = 0; i < 10; i++){
        	play(opening, 'W');
        }
        System.out.println(win / 10);
	}
	
	public static void play(String board, char player){
		char winner = player;
		while(true){
			if(end(board, 'W')){
				win++;
				break;
			}
			else if(end(board, 'B')){
				break;
			}
			if(player == 'W'){
				minimaxGame.midEnd(board, 2, 'W');
				player = 'B';
			}
			else{
				minimaxGame.midEnd(board, 2, 'B');
				player = 'W';
			}
		}
	}
	
	public static boolean end(String board, char player){
		int opponent = 0;
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == (player == 'W' ? 'B' : 'W')) opponent++;
			if(opponent > 2) break;
		}
		if(opponent <= 2) return true;
		boolean move = false;
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == (player == 'W' ? 'B' : 'W')){
				boolean moveIndex = false;
				for(int neighbor : Mills.map.get(i)){
					if(board.charAt(neighbor) == 'x') moveIndex = true;
					if(moveIndex) break;
				}
				if(moveIndex) move = true;
				if(move) return false;
			}
		}
		return true;
	}
}

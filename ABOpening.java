import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ABOpening {
	
	static int[] count;
	public static boardPosition opening(String position, int depth, char player, int alpha, int beta){
		boardPosition res = new boardPosition();
//		res.setAlpha(alpha);
//		res.setBeta(beta);
		if(depth == 0){
			res.value = Estimator.staticEstimationOpening(position);
			res.number += 1;
			return res;
		}
		List<String> positionList = Generator.generateAdd(position, player);
		res.value = (player == 'W' ? Integer.MIN_VALUE : Integer.MAX_VALUE);
		for(String board : positionList){
			count[depth - 1]++;
			if(player == 'W'){
				boardPosition next = opening(board, depth - 1, 'B', alpha, beta);
//				System.out.println(depth + ": " + "'" + res.getAlpha() + "'" + " " + next.value + " " + "'" + res.getBeta() + "'");
				if(next.value > res.value){
					res.position = board;
					res.value = next.value;
				}
				res.number += next.number;
				if(res.value >= beta) return res;
				else{
					alpha = Math.max(res.value, alpha);
				} 
			}
			else{
				boardPosition next = opening(board, depth - 1, 'W', alpha, beta);
//				System.out.println(depth + ": " + "'" + res.getAlpha() + "'" + " " + next.value + " " + "'" + res.getBeta() + "'");
				if(next.value < res.value){
					res.position = board;
					res.value = next.value;
				}
				res.number += next.number;
				if(res.value <= alpha) return res;
				else{
					beta = Math.min(res.value, beta);
				} 
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String input = args[0];
        String output = args[1];
        int depth = Integer.valueOf(args[2]);
        count = new int[depth];
        BufferedReader fileIn = new BufferedReader(new FileReader(input));
        String opening = fileIn.readLine();
        System.out.println(opening);
        Generator.draw(opening);
        boardPosition bp = opening(opening, depth, 'W', Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(bp.position + " " + bp.number + " " + bp.value);
        Generator.draw(bp.position);
        BufferedWriter brw = new BufferedWriter(new FileWriter(output));
        brw.write(bp.position + "      " + bp.number + "       " + bp.value + "       ");
        brw.close();
        for(int i = 0; i < count.length; i++){
        	System.out.println(count[i]);
        }
	}

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniMaxGame {
	public static boardPosition midEnd(String position, int depth, char player){
		boardPosition res = new boardPosition();
		if(depth == 0){
			res.value = Estimator.staticEstimationMidEnd(position);
			res.number += 1;
			return res;
		}
		List<String> positionList = Generator.generateMidEnd(position, player);
		res.value = (player == 'W' ? Integer.MIN_VALUE : Integer.MAX_VALUE);
		for(String board : positionList){
			if(player == 'W'){
				boardPosition next = midEnd(board, depth - 1, 'B');
				if(next.value > res.value){
					res.value = next.value;
					res.position = board;
				}
				res.number += next.number;
			}
			else{
				boardPosition next = midEnd(board, depth - 1, 'W');
				if(next.value < res.value){
					res.value = next.value;
					res.position = board;
				}
				res.number += next.number;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String input = args[0];
        String output = args[1];
        int depth = Integer.valueOf(args[2]);
        BufferedReader fileIn = new BufferedReader(new FileReader(input));
        String opening = fileIn.readLine();
        System.out.println(opening);
        Generator.draw(opening);
        boardPosition bp = midEnd(opening, depth, 'W');
        System.out.println(bp.position + " " + bp.number + " " + bp.value);
        Generator.draw(bp.position);
        BufferedWriter brw = new BufferedWriter(new FileWriter(output));
        brw.write(bp.position + "      " + bp.number + "       " + bp.value + "       ");
        brw.close();
	}

}

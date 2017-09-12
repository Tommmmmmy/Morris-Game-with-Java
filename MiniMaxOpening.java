import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class minimaxOpening {
	public static boardPosition opening(String position, int depth, char player){
		boardPosition res = new boardPosition();
		if(depth == 0){
			res.value = Estimator.staticEstimationOpening(position);
			res.number += 1;
			return res;
		}
		List<String> positionList = Generator.generateAdd(position, player);
		res.value = (player == 'W' ? Integer.MIN_VALUE : Integer.MAX_VALUE);
		for(String board : positionList){
			if(player == 'W'){
				boardPosition next = opening(board, depth - 1, 'B');
				if(next.value > res.value){
					res.value = next.value;
					res.position = board;
				}
				res.number += next.number;
			}
			else{
				boardPosition next = opening(board, depth - 1, 'W');
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
        boardPosition bp = opening(opening, depth, 'W');
        System.out.println(bp.position + " " + bp.number + " " + bp.value);
        Generator.draw(bp.position);
        BufferedWriter brw = new BufferedWriter(new FileWriter(output));
        brw.write(bp.position + "      " + bp.number + "       " + bp.value + "       ");
        brw.close();
	}

}

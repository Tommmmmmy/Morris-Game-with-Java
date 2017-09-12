import java.util.ArrayList;
import java.util.List;

public class Generator {
	
	static Mills mills = new Mills();
	
	public static List<String> generateAdd(String board, char player){
		List<String> list = new ArrayList<>();
		Mills mills = new Mills();
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == 'x'){
				String b = board.substring(0, i) + player + board.substring(i + 1);
				if(mills.closeMill(b, i)){
//					generateRemove(b, list, player);
				}
				else{
					list.add(b);
				}
			}
		}
		return list;
	}
	
	public static List<String> generateMidEnd(String board, char player){
		int count = 0;
		List<Integer> colorIndex = new ArrayList<>();
		List<Integer> emptyIndex = new ArrayList<>();
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == player){
				count++;
				colorIndex.add(i);
			}
			else if(board.charAt(i) == 'x'){
				emptyIndex.add(i);
			}
		}
		if(count == 3) return generateHopping(board, colorIndex, emptyIndex, player);
		else return generateMove(board, colorIndex, player);
	}
	
	public static List<String> generateHopping(String board, List<Integer> colorIndex, List<Integer> emptyIndex, char player){
		List<String> res = new ArrayList<>();
		for(int idx1 : colorIndex){
			for(int idx2 : emptyIndex){
				String copy = board.substring(0, idx1) + "x" + board.substring(idx1 + 1);
				copy = copy.substring(0, idx2) + player + copy.substring(idx2 + 1);
				if(mills.closeMill(copy, idx2)){
					generateRemove(copy, res, player);
				}
				else{
					res.add(copy);
				}
			}
		}
		return res;
	}
	public static List<String> generateMove(String board, List<Integer> whiteIndex, char player){
		List<String> res = new ArrayList<>();
		for(int idx1 : whiteIndex){
			for(int idx2 : mills.map.get(idx1)){
				if(board.charAt(idx2) == 'x'){
					String copy = board.substring(0, idx1) + "x" + board.substring(idx1 + 1);
					copy = copy.substring(0, idx2) + player + copy.substring(idx2 + 1);
					if(mills.closeMill(copy, idx2)){
						generateRemove(copy, res, player);
					}
					else{
						res.add(copy);
					}
				}
			}
		}
		ABGame.sum += res.size();
		return res;
	}
	
	public static void generateRemove(String board, List<String> list, char player){
		Mills mills = new Mills();
		boolean flag = false;
		for(int i = 0; i < board.length(); i++){
			if(player == 'W'){
				if(board.charAt(i) == 'B'){
					if(!mills.closeMill(board, i)){
						String b = board.substring(0, i) + "x" + board.substring(i + 1);
						list.add(b);
						flag = true;
					}
				}
			}
			else if(player == 'B'){
				if(board.charAt(i) == 'W'){
					if(!mills.closeMill(board, i)){
						String b = board.substring(0, i) + "x" + board.substring(i + 1);
						list.add(b);
						flag = true;
					}
				}
			}
		}
		if(flag == false)list.add(board);
	}
	
	public static String swap(String board){
		String res  = board;
		for(int i = 0; i < board.length(); i++){
			if(board.charAt(i) == 'W'){
				res = res.substring(0, i) + 'B' + res.substring(i + 1);
			}
			else if(board.charAt(i) == 'B'){
				res = res.substring(0, i) + 'W' + res.substring(i + 1);
			}
		}
		return res;
	}  
	
	public static void draw(String input){
        System.out.println(input.charAt(20) + "----------" + input.charAt(21) + "----------" + input.charAt(22));
        System.out.println("   " + input.charAt(17) + "-------" + input.charAt(18) + "-------" + input.charAt(19) + "   ");
        System.out.println("      " + input.charAt(14) + "----" + input.charAt(15) + "----" + input.charAt(16) + "      ");
        System.out.println(input.charAt(8) + "--" + input.charAt(9) + "--" + input.charAt(10) + "         " + input.charAt(11) + "--" + input.charAt(12) + "--" + input.charAt(13));
        System.out.println("      " + input.charAt(6) + "---------" + input.charAt(7) + "     ");
        System.out.println("   " + input.charAt(3) + "-------" + input.charAt(4) + "-------" + input.charAt(5) + "   ");
        System.out.println(input.charAt(0) + "----------" + input.charAt(1) + "----------" + input.charAt(2));
    }
}

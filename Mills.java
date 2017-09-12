import java.util.*;

public class Mills {
	
	public static List<List<Integer>> mills;
	public static Map<Integer, int[]> map = new HashMap<>();
	
	public Mills(){
		mills = new ArrayList<>();
		mills.add(Arrays.asList(0, 1, 2));
		mills.add(Arrays.asList(0, 8, 20));
		mills.add(Arrays.asList(2, 13, 22));
		mills.add(Arrays.asList(20, 21, 22));
		mills.add(Arrays.asList(3, 4, 5));
		mills.add(Arrays.asList(3, 9, 17));
		mills.add(Arrays.asList(5, 12, 19));
		mills.add(Arrays.asList(17, 18, 19));
		mills.add(Arrays.asList(6, 10, 14));
		mills.add(Arrays.asList(14, 15, 16));
		mills.add(Arrays.asList(7, 11, 16));
		mills.add(Arrays.asList(0, 3, 6));
		mills.add(Arrays.asList(2, 5, 7));
		mills.add(Arrays.asList(8, 9, 10));
		mills.add(Arrays.asList(11, 12, 13));
		mills.add(Arrays.asList(14, 17, 20));
		mills.add(Arrays.asList(15, 18, 21));
		mills.add(Arrays.asList(16, 19, 22));
		map.put(0, new int[]{3, 8, 1});
		map.put(1, new int[]{0, 2, 4});
		map.put(2, new int[]{1, 5, 13});
		map.put(3, new int[]{0, 6, 4, 9});
		map.put(4, new int[]{3, 1, 5});
		map.put(5, new int[]{4, 2, 7, 12});
		map.put(6, new int[]{3, 7, 10});
		map.put(7, new int[]{5, 6, 11});
		map.put(8, new int[]{0, 9, 20});
		map.put(9, new int[]{3, 8, 10, 17});
		map.put(10, new int[]{6, 9, 14});
		map.put(11, new int[]{7, 12, 16});
		map.put(12, new int[]{5, 11, 13, 19});
		map.put(13, new int[]{2, 12, 22});
		map.put(14, new int[]{10, 15, 17});
		map.put(15, new int[]{14, 16, 18});
		map.put(16, new int[]{11, 15, 19});
		map.put(17, new int[]{9, 14, 18, 20});
		map.put(18, new int[]{15, 17, 19, 21});
		map.put(19, new int[]{12, 16, 18, 22});
		map.put(20, new int[]{8, 17, 21});
		map.put(21, new int[]{18, 20, 22});
		map.put(22, new int[]{13, 19, 21});
	}
	
	public boolean closeMill(String board, int index){
		char color = board.charAt(index);
		for(List<Integer> list : mills){
			if(list.contains(index)){
				boolean same = true;
				for(int idx : list){
					if(board.charAt(idx) != color){
						same = false;
						break;
					}
				}
				if(same == true){
					return true;
				}
			}
		}
		return false;
	}
	
	public static int nearMills(String str, char color){
		int nearMillCount = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == 'x'){
				boolean count = false;
				for(List<Integer> list : Mills.mills){
					if(list.contains(i)){
						boolean bool = true;
						for(int index : list){
							if(i != index){
								if(str.charAt(index) == color){
									bool = true;
								}
								else{
									bool = false;
									break;
								}
							}
						}
						if(!bool) continue;
						else{
							count = true;
							break;
						}
					}
				}
				if(count) nearMillCount++;
			}
		}
		return nearMillCount;
	}
}

import java.util.List;

public class Estimator {
	
	public static int staticEstimationOpening(String str){
		int white = 0;
		int black = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == 'W'){
				white++;
			}
			else if(str.charAt(i) == 'B'){
				black++;
			}
		}
		return white - black;
	}
	
	public static int staticEstimationMidEnd(String str){
		int white = 0;
		int black = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == 'W'){
				white++;
			}
			else if(str.charAt(i) == 'B'){
				black++;
			}
		}
		List<String> positions = Generator.generateMidEnd(str, 'B');
		if(black <= 2) return 10000;
		else if(white <= 2) return -10000;
		else if(positions.size() == 0) return 10000;
		else return (1000 * (white - black) - positions.size());
	}
	
	public static int staticEstimationOpeningImproved(String str){
		int white = 0;
		int black = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == 'W'){
				white++;
			}
			else if(str.charAt(i) == 'B'){
				black++;
			}
		}
		int nearMillCount = Mills.nearMills(str, 'W') - Mills.nearMills(str, 'B');
		
		return white - black + nearMillCount;
	}
	
	public static int staticEstimationMidEndImproved(String str){
		int white = 0;
		int black = 0;
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == 'W'){
				white++;
			}
			else if(str.charAt(i) == 'B'){
				black++;
			}
		}
		List<String> positions = Generator.generateMidEnd(str, 'B');
		if(black <= 2) return 10000;
		else if(white <= 2) return -10000;
		else if(positions.size() == 0) return 10000;
		else{
			int nearMillCount = Mills.nearMills(str, 'W') - Mills.nearMills(str, 'B');
			return (1000 * (white - black + nearMillCount) - positions.size() );
		}
	}
}

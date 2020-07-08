package blackbox.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TriangleProblem {
	String determine(int a,int b,int c) {
		checkEdgeLength(a, b, c);
		if(checkRelation(a, b, c)) {
			String s = checkEquals(a, b, c);
			return s;
		}
		return "非三角形";
	}

	//检查每条边的长是否在0-100之间
	void checkEdgeLength(int... length) {
		IntStream.of(length).boxed().forEach(l -> {
			if(0 >=l || l > 100) {
				throw new IllegalArgumentException("bad length" + l);
			}
		});
	}
	
	//检查是否构成三角形
	boolean checkRelation(int a, int b, int c) {
		if(a + b <= c || a + c <= b || b + c <= a) {
			System.out.println("不构成三角形");
			return false;
		}
		return true;
	}
	
	//判断三角形的类型
	String checkEquals(int a, int b, int c) {
		Map<Integer, String> map = new HashMap<Integer,String>() {{
			put(0,"一般三角形");
			put(1,"等腰三角形");
			put(3,"等边三角形");
		}};
		//如果number值为0,1,3时分别对应一般三角形，等腰三角形和等边三角形
		int number = checkEquals(a, b) + checkEquals(b, c) + checkEquals(a, c);
		System.out.println(number);
		return map.get(number);
	}
	
	int checkEquals(int a, int b) {
		return a == b ? 1 : 0; 
	}
}

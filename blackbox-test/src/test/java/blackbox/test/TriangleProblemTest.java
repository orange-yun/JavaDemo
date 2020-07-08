package blackbox.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackbox.test.TriangleProblem;

@DisplayName("测试△问题")

class TriangleProblemTest {
	TriangleProblem t = new TriangleProblem();
	// 前三个条件：1≤a≤100，1≤b≤100，1≤c≤100
	// 边界值分析
	// 如果满足前三个条件，但不满足后三个输入条件任何一个，预期结果为非三角形
	@ParameterizedTest
	@MethodSource("checkRelation")
	@DisplayName("测试非三角形")
	void test_checkRelation(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> checkRelation(){
		return Stream.of(
				Arguments.of(100,50,50, "非三角形"),
				Arguments.of(50,100,50, "非三角形"),
				Arguments.of(50,50,100, "非三角形"));
	}
	
	// 如果满足前三个条件，且三条边长相等，预期结果为等边三角形;恰好有两条边相等，预期结果为等腰三角形;三条边都不相等，预期结果为一般三角形
	@ParameterizedTest
	@MethodSource("checkEquals")
	@DisplayName("合法三角形测试")
	void test_checkEquals(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> checkEquals(){
		return Stream.of(
				Arguments.of(2,2,2, "等边三角形"),
				Arguments.of(1,50,50, "等腰三角形"),
				Arguments.of(50,1,50, "等腰三角形"),
				Arguments.of(50,50,1, "等腰三角形"),
				Arguments.of(99,50,60, "一般三角形"),
				Arguments.of(50,99,60, "一般三角形"),
				Arguments.of(50,60,99, "一般三角形"));
	}
	
	// 如果不满足前三个输入条件，预期结果为抛出异常
	@ParameterizedTest
	@MethodSource("checkEdgeLength")
	@DisplayName("异常测试")
	void test_checkEdgeLength(int a, int b, int c) {
		Executable executable = () -> t.determine(a, b, c);
		assertThrows(IllegalArgumentException.class,executable);
	}
	static Stream<Arguments> checkEdgeLength(){
		return Stream.of(
				Arguments.of(0,50,50),
				Arguments.of(50,0,50),
				Arguments.of(50,50,0),
				Arguments.of(101,50,50),
				Arguments.of(50,101,50),
				Arguments.of(50,50,101));
	}	
	
	//等价类划分
	@ParameterizedTest
	@MethodSource("checkEquals_1")
	@DisplayName("合法三角形测试")
	void test_checkEquals_1(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> checkEquals_1(){
		return Stream.of(
				Arguments.of(3,4,5, "一般三角形"),
				Arguments.of(4,4,5, "等腰三角形"),
				Arguments.of(3,3,3, "等边三角形"));
	}
	
	@ParameterizedTest
	@MethodSource("checkRelation_1")
	@DisplayName("测试非三角形")
	void test_checkRelation_1(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> checkRelation_1(){
		return Stream.of(
				Arguments.of(50,10,5, "非三角形"),
				Arguments.of(10,50,5, "非三角形"),
				Arguments.of(10,5,50, "非三角形"));
	}
	
	@ParameterizedTest
	@MethodSource("checkEdgeLength_1")
	@DisplayName("异常测试")
	void test_checkEdgeLength_1(int a, int b, int c) {
		Executable executable = () -> t.determine(a, b, c);
		assertThrows(IllegalArgumentException.class,executable);
	}
	static Stream<Arguments> checkEdgeLength_1(){
		return Stream.of(
				Arguments.of(110,60,80),
				Arguments.of(60,110,80),
				Arguments.of(60,80,110));
	}
	
}

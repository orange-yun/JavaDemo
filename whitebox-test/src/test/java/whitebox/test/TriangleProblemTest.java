package whitebox.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("测试△问题")
class TriangleProblemTest {
	TriangleProblem t = new TriangleProblem();
	@Test
	@DisplayName("路径覆盖测试-抛出异常")
	void path_test_exception() {
		Executable executable = () -> t.determine(0, 50, 50);
		assertThrows(IllegalArgumentException.class, executable);
	}
	
	@ParameterizedTest
	@MethodSource("Normal")
	@DisplayName("路径覆盖测试-正常返回")
	void path_test_Normal(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> Normal(){
		return Stream.of(
				Arguments.of(50,10,5, "非三角形"),
				Arguments.of(3,4,5, "一般三角形"),
				Arguments.of(4,4,5, "等腰三角形"),
				Arguments.of(3,3,3, "等边三角形"));
	}

	@Test
	@DisplayName("p-use覆盖测试-抛出异常")
	void p_use_test_exception() {
		Executable executable = () -> t.determine(0, 50, 50);
		assertThrows(IllegalArgumentException.class, executable);
	}
	
	@ParameterizedTest
	@MethodSource("puse_Normal")
	@DisplayName("p-use覆盖测试-正常返回")
	void p_use_test_Normal(int a, int b, int c, String s) {
		assertEquals(s, t.determine(a, b, c));
	}
	static Stream<Arguments> puse_Normal(){
		return Stream.of(
				Arguments.of(50,10,5, "非三角形"),
				Arguments.of(3,4,5, "一般三角形"),
				Arguments.of(4,4,5, "等腰三角形"),
				Arguments.of(3,3,3, "等边三角形"));
	}
	
}

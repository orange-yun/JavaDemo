package iteach.eaap.musics.management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@DisplayName("音乐管理系统的系统测试")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MusicApplicationSystemTest {
	static RestTemplate rest;
	static String id;
//	static String Id;
	
	@BeforeAll
	static void initAll() {
		rest = new RestTemplate();
	}

	@Test
	@DisplayName("添加合法的音乐，对应测试用例 a1")
	@Order(1)
	void test_add_music() {
		String json = "{\"title\": \"t\", \"description\": \"Trouble is a friend \", \"releasedate\": \"2020-06-20 10:31:00\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(json,headers);
		id = rest.postForObject("http://localhost:3307/musics", request, String.class);
		
	}
	
	@ParameterizedTest
	@MethodSource("add_invalid_music_title") 
	@DisplayName("添加非法的音乐，对应测试用例 a2-a6")
	void test_add_invalid_music(String title, String description, String releasedate) {
		String json = "{\"title\": title,\"description\": description, \"releasedate\": releasedate}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(json,headers);
		Assertions.assertThrows(Exception.class, () -> rest.postForObject(
				"http://localhost:3307/musics", request, String.class));
		
	}
	
	static Stream<Arguments> add_invalid_music_title() {
		 return Stream.of(
				    Arguments.of("","Trouble is a friend ","2020-06-14 10:31:00"),
				    Arguments.of("   ","Trouble is a friend ","2020-06-14 10:31:00"),
				    Arguments.of("t","Trouble is a friend","2020-06-14 10:31:00"),
				    Arguments.of("t","                     ","2020-06-14 10:31:00"),
				    Arguments.of("name","descriptiontest","2020-06-04 10:31:00"));
	}
	
	@Test
	@DisplayName("正常发布音乐，对应测试用例 b1")
	@Order(2)
	void test_publish_music() {
		ResponseEntity<Object> entity = rest.exchange(
				"http://localhost:3307/musics/publish/" + id, HttpMethod.PUT, null, Object.class);
		Assertions.assertEquals(200, entity.getStatusCode().value());
	}
	
	@Test
	@DisplayName("正常关闭音乐，对应测试用例 c1")
	@Order(3)
	void test_close_music() {
		ResponseEntity<Object> entity = rest.exchange(
				"http://localhost:3307/musics/close/"+ id , HttpMethod.PUT, null, Object.class);
		assertEquals(200, entity.getStatusCode().value());
	}
	
	@Test
	@DisplayName("正常删除音乐，对应测试用例 d1")
	@Order(4)
	void test_remove_music() {
		ResponseEntity<Object> entity = rest.exchange(
				"http://localhost:3307/musics/remove/"+ id , HttpMethod.PUT, null, Object.class);
		assertEquals(200, entity.getStatusCode().value());
	}
	
	@Test
	@DisplayName("正常获取所有音乐，对应测试用例 e1")
	void test_get_music() {
		ResponseEntity<Object> entity = rest.exchange(
				"http://localhost:3307/musics", HttpMethod.GET, null, Object.class);
		assertEquals(200, entity.getStatusCode().value());
	}
	
	@Test
	@DisplayName("正常获取指定音乐，对应测试用例 f1")
	void test_getid_music() {
		ResponseEntity<Object> entity = rest.exchange(
				"http://localhost:3307/musics/" + id, HttpMethod.GET, null, Object.class);
		Assertions.assertEquals(200, entity.getStatusCode().value());
	}
	
	@Test
	@DisplayName("正常获取音乐状态，对应测试用例 g1")
	@Order(5)
	void test_get_music_status() {
		ResponseEntity<String> entity = rest.exchange(
				"http://localhost:3307/musics/" + id + "/status/", HttpMethod.GET, null, String.class);
		Assertions.assertEquals(200, entity.getStatusCode().value());
	}
	
	//其余类似，略
	
//	@Test
//	@DisplayName("正常发布音乐，对应测试用例 a1")
//	void test_published_music() {
//		String json = "{\"introduce\": \"a\", \"music\": + id }";
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> request = new HttpEntity<>(json,headers);
//		Id = rest.postForObject("http://localhost:3300/publishings", request, String.class);
//		
//	}
//	
//	@Test
//	@DisplayName("正常获取发布音乐，对应测试用例 b1")
//	void test_getpublish_music() {
//		ResponseEntity<Object> entity = rest.exchange(
//				"http://localhost:3300/publishings", HttpMethod.GET, null, Object.class);
//		assertEquals(200, entity.getStatusCode().value());
//	}

}

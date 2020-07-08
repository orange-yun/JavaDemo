package iteach.eaap.musics.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@DisplayName("音乐管理服务集成测试")
class MusicControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String id = "";
    
    @Test
    @Order(1)
    @DisplayName("获取所有音乐")
    void test_getall_musics() throws Exception {
        mockMvc.perform(get("/musics")).andExpect(handler().methodName("getAllMusics"))
        		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    @Order(2)
    @DisplayName("添加新音乐")
    void test_add_music() throws Exception {
        String json = "{\"title\": \"t\", \"description\": \"Trouble is a friend \", \"releasedate\": \"2020-06-20 14:00:00\"}";
        MvcResult result = mockMvc.perform(post("/musics")
        		.content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        id = result.getResponse().getContentAsString();
        System.out.println("added id = " + id);
        assertNotNull(id);
    }

    @Test
    @Order(3)
    @DisplayName("获取音乐状态")
    void test_get_music_status() throws Exception {
        String response = mockMvc.perform(get("/musics/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("ADDED", response);
    }

    @Test
    @Order(4)
    @DisplayName("发布音乐")
    void test_publish_music() throws Exception {
        mockMvc.perform(put("/musics/publish/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/musics/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("PUBLISHED", response);
    }

    @Test
    @Order(5)
    @DisplayName("获取指定音乐")
    void test_get_music_details() throws Exception {
        mockMvc.perform(get("/musics/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("t"))
                .andExpect(jsonPath("$.status").value("PUBLISHED"));
    }

    @Test
    @Order(6)
    @DisplayName("关闭音乐")
    void test_close_music() throws Exception {
        mockMvc.perform(put("/musics/close/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/musics/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("CLOSED", response);
    }

    @Test
    @Order(7)
    @DisplayName("删除音乐")
    void test_remove_music() throws Exception {
        mockMvc.perform(put("/musics/remove/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        String response = mockMvc.perform(get("/musics/" + id + "/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("REMOVED", response);
    }

    @Test
    @DisplayName("测试非法的添加音乐请求")
    void test_add_illegal_music() throws Exception {
        String json = "{\"title\": \"t\", \"description\": \"Trouble is a friend \", \"releasedate\": \"2020-05-30 00:00:00\"}";
        mockMvc.perform(post("/musics").content(json).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("获取一个不存在的音乐")
    void test_get_not_exist_music_details() throws Exception {
        mockMvc.perform(get("/musics/2f5e530d-bff9-425c-8b7e-49cccb0c6eff"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }
    
}

package iteach.eaap.musics.management.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("实体 Music 单元测试")
public class MusicTest {
    @Test
    @DisplayName("测试获取状态")
    void test_get_status() {
    	Releasedate releasedate = Mockito.mock(Releasedate.class);
    	Music music = new Music("title", "description", releasedate);
        Assertions.assertEquals(Status.ADDED, music.status());

        Mockito.when(releasedate.isExpired()).thenReturn(true);
        Assertions.assertEquals(Status.EXPIRED, music.status());
    }

    @Test
    @DisplayName("测试关闭音乐")
    void test_close_music() {
    	Releasedate releasedate = Mockito.mock(Releasedate.class);
    	Music music = new Music("title", "description", releasedate);
        music.close();
        Assertions.assertEquals(Status.CLOSED, music.status());
    }
    
    @Test
    @DisplayName("测试发布音乐")
    void test_publish_music() {
    	Releasedate releasedate = Mockito.mock(Releasedate.class);
    	Music music = new Music("title", "description", releasedate);
        music.publish();
        Assertions.assertEquals(Status.PUBLISHED, music.status());
    }
    
    @Test
    @DisplayName("测试删除音乐")
    void test_remove_music() {
    	Releasedate releasedate = Mockito.mock(Releasedate.class);
        Music music = new Music("title", "description", releasedate);
        music.remove();
        Assertions.assertEquals(Status.REMOVED, music.status());
    }
    
}

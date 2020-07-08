package iteach.eaap.musics.management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import iteach.eaap.musics.management.application.port.outbound.MusicRepository;
import iteach.eaap.musics.management.domain.Music;
import iteach.eaap.musics.management.domain.Releasedate;
import iteach.eaap.musics.management.domain.Status;

@DataJpaTest
@DisplayName("音乐管理服务数据库集成测试")
public class MusicRepositoryIntegrationTest {
    @Autowired
    MusicRepository repository;
    
    @Test
    @DisplayName("测试queryAll方法-查询所有音乐")
    void test_queryall() {
        List<Music> list = repository.queryAll();
        Assertions.assertTrue(list.isEmpty());
    }
    
    @Test
    @DisplayName("测试-添加新音乐")
    void test_add_music() {
    	Music music = new Music("title", "description", new Releasedate(LocalDateTime.now().plusDays(1)));
        repository.add(music);

        List<Music> list = repository.queryAll();
        assertEquals(1, list.size());
        assertEquals(music.makeMusicDTO(), list.get(0).makeMusicDTO());
    }
    
    @Test
    @DisplayName("测试-更新音乐")
    void test_update_music() {
    	Music music = new Music("title", "description", new Releasedate(LocalDateTime.now().plusDays(1)));
    	music.ChangeStatus(Status.ADDED);
        repository.update(music);
        assertEquals("ADDED", music.makeMusicDTO().getStatus());
    }
    
    @Test
    @DisplayName("测试-按id查询音乐")
    void test_queryById_music() {
    	
    }
    
}

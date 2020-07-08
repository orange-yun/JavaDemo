package iteach.eaap.musics.management.domain;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("值对象 Releasedate 单元测试")
class ReleasedateTest {
    @Test
    @DisplayName("datetime 必须晚于当前时间，否则报错")
    void test_early_deadline() {
        Assertions.assertThrows(MusicReleasedateException.class,
                () ->new Releasedate(LocalDateTime.now().minusDays(1)));
    }
    
    @Test
    @DisplayName("测试音乐是否到期")
    void test_is_expired() {
    	Releasedate releasedate = new Releasedate(LocalDateTime.now().plusDays(10));
        Assertions.assertFalse(releasedate.isExpired());
    }

}

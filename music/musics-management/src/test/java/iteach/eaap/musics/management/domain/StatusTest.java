package iteach.eaap.musics.management.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("值对象 Status 单元测试")
public class StatusTest {
    @Test
    @DisplayName("只有已添加状态可以转换成已发布状态")
    void test_changeto_published() {
    	// 如果当前状态是 ADDED，那么可以正常转换为已发布状态
        Assertions.assertEquals(Status.PUBLISHED,Status.ADDED.changeTo(Status.PUBLISHED));

        // 如果当前状态不是 ADDED，那么不可以转换为已发布状态
        Stream.of(Status.CLOSED, Status.EXPIRED, Status.REMOVED).forEach(
        		status -> assertThrows(MusicStatusException.class,
        				() -> status.changeTo(Status.PUBLISHED))
        );
        
//        // 如果当前状态是 REMOVED，那么不可以转换为已关闭 状态
//        Stream.of(Status.REMOVED).forEach(
//        		status -> assertThrows(MusicStatusException.class,
//        				() -> status.changeTo(Status.CLOSED))
//        );
//        
//        // 如果当前状态是REMOVED，那么可以正常转换为已关闭状态
//        assertDoesNotThrow(() -> Status.EXPIRED.changeTo(Status.REMOVED));
        
        // 如果当前状态是 REMOVED，那么转换为 REMOVED 不会报错
        assertDoesNotThrow(() -> Status.REMOVED.changeTo(Status.REMOVED));

    }
}

package iteach.eaap.musics.publishing.adapter.outbound;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iteach.eaap.musics.publishing.application.port.outbound.QueryUserPublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;

public interface JpaQueryUserPublishingRepository extends QueryUserPublishingRepository,JpaRepository<Publishing, String> {
	@Override
    default List<Publishing> queryAllByUserId(String userId) {
        return findAllByUser(userId);
    }

    List<Publishing> findAllByUser(String musicId);
}

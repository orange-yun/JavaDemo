package iteach.eaap.musics.publishing.adapter.outbound;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iteach.eaap.musics.publishing.application.port.outbound.QueryMusicPublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;

public interface JpaQueryMusicPublishingRepository extends QueryMusicPublishingRepository,JpaRepository<Publishing, String> {
	@Override
	default List<Publishing> queryAllByMusicId(String musicId) {
        return findAllByMusic(musicId);
    }
	
	List<Publishing> findAllByMusic(String music);
}

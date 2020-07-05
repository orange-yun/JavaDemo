package iteach.eaap.musics.publishing.application.port.outbound;

import java.util.List;

import iteach.eaap.musics.publishing.domain.Publishing;

public interface QueryUserPublishingRepository {
	List<Publishing> queryAllByUserId(String userId);
}

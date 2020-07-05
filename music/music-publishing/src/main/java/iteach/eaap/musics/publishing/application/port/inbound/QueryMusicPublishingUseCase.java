package iteach.eaap.musics.publishing.application.port.inbound;

import java.util.List;

import iteach.eaap.musics.publishing.domain.Publishing;

public interface QueryMusicPublishingUseCase {
	List<Publishing> queryPublishings(String musicId);
}

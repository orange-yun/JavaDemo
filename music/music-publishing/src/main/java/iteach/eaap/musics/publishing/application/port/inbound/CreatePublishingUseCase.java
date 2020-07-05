package iteach.eaap.musics.publishing.application.port.inbound;

import iteach.eaap.musics.publishing.domain.Publishing;

public interface CreatePublishingUseCase {
	void createPublishing(Publishing publishing);
}

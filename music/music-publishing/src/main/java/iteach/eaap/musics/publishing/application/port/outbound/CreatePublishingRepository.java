package iteach.eaap.musics.publishing.application.port.outbound;

import iteach.eaap.musics.publishing.domain.Publishing;

public interface  CreatePublishingRepository {
	void addPublishing(Publishing publishing);
}

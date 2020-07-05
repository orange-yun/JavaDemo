package iteach.eaap.musics.publishing.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

import iteach.eaap.musics.publishing.application.port.outbound.CreatePublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;

public interface JpaCreatePublishingRepository extends CreatePublishingRepository,JpaRepository<Publishing, String>{
	@Override
    default void addPublishing(Publishing publishing) {
        this.save(publishing);
    }
}

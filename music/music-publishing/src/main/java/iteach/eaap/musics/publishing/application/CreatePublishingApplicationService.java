package iteach.eaap.musics.publishing.application;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import iteach.eaap.musics.publishing.application.port.inbound.CreatePublishingUseCase;
import iteach.eaap.musics.publishing.application.port.outbound.CreatePublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;
import iteach.eaap.musics.publishing.domain.PublishingException;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
class CreatePublishingApplicationService implements CreatePublishingUseCase {
	private final RestTemplate restTemplate;
	private final CreatePublishingRepository repository;

	@Override
	public void createPublishing(Publishing publishing) {
		String url = "http://management-service:3307/musics/" + publishing.getMusic() + "/status";
		String status = restTemplate.getForObject(url, String.class);

		assert status != null;
		if(!status.equalsIgnoreCase("published")) {
			throw new PublishingException("发布失败");
		}

		String userId = "1";
		publishing.setId(UUID.randomUUID().toString());
		publishing.setUser(userId);
		publishing.setDatetime(LocalDateTime.now());
		repository.addPublishing(publishing);
	}
}

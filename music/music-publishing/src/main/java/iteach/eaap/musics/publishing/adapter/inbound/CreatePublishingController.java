package iteach.eaap.musics.publishing.adapter.inbound;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.musics.publishing.application.port.inbound.CreatePublishingUseCase;
import iteach.eaap.musics.publishing.domain.Publishing;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class CreatePublishingController {
	private CreatePublishingUseCase usecase;
	
	@PostMapping("/publishings")
	void createPublishing(@RequestBody Publishing publishing) {
		usecase.createPublishing(publishing);
	}
}

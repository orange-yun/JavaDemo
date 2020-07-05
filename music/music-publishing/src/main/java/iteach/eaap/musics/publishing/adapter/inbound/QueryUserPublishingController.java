package iteach.eaap.musics.publishing.adapter.inbound;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.musics.publishing.application.port.inbound.QueryUserPublishingUseCase;
import iteach.eaap.musics.publishing.domain.Publishing;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class QueryUserPublishingController {
	QueryUserPublishingUseCase usecase;
	
	@GetMapping("/publishings")
	List<Publishing> publishings() {
		String userId = "1"; // TODO hard code
		return usecase.queryAll(userId);
	}
}

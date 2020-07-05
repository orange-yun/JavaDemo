package iteach.eaap.musics.publishing.adapter.inbound;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import iteach.eaap.musics.publishing.application.port.inbound.QueryMusicPublishingUseCase;
import iteach.eaap.musics.publishing.domain.Publishing;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class QueryMusicPublishingController {
	QueryMusicPublishingUseCase usecase;
	
	@GetMapping("/publishings/{musicId}")
	List<Publishing> publishingsOf(String musicId) {
		return usecase.queryPublishings(musicId);
	}
}

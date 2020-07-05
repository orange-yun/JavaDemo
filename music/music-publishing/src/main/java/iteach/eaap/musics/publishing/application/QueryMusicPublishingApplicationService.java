package iteach.eaap.musics.publishing.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.musics.publishing.application.port.inbound.QueryMusicPublishingUseCase;
import iteach.eaap.musics.publishing.application.port.outbound.QueryMusicPublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
class QueryMusicPublishingApplicationService implements QueryMusicPublishingUseCase {
	private QueryMusicPublishingRepository repository;
	
	@Override
	public List<Publishing> queryPublishings(String musicId) {
		return repository.queryAllByMusicId(musicId);
	}
}

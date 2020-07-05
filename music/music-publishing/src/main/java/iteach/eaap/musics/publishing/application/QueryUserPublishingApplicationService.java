package iteach.eaap.musics.publishing.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.musics.publishing.application.port.inbound.QueryUserPublishingUseCase;
import iteach.eaap.musics.publishing.application.port.outbound.QueryUserPublishingRepository;
import iteach.eaap.musics.publishing.domain.Publishing;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QueryUserPublishingApplicationService implements QueryUserPublishingUseCase {
	QueryUserPublishingRepository repository;
	@Override
	public List<Publishing> queryAll(String userId) {
		return repository.queryAllByUserId(userId);
	}
	
}

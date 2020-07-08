package iteach.eaap.musics.management.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iteach.eaap.musics.management.adapter.inbound.MusicDTO;
import iteach.eaap.musics.management.application.port.inbound.MusicUseCase;
import iteach.eaap.musics.management.application.port.outbound.MusicRepository;
import iteach.eaap.musics.management.domain.*;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
class MusicApplicationService implements MusicUseCase{
	private final MusicRepository repository;
	
	@Override
	public String addMusic(MusicDTO music) {
		String title = music.getTitle();
		String description = music.getDescription();
		Releasedate releasedate = new Releasedate(music.getReleasedate());
		
		Music entity = new Music(title, description, releasedate);
		repository.add(entity);
		return entity.makeMusicDTO().getId();
	}

	@Override
	public List<MusicDTO> getAllMusics() {
		return repository.queryAll().stream()
				.map(Music::makeMusicDTO)
				.collect(Collectors.toList());
	}

	private Music musicFor(String id) {
		Music music = repository.queryById(MusicId.of(id));

		if(music == null) {
			throw new MusicException("没有找到指定 id 的音乐：" + id);
		}

		return music;
	}

	@Override
	public void publishMusics(String id) {
		Music music = musicFor(id);
		music.publish();
		repository.update(music);
	}

	@Override
	public void closeMusic(String id) {
		Music music = musicFor(id);
		music.close();
		repository.update(music);
	}

	@Override
	public void removeMusic(String id) {
		Music music = musicFor(id);
		music.remove();
		repository.update(music);
	}

	@Override
	public String statusOf(String id) {
		return musicFor(id).status().toString();
	}

	@Override
	public MusicDTO getMusic(String id) {
		Music music = musicFor(id);
		return music.makeMusicDTO();
	}
}

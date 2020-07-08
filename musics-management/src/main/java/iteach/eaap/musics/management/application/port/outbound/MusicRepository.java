package iteach.eaap.musics.management.application.port.outbound;

import java.util.List;

import iteach.eaap.musics.management.domain.Music;
import iteach.eaap.musics.management.domain.MusicId;

public interface MusicRepository {
	List<Music> queryAll();
	void add(Music music);
	void update(Music music);
	Music queryById(MusicId id);
}

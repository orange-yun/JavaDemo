package iteach.eaap.musics.management.adapter.outbound;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iteach.eaap.musics.management.application.port.outbound.MusicRepository;
import iteach.eaap.musics.management.domain.Music;
import iteach.eaap.musics.management.domain.MusicId;

interface JpaMusicRepository extends MusicRepository, JpaRepository<Music, MusicId> {
    @Override
    default void add(Music music) {
        this.save(music);
    }

    @Override
    default void update(Music music) {
        this.save(music);
    }

    @Override
    default List<Music> queryAll() {
        return this.findAll();
    }

    @Override
    default Music queryById(MusicId id) {
        return this.findById(id).orElse(null);
    }

}

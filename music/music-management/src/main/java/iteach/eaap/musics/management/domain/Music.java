package iteach.eaap.musics.management.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import iteach.eaap.musics.management.adapter.inbound.MusicDTO;

@Entity
@Table(name = "musics")
public class Music {
	//嵌入式主键
	@EmbeddedId
	private MusicId id;
	private String title;
	private String description;
	@Embedded
	private Releasedate releasedate;
	private Status status;
	
	// JPA 
	public Music() {
		
	}
		
	// Builder， Factory 创建型模式
	public Music(String title, String description, Releasedate releasedate) {
		this.id = new MusicId();
		this.title = title;
		this.description = description;
		this.releasedate = releasedate;
		this.status = Status.ADDED;
	}
	
	// 迎合显示用的
	public MusicDTO makeMusicDTO() {
		MusicDTO dto = new MusicDTO();
		dto.setTitle(this.title);
		dto.setReleasedate(releasedate.value());
		dto.setDescription(this.description);
		dto.setId(id.value());
		dto.setStatus(status.name());
		return dto;
	}
	
	public void close() {
		status = status.changeTo(Status.CLOSED);
	}
	
	public void publish() {
		status = status.changeTo(Status.PUBLISHED);
	}
	
	public void remove() {
		status = status.changeTo(Status.REMOVED);
	}
	
	public Status status() {
		if(releasedate.isExpired()) {
			status = status.changeTo(Status.EXPIRED);
		}
		
		return status;
	}

	public void changeStatus(Status newStatus) {
		this.status = this.status.changeTo(newStatus);
	}
	
}

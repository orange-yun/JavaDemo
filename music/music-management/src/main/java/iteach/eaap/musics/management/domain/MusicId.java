package iteach.eaap.musics.management.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//值对象
@Embeddable
public class MusicId implements Serializable{
	@Column(name = "id")
	private String value;
	
	public MusicId() {
		this.value = UUID.randomUUID().toString();
	}
	
	public static MusicId of(String id) {
		MusicId musicId = new MusicId();
		musicId.value = UUID.fromString(id).toString();
		return musicId;
	}
	
	//不使用getValue和Lombok
	public String value() {
		return this.value;
	}
	
	public MusicId nextId() {
		return new MusicId();
	}
	
}

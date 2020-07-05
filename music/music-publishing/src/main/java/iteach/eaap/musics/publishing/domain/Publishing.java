package iteach.eaap.musics.publishing.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PUBLISHINGS")
public class Publishing {
	@Id
	private String id;
	private String code;
	private String user;
	private LocalDateTime datetime;
	private String music;
	
	public String getMusicId() {
	    return music;
    }
}

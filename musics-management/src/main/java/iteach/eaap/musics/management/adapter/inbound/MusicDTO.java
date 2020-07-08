package iteach.eaap.musics.management.adapter.inbound;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MusicDTO {
	private String id;
	@NotBlank
	private String title;
	@NotBlank
	@Size(min = 20)
	private String description;
	@Future
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime releasedate;
	private String status;
}

package iteach.eaap.musics.management.adapter.inbound;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import iteach.eaap.musics.management.application.port.inbound.MusicUseCase;
import iteach.eaap.musics.management.domain.MusicException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class MusicController {
	private final MusicUseCase usecase;
	
	// DTO(Data Transfer Object) 模式
	@PostMapping("/musics")
	public ResponseEntity<Object> newMusic(
			@Valid @RequestBody MusicDTO music,
			Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usecase.addMusic(music));
	}
	
	@GetMapping("/musics")
	List<MusicDTO> getAllMusics() {
		return usecase.getAllMusics();
	}

	@GetMapping("/musics/{id}")
	MusicDTO getMusic(@PathVariable String id) {
		try {
			return usecase.getMusic(id);
		}catch (MusicException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		
	}

	@PutMapping("/musics/publish/{id}")
	void publishMusic(@PathVariable String id) {
		usecase.publishMusics(id);
	}

	@PutMapping("/musics/close/{id}")
	void closeMusic(@PathVariable String id) {
		usecase.closeMusic(id);
	}

	@PutMapping("/musics/remove/{id}")
	void removeMusic(@PathVariable String id) {
		usecase.removeMusic(id);
	}

	@GetMapping("/musics/{id}/status")
	String getMusicStatus(@PathVariable String id) {
		return usecase.statusOf(id);
	}

}

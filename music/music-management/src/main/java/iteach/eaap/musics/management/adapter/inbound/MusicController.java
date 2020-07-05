package iteach.eaap.musics.management.adapter.inbound;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import iteach.eaap.musics.management.application.port.inbound.MusicUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
class MusicController {
	private final MusicUseCase usecase;
	
	// DTO(Data Transfer Object) 模式
	@PostMapping("/musics")
	public ResponseEntity<Object> newMusic(
			@RequestBody MusicDTO music,
			@Valid Errors errors) {
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
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
		return usecase.getMusic(id);
	}

	@PutMapping("/musics/publish/{id}")
	void publishMusic(@PathVariable String id) {
		usecase.publishMusics(id);
	}

	@PutMapping("/musics/close/{id}")
	void closeMusic(@PathVariable String id) {
		usecase.closeMusic(id);
	}

	@DeleteMapping("/musics/close/{id}")
	void removeMusic(@PathVariable String id) {
		usecase.removeMusic(id);
	}

	@GetMapping("/musics/{id}/status")
	String getMusicStatus(@PathVariable String id,
            HttpServletRequest request) {
		System.out.println(request.getServerPort());
		return usecase.statusOf(id);
	}
	
	@GetMapping("/dburl")
    String getMysqlUrl(@Value("${spring.datasource.url}") String url) {
	    return url;
    }
}

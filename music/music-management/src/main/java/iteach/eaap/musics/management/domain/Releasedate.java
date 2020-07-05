package iteach.eaap.musics.management.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//值对象
@Embeddable
public class Releasedate {
	@Column(name = "releasedate")
	private LocalDateTime datetime;
	
	public Releasedate() {}
	
	public Releasedate(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	
	private void datetime(LocalDateTime datetime) {
		if(datetime.isBefore(LocalDateTime.now())) {
			//音乐到期日期
			throw new MusicReleasedateException("音乐到期的日期不能早于当前日期");
		}
		
	}
	//此日期比现在的日期早
	public boolean isExpired() {		
		return this.datetime.isBefore(LocalDateTime.now());
	}
	
	public LocalDateTime value() {
		return this.datetime;
	}	
	
	
}

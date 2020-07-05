package iteach.eaap.musics.gateway;

import lombok.Data;

@Data
public class Publishing {
	private String id;
    private String code;
    private String musicId;
    private Music music;
}
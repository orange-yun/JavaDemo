package iteach.eaap.musics.management.application.port.inbound;

import java.util.List;

import iteach.eaap.musics.management.adapter.inbound.MusicDTO;

public interface  MusicUseCase {  //根据接口定义用例
	//添加新音乐
	String addMusic(MusicDTO music);//返回字符串：string；接收参数：MusicDTO
	//获取所有音乐
	List<MusicDTO> getAllMusics();//返回数组
	//获取指定id的音乐详情
	MusicDTO getMusic(String id);
	//发布、关闭、删除
	void publishMusics(String id);
	void closeMusic(String id);
	void removeMusic(String id);
	//查询指定音乐状态
	String statusOf(String id);
	
	
}

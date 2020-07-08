package iteach.eaap.musics.management.domain;

//枚举
public enum Status {
	ADDED,
	PUBLISHED,
	EXPIRED,
	CLOSED,
	REMOVED;

	public Status changeTo(Status newStatus) {
		// 如果当前状态处于 REMOVED，那么它不可以变成其他状态
		if(this == Status.REMOVED) {
			if(newStatus != Status.REMOVED) {
				throw new MusicStatusException("不能修改已删除音乐的状态");
			}
		}
		
		// 如果当前状态不是 ADDED， 那么不允许发布
		if(this != Status.ADDED) {
			if(newStatus == Status.PUBLISHED) {
				throw new MusicStatusException("不能发布一个不是已添加状态的音乐");
			}
		}
		
		// ...
		return newStatus;
	}
}

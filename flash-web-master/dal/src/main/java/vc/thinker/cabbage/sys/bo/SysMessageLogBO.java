package vc.thinker.cabbage.sys.bo;


import vc.thinker.cabbage.sys.model.SysMessageLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class SysMessageLogBO extends SysMessageLog{

	//图文消息连接
	private String cover;
	
	private String title;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
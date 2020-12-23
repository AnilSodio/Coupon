package vc.thinker.apiv2.bo;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * BO 用于返回数据
 *
 */
public class ImgBo{
	
	private String md5;

	  /** 展示时间 **/
    private Integer time;

    /** 图片结果集 **/
    private List<InitImgBO> imgList = new ArrayList<InitImgBO>();

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public List<InitImgBO> getImgList() {
		return imgList;
	}

	public void setImgList(List<InitImgBO> imgList) {
		this.imgList = imgList;
	}
}
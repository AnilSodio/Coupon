package vc.thinker.apiv2.bo;

import io.swagger.annotations.ApiModelProperty;

public class APIRepairerBO {

    /** 姓名 **/
    private String name;

    /** 性别 1，男 2，女 **/
    private Integer sex;

    /** 头像路径 **/
    private String headImgPath;

    /** 手机 **/
    private String mobile;

    @ApiModelProperty("状态 1:正常 2禁用")
    private String status;

   @ApiModelProperty("所属区域")
    private String area;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}

package vc.thinker.apiv2.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

public class RealnameVO {

	@ApiParam(value = "姓名", required = true) 
	@NotBlank
	private String name;
	
	@ApiParam(value = "身份证", required = true)
	@NotBlank
	private String idcard;
	
    @ApiParam(value = "证件图片", required = false) 
    private List<String> credentialsImages;

    @ApiParam(value = "学校", required = false) 
    private String schoolName;

    @ApiParam(value = "学号", required = false) 
    private String studentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public List<String> getCredentialsImages() {
		return credentialsImages;
	}

	public void setCredentialsImages(List<String> credentialsImages) {
		this.credentialsImages = credentialsImages;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}

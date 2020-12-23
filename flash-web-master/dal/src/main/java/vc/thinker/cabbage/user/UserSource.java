package vc.thinker.cabbage.user;

/**
 * 用户来源
 * @author james
 *
 */
public enum UserSource {
	WEIXIN_OPEN("4")//开放平台
	,WEIXIN_PUBLIC("3") //公众平台
	,WEIXIN_APPLETS("5") //小程序
	,QQ("6")
	,WEIBO("7")
	,FACEBOOK("8")
	,GOOGLE("9");
	
	private UserSource(String accountType){
		this.accountType = accountType;
	}
	
	private String accountType;

	public String getAccountType() {
		return accountType;
	}
}
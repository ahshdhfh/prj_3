package kr.co.haerak.domain.user;

public class LoginSessionDomain {
	private String nickName, personalIntro, userImg, userId, ip;
	private int actAreaNum;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPersonalIntro() {
		return personalIntro;
	}
	public void setPersonalIntro(String personalIntro) {
		this.personalIntro = personalIntro;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getActAreaNum() {
		return actAreaNum;
	}
	public void setActAreaNum(int actAreaNum) {
		this.actAreaNum = actAreaNum;
	}
	@Override
	public String toString() {
		return "LoginSessionDomain [nickName=" + nickName + ", personalIntro=" + personalIntro + ", userImg=" + userImg
				+ ", userId=" + userId + ", ip=" + ip + ", actAreaNum=" + actAreaNum + "]";
	}
	
	public LoginSessionDomain() {
		// TODO Auto-generated constructor stub
	}
	public LoginSessionDomain(String nickName, String personalIntro, String userImg, String userId, String ip,
			int actAreaNum) {
		super();
		this.nickName = nickName;
		this.personalIntro = personalIntro;
		this.userImg = userImg;
		this.userId = userId;
		this.ip = ip;
		this.actAreaNum = actAreaNum;
	}
}

package com.zhidi.entity;

/**
 * 税务人员信息表
 * @author 梅佳杰
 *
 */
public class Taxer {

	private Integer id;				//主键
	private String taxerCode;		//税务人员工号
	private String taxerName;		//税务人员名称
	private String mobile;			//税务人员电话
	private String address;			//税务人员地址
	private String sex;				//性别
	private String birthday;			//出身日期
	private String email;			//邮箱
	private Integer organId;		//所属税务机关
	private Integer state;			//有效标志		1有效  0无效 默认0
	private Integer mgr;			//上级领导
	private Integer admin;			//系统管理员标志    1代表是  0代表不是  默认0
	private String recordDate;		//录入日期
	private Integer recordUserId;	//录入人员
	
	public Taxer() {
		super();
	}

	public Taxer(Integer id, String taxerCode, String taxerName, String mobile, String address, String sex,
				 String birthday, String email, Integer organId, Integer state, Integer mgr, Integer admin, String recordDate,
			Integer recordUserId) {
		super();
		this.id = id;
		this.taxerCode = taxerCode;
		this.taxerName = taxerName;
		this.mobile = mobile;
		this.address = address;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.organId = organId;
		this.state = state;
		this.mgr = mgr;
		this.admin = admin;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaxerCode() {
		return taxerCode;
	}

	public void setTaxerCode(String taxerCode) {
		this.taxerCode = taxerCode;
	}

	public String getTaxerName() {
		return taxerName;
	}

	public void setTaxerName(String taxerName) {
		this.taxerName = taxerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(Integer recordUserId) {
		this.recordUserId = recordUserId;
	}

	@Override
	public String toString() {
		return "Taxer [id=" + id + ", taxerCode=" + taxerCode + ", taxerName=" + taxerName + ", mobile=" + mobile
				+ ", address=" + address + ", sex=" + sex + ", birthday=" + birthday + ", email=" + email + ", organId="
				+ organId + ", state=" + state + ", mgr=" + mgr + ", admin=" + admin + ", recordDate=" + recordDate
				+ ", recordUserId=" + recordUserId + "]";
	}

}

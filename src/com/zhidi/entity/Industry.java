package com.zhidi.entity;

import java.util.Date;

/**
 * 行业代码表
 * @author 梅佳杰
 *
 */
public class Industry {

	private Integer id;				//主键
	private String industryName;	//行业名称
	private Date recordDate;		//录入日期
	private Integer recordUserId;	//录入
	
	public Industry() {
		super();
	}

	public Industry(Integer id, String industryName, Date recordDate, Integer recordUserId) {
		super();
		this.id = id;
		this.industryName = industryName;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
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
		return "Industry [id=" + id + ", industryName=" + industryName + ", recordDate=" + recordDate
				+ ", recordUserId=" + recordUserId + "]";
	}

}

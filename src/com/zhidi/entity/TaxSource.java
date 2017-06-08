package com.zhidi.entity;

import java.util.Date;

/**
 * 税源基础信息
 * @author 梅佳杰
 *
 */
public class TaxSource {

	private Integer id;				//主键
	private Integer payerId;		//纳税人id
	private String taskName;		//任务名称
	private Integer taxOrganId;		//下达部门
	private Integer approverId;		//批准人
	private Integer executeId;		//执行人员
	private Date executeTime;		//执行时间
	private String taskFrom;		//任务来源
	private String taskState;		//任务执行状况
	private String idea;			//调查结论或意见
	private Integer riskLevel;		//风险等级
	private Date recordDate;		//录入日期
	private Integer recordUserId;	//录入人员
	
	public TaxSource() {
		super();
	}

	public TaxSource(Integer id, Integer payerId, String taskName, Integer taxOrganId, Integer approverId,
			Integer executeId, Date executeTime, String taskFrom, String taskState, String idea, Integer riskLevel,
			Date recordDate, Integer recordUserId) {
		super();
		this.id = id;
		this.payerId = payerId;
		this.taskName = taskName;
		this.taxOrganId = taxOrganId;
		this.approverId = approverId;
		this.executeId = executeId;
		this.executeTime = executeTime;
		this.taskFrom = taskFrom;
		this.taskState = taskState;
		this.idea = idea;
		this.riskLevel = riskLevel;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPayerId() {
		return payerId;
	}

	public void setPayerId(Integer payerId) {
		this.payerId = payerId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaxOrganId() {
		return taxOrganId;
	}

	public void setTaxOrganId(Integer taxOrganId) {
		this.taxOrganId = taxOrganId;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Integer getExecuteId() {
		return executeId;
	}

	public void setExecuteId(Integer executeId) {
		this.executeId = executeId;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getTaskFrom() {
		return taskFrom;
	}

	public void setTaskFrom(String taskFrom) {
		this.taskFrom = taskFrom;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
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
		return "TaxSource [id=" + id + ", payerId=" + payerId + ", taskName=" + taskName + ", taxOrganId=" + taxOrganId
				+ ", approverId=" + approverId + ", executeId=" + executeId + ", executeTime=" + executeTime
				+ ", taskFrom=" + taskFrom + ", taskState=" + taskState + ", idea=" + idea + ", riskLevel=" + riskLevel
				+ ", recordDate=" + recordDate + ", recordUserId=" + recordUserId + "]";
	}
	
	
}

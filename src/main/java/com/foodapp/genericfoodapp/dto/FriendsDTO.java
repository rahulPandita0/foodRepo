package com.foodapp.genericfoodapp.dto;

import java.util.Date;

public class FriendsDTO {
	
	private Integer id;
	
	private Boolean approve;
	
	private Date  timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Boolean getApprove() {
		return approve;
	}

	public void setApprove(Boolean approve) {
		this.approve = approve;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}

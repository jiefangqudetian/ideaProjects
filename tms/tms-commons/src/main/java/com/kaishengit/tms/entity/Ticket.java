package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Ticket implements Serializable {
    private Long id;

    private String ticketNum;

    private Date ticketInTime;

    private String ticketState;

    private Date createTime;

    private String updateTime;

    private Date ticketOutTime;

    private Date ticketValidityStart;

    private Date ticketValidityEnd;

    private Long customerId;

    private Integer storeAccountId;

    private Integer ticketInRecordId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Date getTicketInTime() {
        return ticketInTime;
    }

    public void setTicketInTime(Date ticketInTime) {
        this.ticketInTime = ticketInTime;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTicketOutTime() {
        return ticketOutTime;
    }

    public void setTicketOutTime(Date ticketOutTime) {
        this.ticketOutTime = ticketOutTime;
    }

    public Date getTicketValidityStart() {
        return ticketValidityStart;
    }

    public void setTicketValidityStart(Date ticketValidityStart) {
        this.ticketValidityStart = ticketValidityStart;
    }

    public Date getTicketValidityEnd() {
        return ticketValidityEnd;
    }

    public void setTicketValidityEnd(Date ticketValidityEnd) {
        this.ticketValidityEnd = ticketValidityEnd;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Integer getTicketInRecordId() {
        return ticketInRecordId;
    }

    public void setTicketInRecordId(Integer ticketInRecordId) {
        this.ticketInRecordId = ticketInRecordId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Ticket other = (Ticket) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTicketNum() == null ? other.getTicketNum() == null : this.getTicketNum().equals(other.getTicketNum()))
            && (this.getTicketInTime() == null ? other.getTicketInTime() == null : this.getTicketInTime().equals(other.getTicketInTime()))
            && (this.getTicketState() == null ? other.getTicketState() == null : this.getTicketState().equals(other.getTicketState()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getTicketOutTime() == null ? other.getTicketOutTime() == null : this.getTicketOutTime().equals(other.getTicketOutTime()))
            && (this.getTicketValidityStart() == null ? other.getTicketValidityStart() == null : this.getTicketValidityStart().equals(other.getTicketValidityStart()))
            && (this.getTicketValidityEnd() == null ? other.getTicketValidityEnd() == null : this.getTicketValidityEnd().equals(other.getTicketValidityEnd()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getStoreAccountId() == null ? other.getStoreAccountId() == null : this.getStoreAccountId().equals(other.getStoreAccountId()))
            && (this.getTicketInRecordId() == null ? other.getTicketInRecordId() == null : this.getTicketInRecordId().equals(other.getTicketInRecordId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTicketNum() == null) ? 0 : getTicketNum().hashCode());
        result = prime * result + ((getTicketInTime() == null) ? 0 : getTicketInTime().hashCode());
        result = prime * result + ((getTicketState() == null) ? 0 : getTicketState().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getTicketOutTime() == null) ? 0 : getTicketOutTime().hashCode());
        result = prime * result + ((getTicketValidityStart() == null) ? 0 : getTicketValidityStart().hashCode());
        result = prime * result + ((getTicketValidityEnd() == null) ? 0 : getTicketValidityEnd().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStoreAccountId() == null) ? 0 : getStoreAccountId().hashCode());
        result = prime * result + ((getTicketInRecordId() == null) ? 0 : getTicketInRecordId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketNum=").append(ticketNum);
        sb.append(", ticketInTime=").append(ticketInTime);
        sb.append(", ticketState=").append(ticketState);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ticketOutTime=").append(ticketOutTime);
        sb.append(", ticketValidityStart=").append(ticketValidityStart);
        sb.append(", ticketValidityEnd=").append(ticketValidityEnd);
        sb.append(", customerId=").append(customerId);
        sb.append(", storeAccountId=").append(storeAccountId);
        sb.append(", ticketInRecordId=").append(ticketInRecordId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
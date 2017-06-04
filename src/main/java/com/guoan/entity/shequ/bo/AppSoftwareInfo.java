package com.guoan.entity.shequ.bo;

import java.io.Serializable;
import java.util.Date;

public class AppSoftwareInfo implements Serializable {
    private String userId;

    private String clientVersion;

    private String clientPlatform;

    private String hannels;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion == null ? null : clientVersion.trim();
    }

    public String getClientPlatform() {
        return clientPlatform;
    }

    public void setClientPlatform(String clientPlatform) {
        this.clientPlatform = clientPlatform == null ? null : clientPlatform.trim();
    }

    public String getHannels() {
        return hannels;
    }

    public void setHannels(String hannels) {
        this.hannels = hannels == null ? null : hannels.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        AppSoftwareInfo other = (AppSoftwareInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getClientVersion() == null ? other.getClientVersion() == null : this.getClientVersion().equals(other.getClientVersion()))
            && (this.getClientPlatform() == null ? other.getClientPlatform() == null : this.getClientPlatform().equals(other.getClientPlatform()))
            && (this.getHannels() == null ? other.getHannels() == null : this.getHannels().equals(other.getHannels()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getClientVersion() == null) ? 0 : getClientVersion().hashCode());
        result = prime * result + ((getClientPlatform() == null) ? 0 : getClientPlatform().hashCode());
        result = prime * result + ((getHannels() == null) ? 0 : getHannels().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}
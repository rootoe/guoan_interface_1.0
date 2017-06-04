package com.guoan.entity.shequ.bo;

import java.io.Serializable;

public class ChatConversation implements Serializable {
    private String conversationId;

    private String aUserId;

    private String bUserId;

    private Integer aUnread;

    private Integer bUnread;

    private static final long serialVersionUID = 1L;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId == null ? null : conversationId.trim();
    }

    public String getaUserId() {
        return aUserId;
    }

    public void setaUserId(String aUserId) {
        this.aUserId = aUserId == null ? null : aUserId.trim();
    }

    public String getbUserId() {
        return bUserId;
    }

    public void setbUserId(String bUserId) {
        this.bUserId = bUserId == null ? null : bUserId.trim();
    }

    public Integer getaUnread() {
        return aUnread;
    }

    public void setaUnread(Integer aUnread) {
        this.aUnread = aUnread;
    }

    public Integer getbUnread() {
        return bUnread;
    }

    public void setbUnread(Integer bUnread) {
        this.bUnread = bUnread;
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
        ChatConversation other = (ChatConversation) that;
        return (this.getConversationId() == null ? other.getConversationId() == null : this.getConversationId().equals(other.getConversationId()))
            && (this.getaUserId() == null ? other.getaUserId() == null : this.getaUserId().equals(other.getaUserId()))
            && (this.getbUserId() == null ? other.getbUserId() == null : this.getbUserId().equals(other.getbUserId()))
            && (this.getaUnread() == null ? other.getaUnread() == null : this.getaUnread().equals(other.getaUnread()))
            && (this.getbUnread() == null ? other.getbUnread() == null : this.getbUnread().equals(other.getbUnread()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConversationId() == null) ? 0 : getConversationId().hashCode());
        result = prime * result + ((getaUserId() == null) ? 0 : getaUserId().hashCode());
        result = prime * result + ((getbUserId() == null) ? 0 : getbUserId().hashCode());
        result = prime * result + ((getaUnread() == null) ? 0 : getaUnread().hashCode());
        result = prime * result + ((getbUnread() == null) ? 0 : getbUnread().hashCode());
        return result;
    }
}
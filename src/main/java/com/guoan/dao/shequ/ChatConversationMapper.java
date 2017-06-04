package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.ChatConversation;
import com.guoan.entity.shequ.bo.ChatConversationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatConversationMapper {
    int countByExample(ChatConversationCriteria example);

    int deleteByExample(ChatConversationCriteria example);

    int deleteByPrimaryKey(String conversationId);

    int insert(ChatConversation record);

    int insertSelective(ChatConversation record);

    List<ChatConversation> selectByExample(ChatConversationCriteria example);

    ChatConversation selectByPrimaryKey(String conversationId);

    int updateByExampleSelective(@Param("record") ChatConversation record, @Param("example") ChatConversationCriteria example);

    int updateByExample(@Param("record") ChatConversation record, @Param("example") ChatConversationCriteria example);

    int updateByPrimaryKeySelective(ChatConversation record);

    int updateByPrimaryKey(ChatConversation record);
}
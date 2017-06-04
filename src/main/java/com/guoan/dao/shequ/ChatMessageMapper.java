package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.ChatMessage;
import com.guoan.entity.shequ.bo.ChatMessageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageMapper {
    int countByExample(ChatMessageCriteria example);

    int deleteByExample(ChatMessageCriteria example);

    int deleteByPrimaryKey(String msgId);

    int insert(ChatMessage record);

    int insertSelective(ChatMessage record);

    List<ChatMessage> selectByExampleWithBLOBs(ChatMessageCriteria example);

    List<ChatMessage> selectByExample(ChatMessageCriteria example);

    ChatMessage selectByPrimaryKey(String msgId);

    int updateByExampleSelective(@Param("record") ChatMessage record, @Param("example") ChatMessageCriteria example);

    int updateByExampleWithBLOBs(@Param("record") ChatMessage record, @Param("example") ChatMessageCriteria example);

    int updateByExample(@Param("record") ChatMessage record, @Param("example") ChatMessageCriteria example);

    int updateByPrimaryKeySelective(ChatMessage record);

    int updateByPrimaryKeyWithBLOBs(ChatMessage record);

    int updateByPrimaryKey(ChatMessage record);
}
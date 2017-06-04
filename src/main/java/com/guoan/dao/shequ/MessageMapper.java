package com.guoan.dao.shequ;

import com.guoan.entity.shequ.bo.Message;
import com.guoan.entity.shequ.bo.MessageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper {
    int countByExample(MessageCriteria example);

    int deleteByExample(MessageCriteria example);

    int deleteByPrimaryKey(String messageId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageCriteria example);

    Message selectByPrimaryKey(String messageId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageCriteria example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageCriteria example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}
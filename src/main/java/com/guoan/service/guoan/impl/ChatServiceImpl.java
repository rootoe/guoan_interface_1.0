package com.guoan.service.guoan.impl;

import com.guoan.dao.shequ.ChatConversationMapper;
import com.guoan.dao.shequ.ChatMessageMapper;
import com.guoan.entity.base.common.CodeEnum;
import com.guoan.entity.base.common.Result;
import com.guoan.entity.shequ.bo.AppUserInfo;
import com.guoan.entity.shequ.bo.ChatConversation;
import com.guoan.entity.shequ.bo.ChatConversationCriteria;
import com.guoan.entity.shequ.bo.ChatMessage;
import com.guoan.service.guoan.ChatService;
import com.guoan.service.redis.RedisService;
import com.guoan.utils.AliyunOSSUtils;
import com.guoan.utils.Const;
import com.guoan.utils.StringUtils;
import com.guoan.utils.collections.ListUtils;
import com.guoan.utils.redis.RedisContants;
import com.guoan.utils.sendurl.SendURL;
import com.guoan.utils.vedio.VedioUtils;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhaotongbeyond@qq.com on 2015/6/2.
 */
@Service
public class ChatServiceImpl extends RedisService implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    ChatConversationMapper chatConversationMapper;

    @Autowired
    ChatMessageMapper chatMessageMapper;

    @Override
    @Transactional
    public Result send(String jsonString, String savePath) {

        Result result = new Result();
        String aliyunOssPath = "";

        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);
        JSONObject paramsObject = JSONObject.fromObject(requestJsonObject.get("params"));
        String token = requestJsonObject.getString("token");

        AppUserInfo user = new AppUserInfo();
        user = super.getRedisUserObject(RedisContants.USER_APP, token,user.getClass());

        if (null == user || StringUtils.isEmpty(user.getUserId())) {
            result.setCode(CodeEnum.tokenErr.getValue());
            result.setMessage(CodeEnum.tokenErr.getDescription());
            return result;
        }

        String to = paramsObject.getString("to");
        String type = paramsObject.getString("type");
        String body = paramsObject.getString("body");

        // 判断语音文件 -- 转发 -- 上传阿里云
        if("audio".equals(type)){
            String uuid = UUID.randomUUID().toString().replace("-", "");
            File file = new File(savePath);
            try {
                FileUtils.forceMkdir(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String saveFile = savePath + uuid + ".mp3";
            String aliyunPath = AliyunOSSUtils.createFoler();
            aliyunPath = aliyunPath + uuid+ ".mp3";
            aliyunOssPath = AliyunOSSUtils.GUOAN_CDN + aliyunPath;
            VedioUtils.mp3Transcoding(body , "64", aliyunPath, saveFile);
        }


        ChatConversationCriteria chatConversationCriteria = new ChatConversationCriteria();
        chatConversationCriteria.createCriteria().andAUserIdEqualTo(user.getUserId()).andBUserIdEqualTo(to);
        List<ChatConversation> chatConversationList = chatConversationMapper.selectByExample(chatConversationCriteria);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if(ListUtils.isEmpty(chatConversationList)){
            // 聊天窗口不存在 新建
            ChatConversation chatConversation = new ChatConversation();
            chatConversation.setConversationId(uuid);
            chatConversation.setaUserId(user.getUserId());
            chatConversation.setbUserId(to);
            chatConversation.setaUnread(1);
            chatConversation.setbUnread(0);
            chatConversationMapper.insertSelective(chatConversation);

        } else {
            // 聊天窗口存在 更新
            ChatConversation chatConversation = chatConversationList.get(0);
            uuid = chatConversation.getConversationId();
            int aUnread = 0;
            if(chatConversation.getaUnread()<=0){
                aUnread = 1;
            } else {
                aUnread = chatConversation.getaUnread()+1;
            }
            chatConversation.setaUnread(aUnread);
            chatConversationMapper.updateByPrimaryKeySelective(chatConversation);
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgId(UUID.randomUUID().toString().replace("-", ""));
        chatMessage.setConversationId(uuid);

        chatMessage.setFromUserId(user.getUserId());
        chatMessage.setToUserId(to);
        chatMessage.setCreateTime(new Date());

        chatMessage.setType(type);
        if("audio".equals(type)) {
            chatMessage.setBody(aliyunOssPath);
        }else{
            chatMessage.setBody(body);
        }
        chatMessage.setIsRead("0");

        chatMessageMapper.insertSelective(chatMessage);

        // 远程刷新
        SendURL.send(Const.CHAT_MESSAGE_NOTIFY_BACKGROUND_URL_1+to);
        SendURL.send(Const.CHAT_MESSAGE_NOTIFY_BACKGROUND_URL_2+to);
        result = Result.successResult();

        return result;
    }
}

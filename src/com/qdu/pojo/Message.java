package com.qdu.pojo;
// Generated 2019-5-7 15:45:56 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * Message generated by hbm2java
 */
public class Message  implements java.io.Serializable {


     private Integer messageId;
         @JsonIgnore
     private Userinfo userinfo;
     private String messageContent;
     private int messageScore;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
     private Date messageTime;

    public Message() {
    }

    public Message(Userinfo userinfo, String messageContent, int messageScore, Date messageTime) {
       this.userinfo = userinfo;
       this.messageContent = messageContent;
       this.messageScore = messageScore;
       this.messageTime = messageTime;
    }
   
    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
    public String getMessageContent() {
        return this.messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public int getMessageScore() {
        return this.messageScore;
    }
    
    public void setMessageScore(int messageScore) {
        this.messageScore = messageScore;
    }
    public Date getMessageTime() {
        return this.messageTime;
    }
    
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }




}



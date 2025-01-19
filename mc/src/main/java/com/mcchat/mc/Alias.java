package com.mcchat.mc;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alias {
    
    @Id
    private String sessionId;  

    private String aliasName;

    public Alias(String sessionId, String aliasName){
        this.sessionId = sessionId;
        this.aliasName = aliasName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

}

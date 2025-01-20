package com.mcchat.mc;




public class Alias  {
    
   
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

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Alias alias = (Alias)obj;
        if(alias.getAliasName().equals(this.aliasName)){
            if(alias.getSessionId().equals(this.sessionId)){
                return true;
            }
        }
        return false;
    }

}

package com.mcchat.mc;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class AliasService {
   
   
   private final  Set<Alias> aliasSet = ConcurrentHashMap.newKeySet();

        public synchronized  boolean  changeAlias(String name, String sessionId){
                for (Alias alias : aliasSet) {
                    if(alias.getAliasName().equals(name) && alias.getSessionId().equals(sessionId)){
                        alias.setAliasName(name);
                        System.out.println("ALIAS CHANGED");
                        return true;
                    }
                }
                System.out.println("Alias change failed due to a taken name or invalid sessionId name combo.");
                return false;
            }
        public synchronized boolean removeAlias(String sessionId){
                for (Alias alias: aliasSet){
                    if(alias.getSessionId().equals(sessionId)){
                        aliasSet.remove(alias);
                        return true;
                    }
                }
                return false;
            }
        public synchronized void addAlias(Alias alias){
            aliasSet.add(alias);
             }
      
}
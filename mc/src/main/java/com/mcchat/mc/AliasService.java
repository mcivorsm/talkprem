package com.mcchat.mc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliasService {
   
    @Autowired
    private final  AliasRepository aliasRepository;

   
    public AliasService(AliasRepository aliasRepository){
        this.aliasRepository = aliasRepository;
    }

    public Alias saveAlias(Alias alias){
        return aliasRepository.save(alias);
    }

    public boolean changeAlias(String aliasName, String sessionId){
  
         Alias aliasToChange = aliasRepository.findById(sessionId).orElse(null);

        
         if (aliasToChange == null) {
             
             return false;
         }
 
       
         aliasToChange.setAliasName(aliasName);  
 

         aliasRepository.save(aliasToChange);
 

         return true;
    }

  


    

}

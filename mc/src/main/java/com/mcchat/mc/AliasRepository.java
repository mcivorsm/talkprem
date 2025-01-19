package com.mcchat.mc;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AliasRepository extends CrudRepository<Alias, String> {
    Optional<Alias> findById(String sessionId);
}
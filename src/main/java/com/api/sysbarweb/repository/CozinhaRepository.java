package com.api.sysbarweb.repository;


import com.api.sysbarweb.model.Cozinha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends CrudRepository<Cozinha, Long> {

}

package com.hoteis.api_cadastro.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.hoteis.api_cadastro.domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseApiRepository<Entity extends Base, Id extends Serializable> extends
    JpaRepository<Entity, Id>, JpaSpecificationExecutor<Entity> {

    Optional<Entity> findByGuid(String guid);

    default List<Entity> saveAll(Entity... entities) {
        return saveAll(List.of(entities));
    }

    default List<Entity> saveAllAndFlush(Entity... entities) {
        return saveAllAndFlush(List.of(entities));
    }
}

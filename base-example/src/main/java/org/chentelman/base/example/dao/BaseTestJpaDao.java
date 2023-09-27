package org.chentelman.base.example.dao;

import org.chentelman.base.example.entity.BaseTestEntity;
import org.chentelman.base.module.data.jpa.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTestJpaDao extends BaseRepository<BaseTestEntity, Long> {

}




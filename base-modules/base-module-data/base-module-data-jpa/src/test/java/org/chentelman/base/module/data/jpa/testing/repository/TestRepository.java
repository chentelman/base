package org.chentelman.base.module.data.jpa.testing.repository;

import org.chentelman.base.module.data.jpa.repository.BaseRepository;
import org.chentelman.base.module.data.jpa.testing.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends BaseRepository<TestEntity, Long>, JpaRepository<TestEntity, Long> {

}




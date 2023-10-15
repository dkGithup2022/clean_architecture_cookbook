package com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

	@Query("select activity from ActivityJpaEntity activity"
		+ " where activity.ownerAccountID = :ownerAccountId "
		+ "and activity.timestamp >= :since")
	List<ActivityJpaEntity> findByOwnerSince(
		@Param("ownerAccountId") Long ownerAccountId,
		@Param("since") LocalDateTime since);

}

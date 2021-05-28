package com.concordia.report.repository;

import org.springframework.stereotype.Repository;

import com.concordia.report.model.Player;

/**
 * CEJV 559 - New Technologies Report Maven Example
 * Player Repository CRUD is responsible to Create, Read, Update, Delete, count, list, pagination, sorting
 *
 * @author Marchiano
 */

@Repository
public interface PlayerRepository extends org.springframework.data.jpa.repository.JpaRepository<Player, Long> {
     
	Player findByName(String name);
		
}
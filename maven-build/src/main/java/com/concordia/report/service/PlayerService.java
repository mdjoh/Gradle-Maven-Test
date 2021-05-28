package com.concordia.report.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.concordia.report.dto.PlayerDTO;
import com.concordia.report.exception.ServiceException;

/**
 * CEJV 559 - New Technologies Report Maven Example
 * Player Service
 */
/**
 * @author Marchiano Oh
 */

@Service
public interface PlayerService {

	PlayerDTO getPlayer(long id) throws ServiceException;

	void savePlayer(PlayerDTO playerDTO) throws ServiceException;

	void updatePlayer(PlayerDTO playerDTO) throws ServiceException;
	
	void deletePlayer(long id) throws ServiceException;

	List<PlayerDTO> getAllPlayers() throws ServiceException;

	Page<PlayerDTO> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

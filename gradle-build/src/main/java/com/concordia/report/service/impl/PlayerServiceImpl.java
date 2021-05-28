package com.concordia.report.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.concordia.report.dto.PlayerDTO;
import com.concordia.report.exception.ServiceException;
import com.concordia.report.model.Player;
import com.concordia.report.repository.PlayerRepository;
import com.concordia.report.service.PlayerService;
import com.github.dozermapper.core.DozerBeanMapperBuilder;

/**
 * CEJV 559 - New Technologies Report Gradle Example
 * Player Service Layer Implementation
 * 
 * @author Marchiano
 *
 */

@Service
public class PlayerServiceImpl implements PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Transactional
	public void savePlayer(PlayerDTO playerDTO) throws ServiceException {
		if (playerDTO == null) {
			throw new ServiceException("Player not saved");
		}
		
		Player player = new Player();
		BeanUtils.copyProperties(playerDTO, player);
		
		playerRepository.save(player);
	}

	public PlayerDTO getPlayer(long id) throws ServiceException {
		Optional<Player> playerRetrieved = playerRepository.findById(id);

		if (playerRetrieved.isEmpty()) {
			throw new ServiceException("Player not found");
		}
		
		Player player = null;
		player = playerRetrieved.get();
		PlayerDTO playerDTO = new PlayerDTO();
		BeanUtils.copyProperties(player, playerDTO);
		return playerDTO;
	}
	
	public List<PlayerDTO> getAllPlayers() throws ServiceException {
		List<Player> allPlayers = this.playerRepository.findAll();
		
		if(allPlayers.isEmpty()) {
			throw new ServiceException("All teachers not found");
		}
			
		List<PlayerDTO> playerDTOs = new ArrayList<PlayerDTO>(allPlayers.size());
        for (Player player : allPlayers) {
        	PlayerDTO playerDTO = new PlayerDTO();
        	BeanUtils.copyProperties(player, playerDTO);
        	playerDTOs.add(playerDTO);
        }

        return playerDTOs;
	}

	@Transactional
	public void updatePlayer(PlayerDTO playerDTO) throws ServiceException {
		Optional<Player> playerRetrieved = playerRepository.findById(playerDTO.getId());
		
		if (playerRetrieved.isEmpty()) {
			throw new ServiceException("Player not found");
		}
		
		Player player = null;
		player = playerRetrieved.get();
		BeanUtils.copyProperties(playerDTO, player);
		playerRepository.save(player);
		
	}

	@Transactional
	public void deletePlayer(long id) throws ServiceException {
		Optional<Player> playerRetrieved = playerRepository.findById(id);
		
		if (playerRetrieved.isEmpty()) {
			throw new ServiceException("Player not found");
		}
		
		playerRepository.deleteById(id);
		
	}
	
	public Page<PlayerDTO> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		Page<PlayerDTO> playerDTOs = this.playerRepository.findAll(pageable)
				.map((player -> DozerBeanMapperBuilder.buildDefault().map(player, PlayerDTO.class)));
		
		return playerDTOs;	
	}

}
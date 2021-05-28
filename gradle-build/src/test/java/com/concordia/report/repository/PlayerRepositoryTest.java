package com.concordia.report.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.concordia.report.model.Player;

/**
 * CEJV 559 - New Technologies Report Gradle Example
 * Player Repository Tests
 * 
 * @author Marchiano
 *
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void testSavePlayer() {
		Player player = new Player("Smith", "Stingers", 100, "Canada");
		playerRepository.save(player);
		Player playerInDB = playerRepository.findByName("Smith");
		assertNotNull(player);
		assertEquals(playerInDB.getName(), player.getName());
		assertEquals(playerInDB.getTeam(), player.getTeam());
	}
	
	@Test
	public void testGetPlayer() {
		Player player = new Player("Smith", "Stingers", 100, "Canada");
		playerRepository.save(player);
		Player playerInDB = playerRepository.findByName("Smith");
		assertNotNull(player);
		assertEquals(playerInDB.getName(), player.getName());
		assertEquals(playerInDB.getTeam(), player.getTeam());
	}
	
	@Test
	public void testUpdatePlayer() {
		Player player = new Player("Smith", "Stingers", 100, "Canada");
		playerRepository.save(player);
		assertNotNull(player);
		player.setName("Doe");
		assertEquals("Doe", player.getName());
		assertEquals("Stingers", player.getTeam());
	}
	
	@Test
	public void testDeletePlayer() {
		Player player = new Player("Smith", "Stingers", 100, "Canada");
		playerRepository.save(player);
		assertNotNull(player);
		playerRepository.delete(player);
	}
	
	@Test
	public void testSaveMultiplePlayers() {
		Set<Player> playerSet = new HashSet<Player>();
		
		Player player1 = new Player("Marner", "Maple Leafs", 37, "Canada");
		playerSet.add(player1);
		
		Player player2 = new Player("Lowry", "Raptors", 7, "USA");
		playerSet.add(player2);
		
		Player player3 = new Player("Suzuki", "Canadiens", 21, "Canada");
		playerSet.add(player3);

		playerRepository.saveAll(playerSet);
		
		assertNotNull(playerRepository.findAll());
		assertEquals(playerSet.size(), playerRepository.count());
	}
	
	@Test
	public void testDeletePlayerById() {
		Player player = new Player("Smith", "Stingers", 100, "Canada");
		Player playerInDB = playerRepository.save(player);
		assertNotNull(playerInDB);
		playerRepository.deleteById(playerInDB.getId());
	}
	
	@Test
	public void testPaginationPlayers() {
		int totalPlayers = 100;
		for (int playerNumber = 0; playerNumber < totalPlayers; playerNumber++) {
			Player player = new Player("player" + playerNumber, "team" + playerNumber,
					playerNumber, "country" + playerNumber);
			playerRepository.save(player);
		}
		
		assertEquals(totalPlayers, playerRepository.count());
		
		Pageable paging = PageRequest.of(1, 10, Sort.unsorted()); // return the index 1 page with 10 instances that are unsorted		
		Page<Player> playerPageResult = playerRepository.findAll(paging);
		
		if (playerPageResult.hasContent()) {
			assertEquals(playerPageResult.getContent().size(), 10);
		}
	}
}
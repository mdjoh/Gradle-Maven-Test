package com.concordia.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concordia.report.dto.PlayerDTO;
import com.concordia.report.exception.ServiceException;
import com.concordia.report.service.PlayerService;

/**
 * CEJV 559 - New Technologies Report Gradle Example
 * Player Controller
 */
/**
 * @author Marchiano
 */

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	// display records of players
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping("/addNewPlayerForm") // URL end for page with add player form
	public String addNewPlayerForm(Model model) {
		// create model attribute to bind form data
		PlayerDTO  playerDTO = new PlayerDTO();
		model.addAttribute("player", playerDTO); // add the attribute value playerDTO to attribute named player
		// name of page in templates to show for this action
		return "new_player";
	}
	
	@PostMapping("/savePlayer")
	public String savePlayer(@ModelAttribute("player") PlayerDTO playerDTO) throws ServiceException {
		
		playerService.savePlayer(playerDTO);
		// return to index page and not returning a "saved player" page
		return "redirect:/";
	}
	
	@GetMapping("/updatePlayerForm/{id}")
	public String updatePlayerForm(@PathVariable ( value = "id") long id, Model model) throws ServiceException {
		
		// get player object from the service and store it in a DTO
		PlayerDTO playerDTO = playerService.getPlayer(id);
		
		// set player as a model attribute to pre-populate the form
		model.addAttribute("player", playerDTO);
		// return update_player.html page
		return "update_player";
	}
	
	@GetMapping("/deletePlayer/{id}")
	public String deletePlayer(@PathVariable (value = "id") long id) throws ServiceException {
		
		// call delete player method in service layer
		this.playerService.deletePlayer(id);
		// return to index page and not returning a "deleted player" page
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		
		int pageSize = 5;
		
		Page<PlayerDTO> page = playerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<PlayerDTO> listPlayers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPlayers", listPlayers);
		return "index";
	}
}
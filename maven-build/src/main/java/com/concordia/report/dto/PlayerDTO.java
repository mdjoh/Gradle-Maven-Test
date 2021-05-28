package com.concordia.report.dto;

import java.io.Serializable;

/**
 * CEJV 559 - New Technologies Report Maven Example
 * Player Data Transfer Object to display information to front-end and to interact with back end.
 *
 * @author Marchiano
 *
 */
public class PlayerDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String team;
	private int points;
	private String country;
	
	/**
	 * Empty Constructor
	 */
	public PlayerDTO() {
		// do nothing.
	}

	/**
	 * Default constructor for Player DTO
	 *
	 * @param name	Name of player
	 * @param team	Team player plays for
	 * @param points	Number of points scored by player
	 * @param country	Country where player is from
	 */
	public PlayerDTO(String name, String team, int points, String country) {
		this.name = name;
		this.team = team;
		this.points = points;
		this.country = country;
	}

	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// hashCode method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + points;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	// equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerDTO other = (PlayerDTO) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (points != other.points)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	// toString method for Player DTO
	@Override
	public String toString() {
		return "PlayerDTO [id=" + id + ", name=" + name + ", team=" + team + ", points=" + points + ", country="
				+ country + "]";
	}
	
	
}
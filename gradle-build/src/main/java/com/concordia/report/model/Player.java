package com.concordia.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CEJV 559 - New Technologies Report Gradle Example
 * Player Model
 */
/**
 * @author Marchiano Oh
 */

@Entity
@Table(name = "player")
public class Player {
	
	private long id;
	private String name;
	private String team;
	private int points;
	private String country;

	/**
	 * Empty Constructor
	 */
	public Player() {
		// do nothing.
	}

	/**
	 * Default constructor for Player
	 *
	 * @param name	Name of player
	 * @param team	Team player plays for
	 * @param points	Number of points scored by player
	 * @param country	Country where player is from
	 */
	public Player(String name, String team, int points, String country) {
		this.name = name;
		this.team = team;
		this.points = points;
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "team", nullable = false)
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Column(name = "points", nullable = false)
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// toString method for Player
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", team=" + team + ", points=" + points + ", country="
				+ country + "]";
	}
}
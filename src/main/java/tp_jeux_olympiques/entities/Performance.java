package tp_jeux_olympiques.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tp_jeux_olympiques.enums.Medal;

@Entity
@Table(name = "performance")
public class Performance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 15)
	@Enumerated(value = EnumType.STRING)
	private Medal medal;
	
	@ManyToOne
	@JoinColumn(name = "id_athlete")
	private Athlete athlete;
	
	@ManyToOne
	@JoinColumn(name = "id_event")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "id_team")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "id_games")
	private OlympicGamesEdition olympicGamesEdition;

	public Performance(Athlete athlete, Event event, Team team, OlympicGamesEdition olympicGamesEdition, Medal medal) {
		this.athlete = athlete;
		athlete.addPerformance(this);
		this.event = event;
		event.addPerformance(this);
		this.team = team;
		this.olympicGamesEdition = olympicGamesEdition;
		olympicGamesEdition.addPerformance(this);
		this.medal = medal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(athlete, event, medal, olympicGamesEdition, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Performance)) {
			return false;
		}
		Performance other = (Performance) obj;
		return Objects.equals(athlete, other.athlete) && Objects.equals(event, other.event) && medal == other.medal
				&& Objects.equals(olympicGamesEdition, other.olympicGamesEdition) && Objects.equals(team, other.team);
	}

	/**
	 * Getter for {@link #id}.
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for {@link #medal}.
	 *
	 * @return
	 */
	public Medal getMedal() {
		return medal;
	}

	/**
	 * Getter for {@link #athlete}.
	 *
	 * @return
	 */
	public Athlete getAthlete() {
		return athlete;
	}

	/**
	 * Getter for {@link #event}.
	 *
	 * @return
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Getter for {@link #team}.
	 *
	 * @return
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Getter for {@link #olympicGamesEdition}.
	 *
	 * @return
	 */
	public OlympicGamesEdition getOlympicGamesEdition() {
		return olympicGamesEdition;
	}

	/**
	 * Setter for {@link #medal}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setMedal(Medal medal) {
		this.medal = medal;
	}

	/**
	 * Setter for {@link #athlete}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * Setter for {@link #event}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * Setter for {@link #team}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Setter for {@link #olympicGamesEdition}.
	 *
	 * @param The new ATTRIBUTE to replace the current one
	 */
	public void setOlympicGamesEdition(OlympicGamesEdition olympicGamesEdition) {
		this.olympicGamesEdition = olympicGamesEdition;
	}
	
}

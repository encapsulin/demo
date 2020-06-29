package encaps.amaterbox;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@ManyToOne
    @JoinColumn(name="categ_id", nullable=false)
    private Categ categ;

	String date;
	String result;
	String rounds;	
	String round_length;
	String gender;
	String country;
	String location;
	String tournament;
	String notes;
	String fight_winner;
	String fighter_1_name;
	String fighter_1_country;
	String fighter_1_state_team;
	String fighter_1_weight;
	String fighter_2_name;
	String fighter_2_country;
	String fighter_2_state_team;
	String fighter_2_weight;
	
}

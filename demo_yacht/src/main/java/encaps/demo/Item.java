package encaps.demo;

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
    @JoinColumn(name="categ_id", nullable=true)
    private Categ categ;

	String title;
	String href;	
	
	String title2;
	String title3;
	String href2;
	String type;
	String price;
	String selerName;
	String selerTelephone;
	String selerMobile;
	String selerFax;
	String selerWebsite;
	String selerEmail;

	@Override
	public String toString() {
		return "\nItem [id=" + id 
				+ "\n, href=" + href 
				+ "\n, title=" + title 				
				+ "\n, type=" + type 
				+ "\n, price=" + price 
				+ "\n, selerName=" + selerName 
				+ "\n, selerTelephone=" + selerTelephone 
				+ "\n, selerMobile=" + selerMobile 
				+ "\n, selerWebsite=" + selerWebsite 
				+ "\n, selerFax=" + selerFax 
				+ "\n, selerEmail=" + selerEmail 
				+ "]";
	}
		
}

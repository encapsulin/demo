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
	String title; 
	Integer page;
	Boolean done;

	String col1;
	String col2;
	String col3;
	String col4;		
	String col5;
	String col6;
	String col7;
	String col8;
	String col9;
	String col10;
	String col11;
	String col12;
	String col13;
	String col14;		
	String col15;
	String col16;
	String col17;
	String col18;
	String col19;
	String col20;	
	String col21;
	String col22;	
		
//	String Toelichting;
//	String Antwoorden;
//	String Formule;
//	String Formuleuitleg;
//	String Clusters;
	String scol1;
	String scol2;
	String scol3;
	String scol4;
	String scol5;
	//Nr.	Memo	Mat.	Bijz.	Omschrijving arrow_drop_down	Kort	AUB	Hoofd​aanvrg	Soort	Groep	Select.	VO	VM	VV	Toe​lichting	Type	Dec.	Een​heid	Abs ​min	Abs ​max	Ref ​min	Ref ​max	Antwoordenlijst	Formule	
	@Override
	public String toString() {
		return "\n\nItem [id=" + id 
//				+ "\n, title=" + title 
				+ "\n, col1/Nr.=" + col1 
				+ "\n, col2/Memo=" + col2 
				+ "\n, col3/Mat.=" + col3 
				+ "\n, col4/Bijz.=" + col4
				+ "\n, col5/Omschrijving=" + col5 
				
				+ "\n, scol1/Toelichting=" + scol1
				+ "\n, scol2/Antwoorden=" + scol2
				+ "\n, scol3/Formule=" + scol3
				+ "\n, scol4/Formuleuitleg=" + scol4
				
				+ "]";
	}



		
}

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

	String col1;
	
	String col2;
		
	String col3;
		
	String col4;		
		
	String col5;
//		
	String col6;
//		
	String col7;
//		
	String col8;
//		
	String col9;
//		
	String col10;
//		
//	String IVO;
//		
//	String IVM;
//		
//	String IVV;
//		
//	String IToe​lichting;
//		
//	String IType;
//		
//	String IDec;
//		
//	String IEen​heid;
//		
//	String IAbs​min;
//		
//	String IAbs​max;
//		
//	String IRef​min;
//		
//	String IRefmax;
//		
//	String IAntwoordenlijst;
//		
//		
//	String Toelichting;
//	String Antwoorden;
//	String Formule;
//	String Formuleuitleg;
//	String Clusters;
	//Nr.	Memo	Mat.	Bijz.	Omschrijving arrow_drop_down	Kort	AUB	Hoofd​aanvrg	Soort	Groep	Select.	VO	VM	VV	Toe​lichting	Type	Dec.	Een​heid	Abs ​min	Abs ​max	Ref ​min	Ref ​max	Antwoordenlijst	Formule	
	@Override
	public String toString() {
		return "\n\nItem [id=" + id 
				+ "\n, title=" + title 
				+ "\n, Nr.=" + col1 
				+ "\n, Memo=" + col2 
				+ "\n, Mat.=" + col3 
				+ "\n, Bijz.=" + col4
				+ "\n, Omschrijving=" + col5 
//				+ "\n, AUB=" + sAUB 
				
				+ "]";
	}



		
}

package encaps.demo;

import org.springframework.data.repository.CrudRepository;

public interface CategRepo extends CrudRepository<Categ, Integer> {

//	 Categ findByHref(String href);

}

package encaps.demo;

import org.springframework.data.repository.CrudRepository;


public interface CategRepo extends CrudRepository<Categ, Integer> {

	Iterable<Categ> findByParentIdNotNull();

	Iterable<Categ> findByParentIdNull();

	Iterable<Categ> findByParentId(Integer id);

}

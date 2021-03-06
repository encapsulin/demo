package encaps.demo;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Integer> {

	Iterable<Item> findByPriceNull();

	Iterable<Item> findByCategId(Integer id);

}

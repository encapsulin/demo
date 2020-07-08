package encaps.demo;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Integer> {

	Iterable<Item> findAllByCol5NotNull();

	Iterable<Item> findAllTopTenByCol4NotNull();

	Iterable<Item> findAllByCol1NotNullAndScol1NullAndDoneNull();



}

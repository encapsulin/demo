package helloWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ItemController {

	@Autowired
	ItemRepository repo;

	@GetMapping
	public String item(Model model) {
//		Iterable<ItemEntity> list = repo.findAll();

		List<ItemEntity> list = repo.findAllByOrderByIdDesc();
		// System.out.println(list);

//		Iterator itr = itemsIter.iterator();
//
//		List<ItemEntity> list = new ArrayList<>();
//
//		if (!itr.hasNext())
//			list.add(new ItemEntity(1,"empty"));
//		else
//			do {
//				list.add((ItemEntity) itr.next());
//			} while (itr.hasNext());

		// System.out.println(list);
//		Collections.sort(list, (i1,i2)-> i1.id - i2.id);
		model.addAttribute("items", list);
		return "items";
	}

//	@PostMapping
//	public String add(ItemEntity entity) {
//		System.out.println(entity);
//		repo.save(entity);
//		return "redirect:/";
//	}

	@GetMapping("/item/edit")
	public String add(@RequestParam(required = false) Integer id, Model model) {
		ItemEntity entity = new ItemEntity();
		if (id != null && id > 0) {
			Optional<ItemEntity> opt = repo.findById(id);
			if (opt.isPresent())
				entity = opt.get();
		}
		model.addAttribute("item", entity);
		return "item";
	}
	
	@PostMapping("/item/submit")
	public String submit(ItemEntity entity) {
		System.out.println(entity);
		repo.save(entity);
		return "redirect:/";
	}
	
	@GetMapping("/item/del")
	public String del(ItemEntity entity) {
		System.out.println(entity);
		Optional<ItemEntity> opt = repo.findById(entity.id);
		if (opt.isPresent())
			repo.delete(opt.get());
		return "redirect:/";
	}
}

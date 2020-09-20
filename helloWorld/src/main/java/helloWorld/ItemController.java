package helloWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public String item(Model model, @RequestParam(required = false) Integer page) {
//		Iterable<ItemEntity> list = repo.findAll();

//		List<ItemEntity> list = repo.findAllByOrderByIdDesc();

		long count = repo.count();
		page = (page == null) ? 0 : page;
		int size = 50;
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i < count / size; i++)
			listPage.add(i);
		model.addAttribute("pages", listPage);
		Pageable pageAble = PageRequest.of(page, size);
		List<ItemEntity> list = repo.findAllByOrderByIdDesc(pageAble);

		System.out.println(list.size());

		model.addAttribute("items", list);
		return "items";
	}

	@GetMapping("/item/edit")
	public String add(@RequestParam(required = false) Integer id, Model model) {
		// Integer id = Integer.parseInt(sId.replaceAll("[^0-9]", ""));
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

package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.domain.Category;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.repositories.CategoryRepository;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String getIndexPage(Model model){
        model.addAttribute("recipe","Testeeee");

        Optional<Category>  categoryOptional = this.categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = this.unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is: "+categoryOptional.get().getId());
        System.out.println("Unit id is: "+unitOfMeasureOptional.get().getId());

        return "index";
    }
}

package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe",recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/" + savedCommand.getId()+"/show";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", this.recipeService.findCommandById(id));

        return "/recipe/recipeForm";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable Long id){
        recipeService.deleteById(id);

        return "redirect:/";
    }
}

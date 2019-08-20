package guru.springframework.recipeproject.bootstrap;

import guru.springframework.recipeproject.domain.*;
import guru.springframework.recipeproject.repositories.CategoryRepository;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        this.recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        log.debug("Get Recipes....");

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = this.unitOfMeasureRepository.findByDescription("Each");

        Optional<UnitOfMeasure> tablespoonUomOptional = this.unitOfMeasureRepository.findByDescription("Tablespoon");

        Optional<UnitOfMeasure> teapoonUomOptional = this.unitOfMeasureRepository.findByDescription("Teaspoon");

        Optional<UnitOfMeasure> dashUomOptional = this.unitOfMeasureRepository.findByDescription("Dash");

        Optional<UnitOfMeasure> pintUomOptional = this.unitOfMeasureRepository.findByDescription("Pint");

        Optional<UnitOfMeasure> cupUomOptional = this.unitOfMeasureRepository.findByDescription("Cup");

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teapoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pinUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        Optional<Category> optionalAmericanCategory = this.categoryRepository.findByDescription("American");

        Optional<Category> optionalMexicanCategory = this.categoryRepository.findByDescription("Mexican");

        Category americanCategory = optionalAmericanCategory.get();
        Category mexicanCategory = optionalMexicanCategory.get();

        log.debug("American Category" + americanCategory.getDescription());

        //Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.getCategories().add(americanCategory);

        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(4);
        guacRecipe.setDifficulty(Difficulty.EASY);

        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("tomatos",new BigDecimal(3), eachUom));
        guacRecipe.addIngredient(new Ingredient("pepper",new BigDecimal(1), eachUom));

        recipes.add(guacRecipe);

        return recipes;
    }
}

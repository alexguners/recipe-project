package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {

        try{
            Recipe recipe = recipeRepository.findById(id).get();

            Byte[] bytesObject = new Byte[file.getBytes().length];

            int i=0;

            for(byte b : file.getBytes()){
                bytesObject[i++] = b;
            }

            recipe.setImage(bytesObject);

            recipeRepository.save(recipe);

        }catch (IOException e){

            log.error("Error occured ",e);

            e.printStackTrace();
        }
        log.debug("Received a File");
    }
}

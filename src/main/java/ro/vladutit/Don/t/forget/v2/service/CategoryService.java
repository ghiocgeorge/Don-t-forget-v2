package ro.vladutit.Don.t.forget.v2.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public void addNewCategory (Category category) {
        Optional<Category> optional =
                categoryRepository.findByNameIgnoreCaseAndUserId(category.getName(), category.getUser().getId());
        // Only if is not an update for another category
        // If the category name doesn't already exists for the user
        if(category.getId() == null) {
            if(optional.isEmpty()) {
                categoryRepository.save(category);
            } else {
                throw new RuntimeException("Category name already exists! (" + category.getName() + ")");
            }
        } else {
            throw new RuntimeException("You can only add new categories!");
        }
    }

    public void updateCategory(Category category) {
        Optional<Category> optional =
                categoryRepository.findByNameIgnoreCaseAndUserId(category.getName(), category.getUser().getId());
        // Only if is an update for another category, so the id isn't null
        // If the new name is equal with the old name, it is ok, but
        // If is not ok, If the category name doesn't already exists for the user
        if(category.getId() != null) {
            Category categoryDB = getCategoryById(category.getId());
            if(category.getName().toUpperCase().equals(categoryDB.getName().toUpperCase())) {
                categoryRepository.save(category);
            } else {
                if(optional.isEmpty()) {
                    categoryRepository.save(category);
                } else {
                    throw new RuntimeException("Category name already exists! (" + category.getName() + ")");
                }
            }
        } else {
            throw new RuntimeException("You can only update old categories!");
        }
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;
        if(optional.isPresent()) {
            category = optional.get();
        } else {
            throw new RuntimeException("Category not found for: " + id);
        }
        return category;
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public boolean getCategoryByNameAndUserId(String name, Long user_id) {
        Optional<Category> optional = categoryRepository.findByNameIgnoreCaseAndUserId(name, user_id);
        return optional.isPresent();
    }

    public List<Category> getByUserId(Long userId){
        return categoryRepository.getByUserId(userId);
    }
}
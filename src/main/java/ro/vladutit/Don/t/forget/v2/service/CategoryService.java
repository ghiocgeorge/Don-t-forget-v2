package ro.vladutit.Don.t.forget.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public void addCategory (Category category) {
        categoryRepository.save(category);
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

    public boolean getCategoryByName(String name) {
        Optional<Category> optional = categoryRepository.findByNameIgnoreCase(name);
        return optional.isPresent();
    }

    public List<Category> getByUserId(Long userId){
        return categoryRepository.getByUserId(userId);
    }
}

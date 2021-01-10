package ro.vladutit.Don.t.forget.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;
import ro.vladutit.Don.t.forget.v2.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    public void addItem (Item item) {
        itemRepository.save(item);
    }

    public Item getItemById(Long id) {
        Optional<Item> optional = itemRepository.findById(id);
        Item item = null;
        if(optional.isPresent()) {
            item = optional.get();
        } else {
            throw new RuntimeException("Item not found for id: " + id);
        }
        return item;
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getByCategoryId(Long categoryId){
        return itemRepository.getByCategoryId(categoryId);
    }

    public List<Item> getByUserId(Long userId){
        return itemRepository.getByUserId(userId);
    }
}

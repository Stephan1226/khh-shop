package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public void updateItem(String title, Integer price, Integer id){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        item.setId(id);
        itemRepository.save(item);
    }

    public void deleteItem(Integer id){
        Item item = new Item();
        item.setId(id);
        itemRepository.delete(item);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    public Page<Item> findPageBy(Integer abc, Integer pageSize) {
        return itemRepository.findPageBy(PageRequest.of(abc - 1, pageSize));
    }
}

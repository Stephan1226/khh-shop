package com.example.khhshop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Slice<Item> findSliceBy(Pageable page);
    Page<Item> findPageBy(Pageable page);

    @Query(value = "SELECT * FROM class.item WHERE MATCH(title) AGAINST(?1)",  nativeQuery = true)
    List<Item> fullTextSearch(String text);
}

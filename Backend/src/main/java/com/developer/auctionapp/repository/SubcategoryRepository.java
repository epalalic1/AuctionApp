package com.developer.auctionapp.repository;

import com.developer.auctionapp.entity.Category;
import com.developer.auctionapp.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    /**
     * <p>Method that is supported with a derived query to find list of subcategories by the category</p>
     *
     * @param category object based on which we are looking for a subcategories
     * @return list of Subcategory objects that are connected with the category which is sent as a parameter
     */

    @Query
    List<Subcategory> findByCategory(Category category);

    /**
     * <p>Method that is supported with a derived query to find list of subcategory by the name</p>
     * @param name string  based on which we are looking for a subcategory
     * @return subcategory object with specific name
     */

    @Query
    Subcategory findByName(String name);
}

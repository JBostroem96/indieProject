package edu.matc.persistence;

import edu.matc.entity.Category;
import edu.matc.entity.Division;
import edu.matc.testUtil.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDaoTest {

    private GenericDao categoryDao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("clean_DB.sql");
        categoryDao = new GenericDao(Category.class);
    }


    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Category retrievedCategory = (Category) categoryDao.getById(1);
        assertNotNull(retrievedCategory);
        assertEquals(categoryDao.getById(1), retrievedCategory);
    }

    /**
     * Update.
     */
    @Test
    void update() {

        Category category = new Category(Division.SOLO_FEMALE);

        Category categoryToUpdate = (Category)categoryDao.getById(1);

        categoryToUpdate.setDivision(category.getDivision());

        categoryDao.update(categoryToUpdate);

        Category updatedCategory = (Category)categoryDao.getById(1);
        assertEquals(categoryToUpdate, updatedCategory);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        Category category = new Category(Division.SOLO_MALE);
        int insertedCategoryId = categoryDao.insert(category);
        assertNotEquals(0, insertedCategoryId);
        Category insertedCategory = (Category) categoryDao.getById(insertedCategoryId);
        assertEquals(category, insertedCategory);
    }

    /**
     * Delete.
     */

    @Test
    void delete() {

        categoryDao = new GenericDao(Category.class);
        categoryDao.delete(categoryDao.getById(2));
        assertNull(categoryDao.getById(2));

    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {

        List<Category> categories = categoryDao.getAll();
        assertEquals(5, categories.size());
    }

    /**
     * This test's purpose is to ensure that the property has the
     * matching value. In this case "Joe" is a first name in
     * the table
     */
    @Test
    void getByPropertyEqual() {

        List<Category> categories = categoryDao.findByPropertyEqual("division", "Mixed");
        assertEquals(1, categories.size());
        assertEquals(5, categories.get(0).getCategory_id());
    }
}

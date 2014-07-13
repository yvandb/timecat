/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import yvandb.timecat.model.Category;
import yvandb.timecat.model.TimeCatApi;

public class CategoryTest {

    private final TimeCatApi api = new TimeCatApiJdbi();

    private void assertFieldsEqualTo(Category expected, Category actual) {
        assertThat(expected.getId(), equalTo(actual.getId()));
        assertThat(expected.getCategory(), equalTo(actual.getCategory()));
    }

    @Test
    public void create() {
        Category item = api.createCategory(" category ");
        assertThat(item, notNullValue());
        assertThat(item.getId(), notNullValue());
        assertThat(item.getCategory(), equalTo("category"));
    }

    @Test(expected = IllegalAccessError.class)
    public void createNull() {
        api.createCategory(null);
    }

    @Test(expected = IllegalAccessError.class)
    public void createEmpty() {
        api.createCategory(" ");
    }

    @Test(expected = IllegalAccessError.class)
    public void createDouble() {
        api.createCategory(" category");
        api.createCategory("  category");
    }

    @Test
    public void find() {
        List<Category> list = api.findCategories();
        assertThat(list.size(), equalTo(0));

        Category item1 = api.createCategory("cat1");
        list = api.findCategories();
        assertThat(list.size(), equalTo(1));
        assertFieldsEqualTo(list.get(0), item1);
    }

    @Test
    public void defaultSort() {
        Category item1 = api.createCategory("cat1");
        Category item3 = api.createCategory("cat3");
        Category item2 = api.createCategory("cat2");
        List<Category> list = api.findCategories();
        assertThat(list.size(), equalTo(3));
        assertFieldsEqualTo(list.get(0), item1);
        assertFieldsEqualTo(list.get(1), item2);
        assertFieldsEqualTo(list.get(2), item3);
    }
}

/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import yvandb.timecat.model.CatEntryAction;
import yvandb.timecat.model.Category;
import yvandb.timecat.model.Entry;
import yvandb.timecat.model.ImportApi;
import yvandb.timecat.model.TimeCatApi;

public class CatEntryTest {

    private final ImportApi importApi = new ImportApiJdbi();

    private final TimeCatApi api = new TimeCatApiJdbi();

    @Before
    public void setup() {
        // create 3 entries

    }

    private void assertFieldsEqualTo(CatEntryAction expected, CatEntryAction actual) {
        assertThat(expected.getId(), equalTo(actual.getId()));
        assertThat(expected.getCategoryId(), equalTo(actual.getCategoryId()));
        assertThat(expected.getEntryIds(), equalTo(actual.getEntryIds())); // TODO check array equalTo
        assertThat(expected.getDatetime(), equalTo(actual.getDatetime()));
        assertThat(expected.getDeleted(), equalTo(actual.getDeleted()));
        assertThat(expected.getSearchContext(), equalTo(actual.getSearchContext()));
    }

    @Test
    public void single() {
        List<Entry> list = api.findUnassignedEntries(null);
        assertThat(list.size(), equalTo(3));
        Entry entry = list.get(1);

        Category item = api.createCategory("category");
        CatEntryAction action = api.assignEntriesToCategory(item.getId(), Collections.singleton(entry.getId()), null);
        assertThat(catList.size(), equalTo(1));
        assertThat(catList.get(0), equalTo(entry));

        list = api.findUnassignedEntries(null);
        assertThat(list.size(), equalTo(2));
        assertThat(list, not(hasItem(entry)));

        catList = api.findEntriesByCategory(item.getId());
        assertThat(catList.size(), equalTo(1));
        assertThat(catList.get(0), equalTo(entry));
    }

    @Test(expected = IllegalAccessError.class)
    public void reassign() {
        List<Entry> list = api.findUnassignedEntries(null);
        Entry entry = list.get(1);

        Category item = api.createCategory("category");
        api.assignEntriesToCategory(item.getId(), Collections.singleton(entry.getId()), null);
        api.assignEntriesToCategory(item.getId(), Collections.singleton(entry.getId()), null);
    }

    @Test(expected = IllegalAccessError.class)
    public void notexistingCategory() {
        List<Entry> list = api.findUnassignedEntries(null);
        Entry entry = list.get(1);

        api.assignEntriesToCategory(Long.valueOf(-1), Collections.singleton(entry.getId()), null);
    }

    @Test(expected = IllegalAccessError.class)
    public void notexistingEntry() {
        Category item = api.createCategory("category");
        api.assignEntriesToCategory(item.getId(), Collections.singleton(Long.valueOf(-1)), null);
    }
}

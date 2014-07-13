/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.impl;

import java.time.ZonedDateTime;
import java.util.List;

import yvandb.timecat.model.CatEntryAction;
import yvandb.timecat.model.Category;
import yvandb.timecat.model.Entry;
import yvandb.timecat.model.TimeCatApi;

public class TimeCatApiJdbi implements TimeCatApi {

    private Long nextSeq() {
        return createQuery("select nextval('shared_seq')");
    }

    public Category createCategory(String category) {
        Category bean = new Category(nextSeq(), category);
        createQuery("insert into categories(id, category) values :category");
        return bean;
    }

    public List<Category> findCategories() {
        List<Category> list = createQuery("select * from categories order by category");
        return list;
    }

    public List<Entry> findEntriesByCategory(Long categoryId) {
        List<Entry> list = createQuery("select * from entries where id in (select entry_id from cat_entries where category_id = :categoryId) order by id");
        return list;
    }

    public List<String> findSearchContextsByCategory(Long categoryId) {
        List<String> list = createQuery("select search_context from cat_search_contexts where category_id = :categoryId order by search_context");
        return list;
    }

    public List<Entry> findUnassignedEntries(String searchString) {
        List<Entry> list = createQuery("select * from entries where id not in (select entry_id from cat_entries) order by id");
        return list;
    }

    public List<CatEntryAction> findActions() {
        List<CatEntryAction> list = createQuery("select * from category_entry_actions order by id");
        return list;
    }

    public CatEntryAction assignEntriesToCategory(Long categoryId, Iterable<Long> entryIds, String searchContext) {
        CatEntryAction bean = new CatEntryAction(nextSeq(), categoryId, entryIds, ZonedDateTime.now(), Boolean.FALSE, searchContext);
        createQuery("insert into category_entry_actions(id, category_id, entry_ids, datetime, deleted, search_context) values (?, ?, ?, ?, ?, ?)");
        for (Long entryId : entryIds) {
            createQuery("insert into category_entries(entry_id, category_id, action_id) values (:entryId, :categoryId, :actionId)");
        }
        return bean;
    }
}

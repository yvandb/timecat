/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.model;

import com.google.common.base.Objects;

public class Category {

    private final Long id;

    private final String category;

    public Category(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", id).add("category", category).toString();
    }
}

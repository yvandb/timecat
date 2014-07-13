/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.model;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.sun.istack.internal.Nullable;

public class CatEntryAction {

    private final Long id;

    private final Long categoryId;

    private final Iterable<Long> entryIds;

    private final ZonedDateTime datetime;

    private final Boolean deleted;

    private final Optional<String> searchContext;

    public CatEntryAction(@Nullable Long id, Long categoryId, Iterable<Long> entryIds, @Nullable ZonedDateTime datetime, @Nullable Boolean deleted,
            @Nullable String searchContext) {
        this.id = id;
        this.categoryId = categoryId;
        this.entryIds = entryIds;
        this.datetime = datetime;
        this.deleted = deleted;
        this.searchContext = Optional.ofNullable(searchContext);
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Iterable<Long> getEntryIds() {
        return entryIds;
    }

    public ZonedDateTime getDatetime() {
        return datetime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Optional<String> getSearchContext() {
        return searchContext;
    }
}

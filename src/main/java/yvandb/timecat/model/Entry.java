/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.model;

import java.time.ZonedDateTime;

public class Entry {

    private final Long id;

    private final ZonedDateTime datetime;

    private final Double amount;

    private final String description;

    public Entry(Long id, ZonedDateTime datetime, Double amount, String description) {
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDatetime() {
        return datetime;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

}

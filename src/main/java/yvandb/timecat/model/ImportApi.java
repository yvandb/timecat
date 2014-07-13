/*
 * Copyright (C) 2014 Yvan De Boeck yvandb@gmail.com
 * All rights reserved.
 */
package yvandb.timecat.model;

public interface ImportApi {

    int createEntries(Iterable<Entry> entries);
}

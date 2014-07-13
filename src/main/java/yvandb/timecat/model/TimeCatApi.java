package yvandb.timecat.model;

import java.util.List;

import com.sun.istack.internal.Nullable; // TODO should come from annotations jar

public interface TimeCatApi {

    Category createCategory(String category);

    List<Category> findCategories();

    List<Entry> findEntriesByCategory(Long categoryId);

    List<String> findSearchContextsByCategory(Long categoryId);

    List<Entry> findUnassignedEntries(@Nullable String searchString);

    List<CatEntryAction> findActions();

    CatEntryAction assignEntriesToCategory(Long categoryId, Iterable<Long> entryIds, @Nullable String searchContext);
}

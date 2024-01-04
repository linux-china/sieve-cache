package org.mvnsearch;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Cache API
 *
 * @author linux_china
 */
public interface Cache<T> {

    void put(@NotNull String key, @NotNull T value);

    @Nullable
    T get(@NotNull String key);

    void delete(@NotNull String key);

    int capacity();

    int size();

    void clear();

    boolean containsKey(@NotNull String key);

    Map<String, T> getAll(Set<String> keys);

    boolean putIfAbsent(@NotNull String key, @NotNull T value);

    @Nullable
    T getAndRemove(@NotNull String key);

    T getAndPut(@NotNull String key, @NotNull T value);
}

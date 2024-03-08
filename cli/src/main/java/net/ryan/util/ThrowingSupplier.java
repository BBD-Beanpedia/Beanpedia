package net.ryan.util;

@FunctionalInterface
public interface ThrowingSupplier<S> {
    S get() throws Exception;
}
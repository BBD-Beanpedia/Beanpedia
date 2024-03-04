package net.ryan.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public class Result<S> implements Supplier<S> {
    private final Exception exception;
    private final S supplier;

    private Result(Exception e, S s) {
        this.exception = e;
        this.supplier = s;
    }

    public static <S> Result<S> success(S s) {
        return new Result<>(null, Objects.requireNonNull(s));
    }

    public static <S> Result<S> fail(Exception e) {
        return new Result<>(Objects.requireNonNull(e), null);
    }

    @Override
    public S get() {
        if (isError())
            throw new NoSuchElementException("Attempted to retrieve value on erroneous result");
        return supplier;
    }

    public boolean isError() {
        return exception != null;
    }
}

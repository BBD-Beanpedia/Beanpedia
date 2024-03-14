package net.ryan.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
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

    public Result<S> ifSuccess(Consumer<S> consumer) {
        if (isSuccess()) consumer.accept(supplier);
        return this;
    }

    public Result<S> ifError(Consumer<Exception> consumer) {
        if (isError()) consumer.accept(exception);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <N> Result<N> map(Function<S, N> f) {
        // Here we can safely cast as we know that we have an error type.
        if (isError()) return (Result<N>) this;
        return Result.from(() -> f.apply(supplier));
    }

    public Result<S> mapDirect(Function<S, Result<S>> f) {
        if (isError()) return this;
        return f.apply(supplier);
    }

    public <T> Result<T> mapToNew(Function<S, Result<T>> f) {
        if (isError()) return Result.fail(this.getError());
        return f.apply(supplier);
    }

    public Result<S> mapError(Function<Exception, Exception> f) {
        if (isError()) return Result.fail(f.apply(exception));
        return this;
    }

    public static <S> Result<S> from(ThrowingSupplier<S> s) {
        Objects.requireNonNull(s);
        try {
            return success(s.get());
        } catch (Exception t) {
            return fail(t);
        }
    }

    @Override
    public S get() {
        if (isError()) throw new NoSuchElementException("Attempted to retrieve value on erroneous result");
        return supplier;
    }

    public Exception getError() {
        if (!isError()) throw new NoSuchElementException("Attempted to retrieve error on non-erroneous result");
        return exception;
    }

    public boolean isSuccess() {
        return supplier != null;
    }

    public boolean isError() {
        return exception != null;
    }
}

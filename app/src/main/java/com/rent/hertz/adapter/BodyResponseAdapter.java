package com.rent.hertz.adapter;


import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;

public final class BodyResponseAdapter<R> implements Future<R> {

    private Call<R> call;
    private boolean completed = false;
    private R value;

    public BodyResponseAdapter(Call<R> call) {
        this.call = call;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return call == null || call.isCanceled();
    }

    @Override
    public boolean isCancelled() {
        return call == null || call.isCanceled();
    }

    @Override
    public boolean isDone() {
        return completed;
    }

    @Override
    public R get() {
        if (isCancelled()) {
            throw new CancellationException();
        } else {
            return value;
        }
    }

    @Override
    public R get(long timeout, TimeUnit unit) {
        return get();
    }
}

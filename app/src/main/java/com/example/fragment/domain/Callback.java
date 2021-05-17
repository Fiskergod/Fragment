package com.example.fragment.domain;

public interface Callback<T> {

    void onSuccess(T value);

    void onError(Throwable error);
}

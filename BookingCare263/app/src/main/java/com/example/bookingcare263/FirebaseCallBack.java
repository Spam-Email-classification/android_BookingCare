package com.example.bookingcare263;

public interface FirebaseCallBack <T> {
    void onSuccess(T data);
    void onFailed(String message);

}

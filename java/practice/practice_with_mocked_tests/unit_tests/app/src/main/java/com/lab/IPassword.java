package com.lab;

public interface IPassword {
    boolean isPasswordSame(IPassword other);
    int getPasswordHash();
}

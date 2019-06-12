package com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions;


public class NicknameNotValidException extends RuntimeException {
    private final String nickname;

    public NicknameNotValidException(String nickname) {
        super("Nickname already exists!: " + nickname);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}

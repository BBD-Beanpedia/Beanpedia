package net.ryan.old.model;

public record GithubCodeAuthResponse(String deviceCode, int expiresIn, int interval, String userCode,
                                     String verificationUri) {
}

package net.ryan.bean;

public record GithubAuthDeviceModel(String deviceCode, String userCode, String verificationUri, int expiresIn,
                                    int interval) {
}
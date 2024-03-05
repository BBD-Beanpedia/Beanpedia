package net.ryan.model;

public record GithubCodeAuthResponse(String deviceCode, int expiresIn, int interval, String userCode,
                                     String verificationUri) implements JsonDeserializable<GithubCodeAuthResponse>, JsonSerializable<GithubCodeAuthResponse> {
    @Override
    public GithubCodeAuthResponse deserialize(String jsonString) {
        return null;
    }
}

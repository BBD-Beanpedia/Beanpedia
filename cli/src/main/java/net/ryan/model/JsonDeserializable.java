package net.ryan.model;

public interface JsonDeserializable<T> {
    T deserialize(String jsonString);
}

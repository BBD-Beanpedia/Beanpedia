package net.ryan.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HttpHandlerTest {

  @Test
  public void testNewGetRequestSuccess() {
    String testUrl = "https://example.com";
    var result = HttpHandler.newGetRequest(testUrl);
    assertFalse(result.isError());
    assertNotNull(result);
  }

  @Test
  public void testNewPostRequestSuccess() {
    String testUrl = "https://example.com";
    var result = HttpHandler.newPostRequest(testUrl);
    assertFalse(result.isError());
    assertNotNull(result);
  }

}

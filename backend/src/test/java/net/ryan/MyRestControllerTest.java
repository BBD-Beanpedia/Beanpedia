package net.ryan;

import net.ryan.config.SecurityConfig;
import net.ryan.controller.AuthController;
import net.ryan.controller.GreeterController;
import net.ryan.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({GreeterController.class, AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class MyRestControllerTest {


    @Autowired
    MockMvc mvc;

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/greeting")).andExpect(status().isUnauthorized());
    }

    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
//        The githubToken should not really be accessible for version control but it is just public available data so not 'That Bad'
        MvcResult result = this.mvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"githubToken\": \"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\"}"))
                .andExpect(status().isOk())
                .andReturn();
        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/greeting").header("Authorization", "Bearer " + token)).andExpect(content().string("Ryan-Blignaut"));
    }

    @Test
    @WithMockUser
    public void rootWithMockUserStatusIsOK() throws Exception {
        this.mvc.perform(get("/greeting")).andExpect(status().isOk());
    }

}
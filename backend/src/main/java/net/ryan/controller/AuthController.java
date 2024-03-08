package net.ryan.controller;

import net.ryan.model.GithubAuthToken;
import net.ryan.model.GithubDataResp;
import net.ryan.service.TokenService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // To integrate with GitHub as a 'social' login we will use the access token GitHub gave us to get the users details and wrap those in our jwt.
    private GithubDataResp requestGithubData(String githubToken) {
        String githubApiUrl = "https://api.github.com/user";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<GithubDataResp> response = restTemplate.exchange(githubApiUrl, HttpMethod.GET, entity, GithubDataResp.class);
        return response.getBody();
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody GithubAuthToken githubAuthToken) {
        final GithubDataResp githubData = requestGithubData(githubAuthToken.githubToken());
        if (githubData != null) {
            final String generatedToken = tokenService.generateToken(githubData);
            return ResponseEntity.ok(generatedToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid GitHub token");
        }
    }
}

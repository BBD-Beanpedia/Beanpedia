package net.ryan;

import net.ryan.model.GithubCodeAuthResponse;
import net.ryan.model.GithubPollAuthResponse;
import net.ryan.util.HttpHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        GithubCodeAuthResponse githubCodeAuthResponse = HttpHandler.newPostRequest("https://github.com/login/device/code").get().bodyJson("""
                { "client_id": "a2a0895678d18622ca6d" }
                """).sendJson();

        System.out.println(githubCodeAuthResponse.verificationUri());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(() -> {
            executorService.shutdown();
            System.out.println("Server polling stopped after " + githubCodeAuthResponse.expiresIn() + " minutes.");
        }, githubCodeAuthResponse.expiresIn(), TimeUnit.MINUTES);

        executorService.scheduleAtFixedRate(() -> {
            GithubPollAuthResponse githubCodeAuthResponse1 = HttpHandler.newPostRequest("https://github.com/login/device/code").get().bodyJson("""
                    { "client_id": "a2a0895678d18622ca6d" }
                    """).sendJson();
        }, 0, githubCodeAuthResponse.interval(), TimeUnit.SECONDS);

    }
}
package net.ryan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        final CliOptionHelper options = CliOptionHelper.register();
        options.show();
        System.out.println("""
                Welcome to the Bean Encyclopedia CLI!
                                
                Choose an option:
                1. View All Beans
                2. Search Beans
                3. Filter Beans
                4. Exit
                """);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = br.readLine();






       /* System.out.println("Hello world!");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        GithubCodeAuthResponse githubCodeAuthResponse = HttpHandler.newPostRequest("https://github.com/login/device/code").get().bodyJson("""
                { "client_id": "a2a0895678d18622ca6d" }
                """).sendJson(GithubCodeAuthResponse.class);

        System.out.println(githubCodeAuthResponse.verificationUri());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(() -> {
            executorService.shutdown();
            System.out.println("Server polling stopped after " + githubCodeAuthResponse.expiresIn() + " minutes.");
        }, githubCodeAuthResponse.expiresIn(), TimeUnit.MINUTES);

        executorService.scheduleAtFixedRate(() -> {
            GithubPollAuthResponse githubCodeAuthResponse1 = HttpHandler.newPostRequest("https://github.com/login/device/code").get().bodyJson("""
                    { "client_id": "a2a0895678d18622ca6d" }
                    """).sendJson(GithubPollAuthResponse.class);
        }, 0, githubCodeAuthResponse.interval(), TimeUnit.SECONDS);*/

    }
}
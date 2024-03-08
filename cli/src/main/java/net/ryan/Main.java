package net.ryan;

import net.ryan.util.InputUtils;
import net.ryan.util.Result;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Welcome to the Bean Encyclopedia CLI!");

        final CliOptionHelper options = CliOptionHelper.register();
        options.show();
//        Result<Double> doubleResult = InputUtils.getInstance().readDoubleFromConsole();
//        doubleResult
//                .ifSuccess(System.out::println)
//                .ifError(Throwable::printStackTrace);

/*        InputUtils.getInstance().readIntRangeFromConsole(0, 15)
                .ifSuccess(System.out::println)
                .ifError(Throwable::printStackTrace);*/



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
package net.ryan;

public class Main {
    public static void main(String[] args) {


        System.out.println("Welcome to the Bean Encyclopedia CLI!");
        CliOptionHelper.getInstance()
                       .show();

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

    private static void welcomeUser(String string) {

    }
}
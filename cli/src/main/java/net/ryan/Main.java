package net.ryan;

public class Main {
    public static void main(String[] args) {

       /* BeanDataHandler.getInstance()
                       .requestListBeans()
                       .ifSuccess(beanModels -> beanModels.forEach(model -> System.out.println(model.toString())));

        BeanDataHandler.getInstance()
                       .readAuthFromFile();
        BeanDataHandler.getInstance()
                       .getWelcome()
                       .ifError(e -> System.out.println("Error: " + e.getMessage()))
                       .ifSuccess(Main::welcomeUser);


        HttpHandler.newGetRequest("http://localhost:8080/greeting")
                   .mapToNew(HttpHandler.Request::sendString)
                   .ifError(e ->
                   {
                       System.out.printf("Unable to make call %s, %s\n", e.getMessage(), e.getCause());
                   })
                   .ifSuccess(System.out::println);
*/

        System.out.println("Welcome to the Bean Encyclopedia CLI!\n");
        CliOptionHelper.getInstance()
                       .show();

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

    private static void welcomeUser(String string) {

    }
}
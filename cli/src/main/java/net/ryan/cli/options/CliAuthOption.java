package net.ryan.cli.options;

import net.ryan.bean.GithubAuthDeviceModel;
import net.ryan.util.BeanDataHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CliAuthOption implements CliOption {
    @Override
    public String getName() {

        return "Login";
    }

    @Override
    public void run() {
        BeanDataHandler.getInstance()
                       .getGithubAuth()
                       .ifError(e -> System.out.println("Error: " + e.getMessage()))
                       .ifSuccess(this::navigateToGithub);

    }

    private void navigateToGithub(GithubAuthDeviceModel githubAuthDeviceModel) {
        System.out.printf("Please Navigate to %s and enter this code %s\n", githubAuthDeviceModel.verificationUri(), githubAuthDeviceModel.userCode());
        try(ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1))
        {

            final Runnable pollForToken = () -> BeanDataHandler.getInstance()
                                                               .getGithubPoll(githubAuthDeviceModel.deviceCode())
                                                               .ifSuccess(token -> pollForTokenCheck(token, executorService));
            executorService.scheduleAtFixedRate(pollForToken, 0, githubAuthDeviceModel.interval() + 1, TimeUnit.SECONDS);
        }
    }

    private static void pollForTokenCheck(String token, ScheduledExecutorService executorService) {
        BeanDataHandler.getInstance()
                       .requestAndSaveToken(token)
        ;
        executorService.shutdownNow();

        //TODO: Navigate to home screen again
    }


}

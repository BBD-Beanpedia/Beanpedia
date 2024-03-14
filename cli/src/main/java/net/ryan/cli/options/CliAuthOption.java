package net.ryan.cli.options;

import net.ryan.CliOptionHelper;
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
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        {

            final Runnable pollForToken = () -> BeanDataHandler.getInstance()
                                                               .getGithubPoll(githubAuthDeviceModel.deviceCode())
                                                               .ifSuccess(token -> {
                                                                   System.out.println("Successfully got access token");
                                                                   BeanDataHandler.getInstance()
                                                                                  .setAuthToken(token);
                                                                   executorService.shutdownNow();
                                                                   CliOptionHelper.getInstance()
                                                                                  .show();
                                                               });
            executorService.scheduleAtFixedRate(pollForToken, 0, githubAuthDeviceModel.interval() + 1, TimeUnit.SECONDS);
        }
    }


}

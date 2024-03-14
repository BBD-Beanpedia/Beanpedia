package net.ryan;

import net.ryan.bean.GithubAuthDeviceModel;
import net.ryan.util.BeanDataHandler;
import net.ryan.util.Result;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Bean Encyclopedia CLI!\n");
        System.out.println("Please login to continue.");
        BeanDataHandler.getInstance()
                       .getGithubAuth()
                       .ifError(e -> System.out.println("Error: " + e.getMessage()))
                       .ifSuccess(Main::navigateToGithub);
    }

    private static void navigateToGithub(GithubAuthDeviceModel githubAuthDeviceModel) {
        System.out.printf("Please Navigate to %s and enter this code %s\n", githubAuthDeviceModel.verificationUri(), githubAuthDeviceModel.userCode());

        long intervalSeconds = githubAuthDeviceModel.interval() + 1;
        // Should actually count down the 300 seconds but hey.
        while (true) {
            try {
                Thread.sleep(intervalSeconds * 1000);
                Result<String> token = BeanDataHandler.getInstance()
                                                      .getGithubPoll(githubAuthDeviceModel.deviceCode());
                if (token.isSuccess()) {
                    System.out.println("Successfully got access token");
                    BeanDataHandler.getInstance()
                                   .setAuthToken(token.get());
                    CliOptionHelper.getInstance()
                                   .show();
                    break; // Exit the loop since token is received
                }
            } catch (InterruptedException e) {
                Thread.currentThread()
                      .interrupt();
                System.err.println("Thread interrupted while sleeping: " + e.getMessage());
            }
        }
    }
}
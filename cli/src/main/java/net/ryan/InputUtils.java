package net.ryan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Scanner;

public class InputUtils {
    private final Scanner scanner;

    private InputUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputUtils getInstance() {
        return new InputUtils(new Scanner(System.in));
    }

    private int getIntFromScannerSafe(Runnable displayRunnable) {
     /*   try {
            displayRunnable.run();
//            final int selection = this.scanner.nextInt()

//            BufferedReader bufferedReader = new BufferedReader();
        }*/
        return -1;
    }

}

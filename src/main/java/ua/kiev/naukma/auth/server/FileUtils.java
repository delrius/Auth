package ua.kiev.naukma.auth.server;

import ua.kiev.naukma.auth.shared.ConstantResults;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

public class FileUtils {
    private static final String pathToPasswords = "passwords.properties";
    private static final ConcurrentHashMap<String, String> passwords = new ConcurrentHashMap<String, String>();
    private static final String separator = "===";

    static {
        checkIfExists();
    }

    public static String login(String name, String password) {
        String pass = passwords.get(name);
        if (pass == null) {
            return ConstantResults.noSuchUser;
        } else if (pass.equals(password)) {
            return ConstantResults.logined;
        } else {
            return ConstantResults.passwordIncorrect;
        }
    }

    public static void checkIfExists() {
        Path path = FileSystems.getDefault().getPath(".", pathToPasswords);

        final boolean exists = Files.exists(path);

        if (!exists) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (passwords.isEmpty()) {
            readFromFile();
        }
    }

    public static String register(String name, String password) {

        Path path = FileSystems.getDefault().getPath(".", pathToPasswords);

        if (passwords.putIfAbsent(name, password) == null) {
            try {
                String content = name + separator + password + "\n";
                Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ConstantResults.registered;
        } else {
            return ConstantResults.alreadyRegistered;
        }
    }

    private static void readFromFile() {
        Path path = FileSystems.getDefault().getPath(".", pathToPasswords);
        try {
            BufferedReader reader =
                    Files.newBufferedReader(
                            path,
                            Charset.defaultCharset());

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] c = line.split(separator);
                if (c != null && c.length == 2) {
                    passwords.put(c[0], c[1]);
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}

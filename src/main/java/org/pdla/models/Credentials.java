package org.pdla.models;

public class Credentials {
    // Hardcoded credentials
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    public String getUrl() {
        return dbUrl;
    }

    public static void SetTestCredentials(){
        dbUrl = System.getProperty("db.url");
        dbUser = System.getProperty("db.user");
        dbPassword = System.getProperty("db.password");

        System.out.println("Using DB URL: " + dbUrl);
    }

    public String getUsername() {
        return dbUser;
    }

    public String getPassword() {
        return dbPassword;
    }
}
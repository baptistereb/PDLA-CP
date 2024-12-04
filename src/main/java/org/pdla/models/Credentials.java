package org.pdla.models;

public class Credentials {
    // Hardcoded credentials
    private static String dbUrl = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_033";
    private static String dbUser = "projet_gei_033";
    private static String dbPassword = "vo6Ahc4d";
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
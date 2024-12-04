package org.pdla.models;

public class Credentials {
    // Hardcoded credentials
    private static String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_033";
    private static String username = "projet_gei_033";
    private static String password = "vo6Ahc4d";

    public String getUrl() {
        return url;
    }

    public static void SetTestCredentials(){
        url = "jdbc:mysql://localhost:3306/test_database";
        username = "root";
        password = "root";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
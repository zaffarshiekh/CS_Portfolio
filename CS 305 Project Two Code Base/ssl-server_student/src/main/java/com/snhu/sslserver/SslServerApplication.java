package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {
    public static void main(String[] args) {
	    SpringApplication.run(SslServerApplication.class, args);
	}
}

@RestController
class ServerController {

    @RequestMapping("/hash")
    public String myHash() {
        String data = "Hello Zaffar Shiekh!";
        try {
            String checkSum = getSHA(data);
            return "<p>data: " + data + " : SHA-256 " + checkSum + "</p>";
        } catch (NoSuchAlgorithmException e) {
            // Log the exception and handle error scenario
            return "Error generating SHA-256 checksum";
        }
    }

    public static String getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}

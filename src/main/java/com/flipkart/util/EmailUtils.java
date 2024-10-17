package com.flipkart.util;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

    // Method to fetch OTP from email
    public static String fetchOtpFromEmail(String host, String storeType, String user, String password) {
        try {
            // Setup properties for connection
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.ssl.enable", "true");
            properties.put("mail.imap.connectiontimeout", "5000"); // Timeout settings
            properties.put("mail.imap.timeout", "5000");
            properties.put("mail.imap.writetimeout", "5000");

            // Get the session object
            Session session = Session.getInstance(properties);

            // Connect to the email store
            Store store = session.getStore("imaps");
            store.connect(host, user, password);

            // Get the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for unread messages (you can add custom logic here)
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                String subject = message.getSubject();
                if (subject.contains("Your OTP")) { // Example OTP email subject filter
                    String content = message.getContent().toString();
                    // Extract OTP using regex or any custom logic based on your email format
                    String otp = extractOtp(content);
                    return otp;
                }
            }
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to extract OTP using regex
    private static String extractOtp(String content) {
        // Define the pattern for OTP (e.g., 6 digits)
        Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = otpPattern.matcher(content);

        // If OTP is found, return it
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    // Helper method to get text from message
    private static String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    // Helper method to extract text from multipart message
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append(org.jsoup.Jsoup.parse(html).text()); // Use Jsoup to parse HTML
            }
        }
        return result.toString();
    }
}

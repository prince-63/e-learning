package com.e_learning.notification_service.services;

/**
 * Service interface for managing emails within the application.
 */
public interface EmailService {

    /**
     * Send email to the user
     * @param to - the email of destination
     * @param subject - the subject of the email
     * @param body - body of the email, contain html language
     */
    void sendEmail(String to, String subject, String body);
}

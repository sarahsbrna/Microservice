package com.sarah.notificationservice.notificationservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.sarah.notificationservice.notificationservice.dto.PeminjamanNotificationDTO;

@Service
public class NotificationConsumer {

    private final EmailService emailService;

    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void handlePeminjamanNotification(PeminjamanNotificationDTO notificationDTO) {
        System.out.println("Received notification from RabbitMQ: " + notificationDTO);
        emailService.sendPeminjamanNotification(notificationDTO);
    }
}
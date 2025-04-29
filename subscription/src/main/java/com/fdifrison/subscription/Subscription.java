package com.fdifrison.subscription;

import com.fdifrison.common.subscription.dto.SubscriptionPlan;
import com.fdifrison.common.subscription.dto.SubscriptionStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private SubscriptionPlan plan;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    private Instant startDate;
    private Instant endDate;
}

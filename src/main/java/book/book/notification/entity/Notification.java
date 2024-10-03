package book.book.notification.entity;

import book.book.common.BaseEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(length = 50)
    private String notificationType;

    private String message;

    private LocalDateTime createdTime;
}
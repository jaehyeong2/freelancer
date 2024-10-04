package jjfactory.freelancer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Freelancer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String phone;
    private Long viewCount;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private boolean isExposed;
    private LocalDateTime exposedAt;

    @Builder
    public Freelancer(String lastName, String firstName, String phone, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isExposed, LocalDateTime exposedAt, Long viewCount) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isExposed = isExposed;
        this.exposedAt = exposedAt;
        this.viewCount = viewCount;
    }

    public String getKoreanName(){
        return lastName + firstName;
    }

    public void exposeProfile(){
        isExposed = true;
        exposedAt = LocalDateTime.now();
    }
}

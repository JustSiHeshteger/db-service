package ru.zvrg.dbservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime createDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updateDate;
}

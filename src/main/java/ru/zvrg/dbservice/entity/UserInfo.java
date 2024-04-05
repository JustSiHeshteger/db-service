package ru.zvrg.dbservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_info")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column
    private String secondName;

    @Column
    private String thirdName;

    @Column(nullable = false)
    private String nickName;
}

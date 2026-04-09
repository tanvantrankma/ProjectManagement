package com.vti.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Position", catalog = "TestingSystem")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "PositionID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "PositionName", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PositionName name;

    public enum PositionName {
        Dev, Test, Scrum_Master, PM
    }

    @OneToMany(mappedBy = "position")
    List<Account> accounts;

}

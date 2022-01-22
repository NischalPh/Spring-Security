package com.nischal.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Generated(GenerationTime.INSERT)
    @Column(name = "STATUS",nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;

    public BaseEntity(Long id) {
        this.id= id;
    }
}

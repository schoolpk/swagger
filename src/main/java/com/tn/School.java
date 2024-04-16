package com.tn;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="school")
    public class School {
        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)

        private Integer id;

        private String schoolName;

        private String address;

        private int numTeacher;
    }

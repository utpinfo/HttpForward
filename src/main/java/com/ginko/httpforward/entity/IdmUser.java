package com.ginko.httpforward.entity;

import javax.persistence.*;

@Entity
@Table(name = "IDM_USER", schema = "IDM")
public class IdmUser {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    //@SequenceGenerator(name = "user_seq_gen", sequenceName = "idm_user_id", allocationSize = 1)
    @Column(name = "USER_ID") // 對應欄位
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

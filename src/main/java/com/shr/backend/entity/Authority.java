package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authorities", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "authId")
public class Authority {
    private int authId;
    private String name;
    private List<User> userList;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getAuthId() { return authId; }
    public void setAuthId(int authId) { this.authId = authId; }

    @Column(nullable = false, unique = true)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @OneToMany(mappedBy = "authority")
    @JsonIgnoreProperties(value = "authority")
    public List<User> getUserList() { return userList; }
    public void setUserList(List<User> userList) { this.userList = userList; }
}

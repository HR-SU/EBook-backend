package com.shr.backend.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users", schema = "ebook")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private Boolean active;
    private Authority authority;
    private List<Order> orderList;
    private Shopcart shopcart;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    @Column(nullable = false, unique = true)
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Column(nullable = false)
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getActive() {return active; }
    public void setActive(Boolean active) { this.active = active; }

    @ManyToOne
    @JsonIgnoreProperties(value = "userList")
    public Authority getAuthority() { return authority; }
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnoreProperties(value = "user")
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user")
    public Shopcart getShopcart() { return shopcart; }
    public void setShopcart(Shopcart shopcart) {
        this.shopcart = shopcart;
    }
}

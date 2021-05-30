package com.example.hybridbooksbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_has_role", schema = "hybrid_books", catalog = "")
public class UserHasRole {
    private Long id;
    private User user;
    private Role role;

    public UserHasRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasRole that = (UserHasRole) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.hashCode(), role.hashCode());
    }

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JsonBackReference(value = "rolesUser")
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

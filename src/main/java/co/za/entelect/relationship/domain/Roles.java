package co.za.entelect.relationship.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<UserProfile> users = new HashSet<>();

    public Roles()
    {
    }

    public Roles(String role)
    {
        this.role = role;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Set<UserProfile> getUsers()
    {
        return users;
    }

    public void setUsers(Set<UserProfile> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", users=" + users +
                '}';
    }
}

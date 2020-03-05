package my.com.spring.boot.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends AuditEntity{

    @Column(unique = true)
    @NotNull(message = "Please provider a role name !")
    private String name;

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade =  {
                CascadeType.PERSIST,
                CascadeType.MERGE
        },
        mappedBy = "roles"
    )
    private Set<User> users = new HashSet<>();

    public Role() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

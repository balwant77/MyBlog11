package com.myblog.myblog11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users",uniqueConstraints={
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)//FetchType.EAGER will load all the tables into temporary memory i.e cache memory of hibernate.
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),//it will create a third table with name user_roles
   //user_id is the foreign key in 3rd table which is mapped to the primary key of the first table i.e "id"
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    //inverseJoinColumns will join child table to the third table i.e roles table to user_roles
    //In a many-to-many relationship, you typically have two sides: the owning side and the inverse side. The owning side is the side that is responsible for
    // managing the association and updating the join table, while the inverse side is the side that doesn't manage the association.
    //the use of inverseJoinColumns is about specifying the details of the foreign key relationship in the join table from the perspective of the inverse side
    // (in this case, the Role entity). It helps define how the Role entities are linked in the many-to-many relationship.
    private Set<Role> roles;


}

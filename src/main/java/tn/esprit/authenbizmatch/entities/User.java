package tn.esprit.authenbizmatch.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role ;

    private String email;
    @Transient
    private String confirmpassword;
}

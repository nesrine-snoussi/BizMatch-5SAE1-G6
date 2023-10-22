package tn.esprit.planstrategicmicroservice.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanStrategique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vision ;
    private String mission ;
    private String valeurs ;
    private Date date;



    // Relation avec l'entreprise (chaque objectif appartient à une entreprise)
    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "planStrategique", cascade = CascadeType.ALL)
    private List<ObjectifStra> objectifStras;


    public PlanStrategique(List<ObjectifStra> objectifStras) {
        this.objectifStras = objectifStras;
    }
}


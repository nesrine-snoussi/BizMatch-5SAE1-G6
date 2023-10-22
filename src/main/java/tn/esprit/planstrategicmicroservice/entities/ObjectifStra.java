package tn.esprit.planstrategicmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ObjectifStra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String objectif;
    private int pourcentageImportance;

    @ManyToOne
    @JoinColumn(name = "plan_strategique_id")
    @JsonIgnore
    private PlanStrategique planStrategique;
}

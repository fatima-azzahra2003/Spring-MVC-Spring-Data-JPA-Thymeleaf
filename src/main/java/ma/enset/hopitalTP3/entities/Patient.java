package ma.enset.hopitalTP3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Le nom ne doit pas être vide")
    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères")
    private String nom;
    private String prenom;
    @NotNull(message = "La date de naissance est obligatoire")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;


}

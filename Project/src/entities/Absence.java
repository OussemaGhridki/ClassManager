package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ) // ycalculi bel kaaba bel kaaba hata ken yetfasakh yaawd min kas
    private int idAbsence ;
    private String date ;
    private String motif ;
    
    private String justification ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abs_session") // jointure de columns
    private Session session ;
    @ManyToOne
    @JoinColumn(name="student_abs")
    private Student student;
}

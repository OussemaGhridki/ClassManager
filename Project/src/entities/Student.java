package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int idStudent ;
    private  String nameStudent ;
    private  String familyName  ;
    private String birthday ;
    private  String photo ; // url
    private  StateStudent state ;
    private Situation situation ;
    @OneToMany(mappedBy="student")// each student may have many absences
    private List<Absence> absences ;// list of absence

    @ManyToOne
    @JoinColumn(name="student_group")
    private Group group ;

}

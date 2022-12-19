package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString //elle renvoie le nom de la classe de l'objet concerné suivi de l'adresse de cet objet
@Entity // bch nekhdmou beha fel base de donnee , bch twali tab fel base de dd
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  int idGroup ;
    private String nameGroup  ;
    private int nbStudents ;
    private  String emailGroup ;
    private  String levelStudy ;
    @OneToMany(mappedBy="student")
    private Set<Student> students ;

    @ManyToMany
    @JoinTable(
            name = "modules_group",
            joinColumns = @JoinColumn(name = "idGroup"))
    private List<Module> modules ;

}

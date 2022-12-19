package entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )//  to automatically generate the primary key value
    private  int idTeacher  ;
    private String nameTeacher ;
    private String familyName ;
    private String personalEmail ;
    private String workEmail ;
    private String photoTeacher ; //url
    private int dueTeacher ; //total hours to teach by week
    @ManyToMany
    @JoinTable(
            name = "modules_taught",
            joinColumns = @JoinColumn(name = "idTeacher"))
    private List<Module> modules;
    // les fonctions



}

package entities;

import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private  int idModule ;
    private String name ;
    private int due ; // nbr of hours
    private  TypeModule type ;
    private String studyLevel  ;
    @ManyToMany(mappedBy="modules")
    private Set<Teacher> teachers ;
    private Teacher responsable ;
    @ManyToMany(mappedBy="modules")
    private List<Group> groups ;

}

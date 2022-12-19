package entities;

import lombok.*;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int idSession ;
    private String startTime ;
    private  String endTime ;
    private int claasroomNbr ;
    private String goal ;
    private String summary ;
    private StateSession state;
    private Module module ;
    private Type type ;
    private List<Tools> toolsList;

    private Boolean online ;
    @OneToOne(mappedBy = "session")
    private Absence absence;

}

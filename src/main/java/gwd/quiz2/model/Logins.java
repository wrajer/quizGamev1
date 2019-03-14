package gwd.quiz2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Logins {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;
    private String password;


}

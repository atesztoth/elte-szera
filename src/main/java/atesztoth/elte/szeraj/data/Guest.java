package atesztoth.elte.szeraj.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="guests")
public class Guest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



}
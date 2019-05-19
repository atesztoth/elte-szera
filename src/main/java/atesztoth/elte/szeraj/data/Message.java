package atesztoth.elte.szeraj.data;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "friends")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    private Date sent;

    @Nullable
    private String attachedPhoneNumber;

    @Nullable
    private Date delivered;

    private Person sender;

    public Message() { }
}
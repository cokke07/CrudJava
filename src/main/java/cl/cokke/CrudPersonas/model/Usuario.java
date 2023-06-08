package cl.cokke.CrudPersonas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    @Email(message = "El correo ya se encuentra registrado")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula y un dígito.")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefono> phones;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime created;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime modified;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime last_login;
    private String token;
    private boolean isActive;

}

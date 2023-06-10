package cl.cokke.CrudPersonas.dto;

import cl.cokke.CrudPersonas.model.Telefono;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioCreatedDTO {
    private Long id;
    private String name;
    private String email;
    private List<TelefonoDTO> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    private boolean isActive;
}

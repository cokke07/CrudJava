package cl.cokke.CrudPersonas.dto;

import cl.cokke.CrudPersonas.model.Telefono;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreatedDTO {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private boolean isActive;
}

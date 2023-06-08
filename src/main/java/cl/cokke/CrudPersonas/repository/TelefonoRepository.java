package cl.cokke.CrudPersonas.repository;

import cl.cokke.CrudPersonas.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}

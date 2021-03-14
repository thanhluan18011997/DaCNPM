package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.unigo.model.Morals;

public interface MoralRepository extends JpaRepository<Morals,Long> {
}

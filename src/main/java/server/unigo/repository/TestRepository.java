package server.unigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.unigo.model.Morals;
import server.unigo.model.Tests;

public interface TestRepository extends JpaRepository<Tests,Long> {
}

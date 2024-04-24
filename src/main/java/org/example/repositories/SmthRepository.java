package org.example.repositories;

import org.example.models.Smth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmthRepository extends JpaRepository<Smth, Long> {
}

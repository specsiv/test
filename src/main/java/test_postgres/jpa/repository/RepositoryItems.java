package test_postgres.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test_postgres.jpa.entity.Items;

@Repository
public interface RepositoryItems extends JpaRepository<Items, Integer> {
    @Query(value = "select setval('itemsseq', 0)", nativeQuery = true)
    void resetSequence();
    boolean existsByName(String name);
    boolean existsById(int id);
    Items getByName(String name);
}
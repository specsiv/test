package test_postgres.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test_postgres.jpa.entity.Orders;

@Repository
public interface RepositoryOrder extends JpaRepository<Orders, Integer> {
    @Query(value = "select setval('orderseq', 0)", nativeQuery = true)
    void resetSequence();
    boolean existsById(int id);
}
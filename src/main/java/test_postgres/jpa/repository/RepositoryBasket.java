package test_postgres.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test_postgres.jpa.entity.Basket;

@Repository
public interface RepositoryBasket extends JpaRepository<Basket, Integer> {
    @Query(value = "select setval('basketseq', 0)", nativeQuery = true)
    void resetSequence();
    boolean existsById(int id);
}
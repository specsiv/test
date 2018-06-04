package test_postgres.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test_postgres.jpa.entity.Customer;

import java.util.List;

@Repository
public interface RepositoryCustomer extends JpaRepository<Customer, Integer> {
    @Query(value = "select vipstatus from Customer c where c.id = :id", nativeQuery = true)
    boolean vipStatusCheck(@Param("id") int id);
    @Query(value = "select setval('customerseq', 0)", nativeQuery = true)
    void resetSequence();
    boolean existsById(int id);
    boolean existsByFio(String fio);
    Customer findByFio(String fio);
}
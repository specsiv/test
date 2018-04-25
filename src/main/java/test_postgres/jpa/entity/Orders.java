package test_postgres.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq", sequenceName = "orderseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    @Basic
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Basic
    @Column(name = "customerid", nullable = false)
    private int customerId;

    public Orders(LocalDate date, int customerId) {
        this.date = date;
        this.customerId = customerId;
    }
}
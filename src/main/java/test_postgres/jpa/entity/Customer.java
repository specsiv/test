package test_postgres.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq", sequenceName = "customerseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "fio", nullable = false)
    private String fio;
    @Basic
    @Column(name = "vipstatus", nullable = false)
    private boolean vipStatus;

    public Customer(String fio, boolean vipStatus) {
        this.fio = fio;
        this.vipStatus = vipStatus;
    }
}
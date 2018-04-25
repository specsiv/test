package test_postgres.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "basket")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Basket {
    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq", sequenceName = "basketseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    @Basic
    @Column(name = "itemid", nullable = false)
    private int itemId;
    @Basic
    @Column(name = "orderid", nullable = false)
    private int orderId;

    public Basket(int itemId, int orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }
}
package shop.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Long> {

    private Integer idAccount;
    private List<OrderItem> items;
    private Timestamp created;

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Суммарная стоимость всех заказов
     * @return
     */
    public BigDecimal totalCost() {
        BigDecimal cost = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItem i : items) {
                cost = cost.add(i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getCount())));
            }
        }
        return cost;
    }
}

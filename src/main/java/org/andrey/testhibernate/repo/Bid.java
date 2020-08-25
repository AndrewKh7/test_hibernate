package org.andrey.testhibernate.repo;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bid_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String key;

    protected Bid(){}

    public Bid(String key,  Item item){
        if ( item == null) throw new IllegalArgumentException("Item cannot be null in the bid constructor");
        this.item = item;
        this.key = key;
        this.item.addBid(key,this);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Bid:"+ id;
    }
}

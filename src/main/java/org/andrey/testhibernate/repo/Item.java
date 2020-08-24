package org.andrey.testhibernate.repo;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    Integer id;

    @OneToMany(mappedBy = "item")
    @MapKeyColumn(name = "key")
    protected Map<String,Bid> bids = new HashMap<>();

    public Map<String, Bid> getBids() {
        return bids;
    }

    public void setBids(Map<String, Bid> bids) {
        this.bids = bids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addBid(String key, Bid bid){
        this.bids.put(key,bid);
    }
    public String toString(){
        return "Item: id="+this.id +", Bids="+bids;
    }
}

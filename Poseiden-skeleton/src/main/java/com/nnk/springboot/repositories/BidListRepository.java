package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends CrudRepository<Bid, Integer> {

}

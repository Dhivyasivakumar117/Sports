package com.cg.oss.repopsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oss.entities.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long>{

}

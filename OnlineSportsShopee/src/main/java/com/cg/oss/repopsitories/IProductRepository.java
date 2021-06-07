package com.cg.oss.repopsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.oss.entities.Product;


@Repository
public interface IProductRepository extends JpaRepository<Product,Long>{

	public List<Product> findByProductName(String productName);
	
	public List<Product> findByProductSize(String sizevar);
	
	public List<Product> findByMrp(double price);
	
	public List<Product> findByColor(String color);
}

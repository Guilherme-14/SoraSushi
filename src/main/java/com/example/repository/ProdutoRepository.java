package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{

}

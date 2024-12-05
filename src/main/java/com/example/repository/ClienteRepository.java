package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}

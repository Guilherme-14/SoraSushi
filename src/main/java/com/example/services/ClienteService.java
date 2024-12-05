package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Cliente;
import com.example.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvarCliente (Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	public Optional<Cliente> buscarPorId(Long id){
		return clienteRepository.findById(id);
	}
	
	public Cliente atualizarCliente(Cliente cliente) {
		if (clienteRepository.existsById(cliente.getId())) {
			return clienteRepository.save(cliente);
		} else {
			throw new RuntimeException("Hóspede não encontrado");
		}
	}
	
	public void deletarCliente(Long id) {
		clienteRepository.deleteById(id);
	}
}

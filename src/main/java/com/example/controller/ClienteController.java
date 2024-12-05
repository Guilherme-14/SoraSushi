package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Cliente;
import com.example.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	private final ClienteService clienteService;

	@Autowired
	public ClienteController (ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
		Cliente novoCliente = clienteService.salvarCliente(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos(){
		List<Cliente> clientes = clienteService.listarTodos();
		return new ResponseEntity<>(clientes,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
		Optional <Cliente> cliente = clienteService.buscarPorId(id);
		return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente (@PathVariable Long id, @RequestBody Cliente cliente){
		if(!clienteService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		cliente.setId(id);
		Cliente clienteAtualizado = clienteService.atualizarCliente(cliente);
		return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletaeCliente(@PathVariable Long id){
		if(!clienteService.buscarPorId(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteService.deletarCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

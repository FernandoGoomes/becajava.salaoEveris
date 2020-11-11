package br.salaoeveris.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Cliente;
import br.salaoeveris.app.repository.ClienteRepository;
import br.salaoeveris.app.request.ClienteRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ClienteListResponse;
import br.salaoeveris.app.response.ClienteResponse;


@Service
public class ClienteService {

	final ClienteRepository _clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		_clienteRepository = clienteRepository;
	}

	// POST - CRIAR CLIENTE

	public BaseResponse inserir(ClienteRequest clienteRequest) {
		Cliente cliente = new Cliente();
		ClienteResponse clienteResponse = new ClienteResponse();
		clienteResponse.statusCode = 400;

		if (clienteRequest.getNome() == "" || clienteRequest.getNome() == null) {
			clienteResponse.message = "Nome não foi preenchido";
			return clienteResponse;
		}

		if (clienteRequest.getCpf() == "" || clienteRequest.getCpf() == null) {
			clienteResponse.message = "CPF não foi preenchido";
			return clienteResponse;
		}
		if (clienteRequest.getEndereco() == "" || clienteRequest.getEndereco() == null) {
			clienteResponse.message = "Endereço não foi preenchido";
			return clienteResponse;
		}

		if (clienteRequest.getTelefone() == "" || clienteRequest.getTelefone() == null) {
			clienteResponse.message = "Telefone não foi preenchido";
			return clienteResponse;
		}

		cliente.setNome(clienteRequest.getNome());
		cliente.setCpf(clienteRequest.getCpf());
		cliente.setEndereco(clienteRequest.getEndereco());
		cliente.setTelefone(clienteRequest.getTelefone());

		_clienteRepository.save(cliente);

		clienteResponse.statusCode = 201;
		clienteResponse.message = "Cliente cadastrado com sucesso.";
		clienteResponse.setNome(cliente.getNome());
		clienteResponse.setTelefone(cliente.getTelefone());
		clienteResponse.setEndereco(cliente.getEndereco());
		clienteResponse.setId(cliente.getId());

		return clienteResponse;
	}

	// GET - OBTER
	public ClienteResponse obter(Long id) {
		Optional<Cliente> cliente = _clienteRepository.findById(id);		
	    ClienteResponse response = new ClienteResponse();
	    
	    if (cliente.isEmpty()) {
	    	response.statusCode = 400;
	    	response.message = "ID não encontrado";
	    	return response;	    	
	    }
	    
	    response.setId(cliente.get().getId());
	    response.setNome(cliente.get().getNome());
	    response.setEndereco(cliente.get().getEndereco());
	    response.setTelefone(cliente.get().getTelefone());
	    response.statusCode = 200;
	    response.message = "Cliente obtido com sucesso";
	    return response;
	    
	}
	
	// GET OBTER TUDO
	
	public ClienteListResponse listar() {
		
		List<Cliente> lista = _clienteRepository.findAll();		
		List<ClienteResponse> listaResponse = new ArrayList<ClienteResponse>();
		ClienteListResponse response = new ClienteListResponse();
		
		for (Cliente cliente : lista) {
			ClienteResponse clienteResponseList = new ClienteResponse();
			
			clienteResponseList.setId(cliente.getId());
			clienteResponseList.setNome(cliente.getNome());
			clienteResponseList.setTelefone(cliente.getTelefone());
			clienteResponseList.setEndereco(cliente.getEndereco());
			
			listaResponse.add(clienteResponseList);
		}
		

		response.setClientes(listaResponse);
		response.statusCode = 200;
		response.message = "Clientes obtido com sucesso";
		
		return response;
		
	}
	
	

}

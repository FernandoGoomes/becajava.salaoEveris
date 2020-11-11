package br.salaoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Servico;
import br.salaoeveris.app.repository.ServicoRepository;
import br.salaoeveris.app.request.ServicoRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ListaServicoResponse;
import br.salaoeveris.app.response.ServicoResponse;

@Service
public class ServicoService {
	
	// PROPRIEDADES
	final ServicoRepository _servicoRepository;
	
	// CONSTRUTOR
	public ServicoService(ServicoRepository servicoRepository) {
		_servicoRepository = servicoRepository;
	}
	
	// INSERIR SERViÇO
	
	public BaseResponse inserir(ServicoRequest servicoRequest) {
		Servico servico = new Servico();
		BaseResponse response = new BaseResponse();
		response.statusCode = 400;
		
		if (servicoRequest.getTipo().isEmpty() || servicoRequest.getTipo() == "") {
			response.message = "Nome de serviço não foi inserido";
			return response;
			}
		if (servicoRequest.getValor() == null || servicoRequest.getValor() == 0) {
			response.message ="Valor de serviço não informado";
			return response;
		}
		
		servico.setTipo(servicoRequest.getTipo());
		servico.setValor(servicoRequest.getValor());
		
		_servicoRepository.save(servico);
		response.statusCode = 201;
		response.message = "Serviço inserido com sucesso";
		return response;
	}
	
	
	// OBTER UM SERVIÇO
	
	public ServicoResponse obter(Long id) {
		Optional<Servico> servico = _servicoRepository.findById(id);
		
		ServicoResponse response = new ServicoResponse();
		
		if (servico.isEmpty()) {
			response.statusCode = 400;
			response.message = "Serviço não encontrado";
			return response;
		}
		
		response.statusCode = 200;
		response.message = "Serviço encontrado";
		response.setTipo(servico.get().getTipo());
		response.setValor(servico.get().getValor());
		return response;
	}
	
	// LISTAR TODOS SERVIÇOS
	
	public ListaServicoResponse listar() {
		List<Servico> lista = _servicoRepository.findAll();
		
		ListaServicoResponse response = new ListaServicoResponse();
		
		response.setServicos(lista);
		response.statusCode = 200;
		response.message = "Serviços obtidos com sucesso";
		
		return response;
	}
	
}

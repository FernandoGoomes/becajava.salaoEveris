package br.salaoeveris.app.service;

import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Agendamento;
import br.salaoeveris.app.model.Cliente;
import br.salaoeveris.app.model.Servico;
import br.salaoeveris.app.repository.AgendamentoRepository;
import br.salaoeveris.app.request.AgendamentoRequest;
import br.salaoeveris.app.response.BaseResponse;

@Service
public class AgendamentoService {

	final AgendamentoRepository _agendamentoRepository;
	
	public AgendamentoService(AgendamentoRepository agendamentoRepository) {
		_agendamentoRepository = agendamentoRepository;
	}
	
	// INSERIR AGENDAMENTO
	
	public BaseResponse inserir(AgendamentoRequest agendamentoRequest) {
		BaseResponse response = new BaseResponse();
		Agendamento agendamento = new Agendamento();
		response.statusCode = 400;
		
		if (agendamentoRequest.getData() == null) {
			response.message = "Data do agendamento não inserida";
			return response;
		}
		if (agendamentoRequest.getClienteId() == null || agendamentoRequest.getClienteId() == 0) {
			response.message = "Cliente não inserido";
			return response;
		}
		if (agendamentoRequest.getServicoId() == null || agendamentoRequest.getServicoId() == 0) {
			response.message = "Serviço não inserido";
			return response;
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(agendamentoRequest.getClienteId());
		agendamento.setCliente(cliente);
		
		Servico servico = new Servico();
		servico.setId(agendamentoRequest.getServicoId());
		agendamento.setServico(servico);
		
		agendamento.setData(agendamentoRequest.getData());
		
		_agendamentoRepository.save(agendamento);
		response.statusCode = 201;
		response.message = "Agendamento Realizado";
		
		return response;
	}
}

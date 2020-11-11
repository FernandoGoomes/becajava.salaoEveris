package br.salaoeveris.app.response;

import java.time.LocalDateTime;

public class AgendamentoResponse extends BaseResponse {
	
	private Long id;
	private Long clienteId;
	private Long servicoId;
	private LocalDateTime data;
	private Double valor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getServicoId() {
		return servicoId;
	}
	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	
}

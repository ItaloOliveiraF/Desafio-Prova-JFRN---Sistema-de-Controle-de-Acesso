package acessojfrn;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.lang.String;


//Essa classe é responsável por guardar e manipular todos os acessos ao prédio da Justiça Federal.
public class ControleAcesso {
	private LinkedList<String> listaFuncionarios; //Lista dos funcionários existentes
	private LinkedList<Acesso> listaFuncionariosNoPredio; //Lista dos funcionários que estão dentro do prédio  
	private LinkedList<Acesso> listaVisitantesNoPredio;//Lista dos visitantes que estão dentro do prédio
	private LinkedList<Acesso> historicoAcessos; //Lista com histórico do registro de acessos
	
	
	//Esse método verifica se alguém que está tentando entrar no prédio é funcinário ou visitante.
	public boolean verificaSeEhFuncionario(String nome){
		if(this.listaFuncionarios.contains(nome)){
			return true;
		}
		return false;
	}
	
	//Esse método verifica se um dado nome de funcionário que está tentando entrar no prédio já está la dentro. Essa é uma medida de segurança
	//para barrar pessoas que quisessem se passar por outro funcionário
	public boolean verificaSeFuncionarioEstaNoPredio(String nome){
		for(int i=0; i<listaFuncionariosNoPredio.size(); i++) {
			if(listaFuncionariosNoPredio.get(i).getNome() == nome) {
				return true;
			}
		}
		return false;
	}
	
	
	//Esse método verifica se a pessoa está apta a entrar no prédio ou não. Para ela receber um sim, pode ser um funcionário que ainda não está no prédio
	// ou então um visitante caso ainda não haja 50 dentro.
	public boolean liberaEntrada(String nome, boolean ehFuncionario, boolean funcionarioNoPredio) {
	
		if(ehFuncionario) {
			if(funcionarioNoPredio) {
				return false;
			}
			return true;
		}
		
		if(listaVisitantesNoPredio.size() < 50) {
			return true;
		}
		
		return false;		
	}
	
	
	//Esse método adiciona a pessoa que entrou recentemente a lista de funcionarios ou de visitantes no prédio.
	public void RegistraAcessoEntrada(String nome, String registroHorarioEntrada, String registroDataEntrada,
			boolean ehFuncionario) {
		
		Acesso entrada =  new Acesso(nome, registroHorarioEntrada, registroDataEntrada, ehFuncionario);
		
		if (ehFuncionario) {
			this.listaFuncionariosNoPredio.add(entrada);
		}
		else {
			this.listaVisitantesNoPredio.add(entrada);
		}
	}
	
	//Esse método remove uma pessoa que está sainda das listas de funcionários ou visitantes dentro do prédio e coloca o acesso dela no histórico de registros.
	
	public boolean RegistraAcessoSaida(String nome) {
		for(int i=0; i<listaFuncionariosNoPredio.size(); i++) {
			if(listaFuncionariosNoPredio.get(i).getNome() == nome) {
				Acesso saida = listaFuncionariosNoPredio.get(i);
				listaFuncionariosNoPredio.remove(i);
				
				String registroHorarioSaida = calculaHorario();
				String registroDataSaida = calculaData();
				
				saida.setRegistroDataSaida(registroDataSaida);
				saida.setRegistroHorarioSaida(registroHorarioSaida);
				
				this.historicoAcessos.add(saida);
				return true;
			}
		}
		for(int i=0; i<listaVisitantesNoPredio.size(); i++) {
			if(listaVisitantesNoPredio.get(i).getNome() == nome) {
				Acesso saida = listaVisitantesNoPredio.get(i);
				listaVisitantesNoPredio.remove(i);
				
				String registroHorarioSaida = calculaHorario();
				String registroDataSaida = calculaData();
				
				saida.setRegistroDataSaida(registroDataSaida);
				saida.setRegistroHorarioSaida(registroHorarioSaida);
				
				this.historicoAcessos.add(saida);
				return true;
			}
		}
						
		System.out.println("Essa pessoa não se econtra no prédio");
					
		return false;
	}
	
	//Esse método calcula a quantidade de funcionários no Prédio
	public int qntdFuncionariosPredio() {
		return listaFuncionariosNoPredio.size();
	}
	
	//Esse método calcula a quantidade de visitantes no Prédio
	public int qntdVisitantesPredio() {
		return listaVisitantesNoPredio.size();
	}
	
	//Esse método calcula a quantidade de pessoas no Prédio
	public int qntdTotalPredio() {
		return listaFuncionariosNoPredio.size() + listaVisitantesNoPredio.size();
	}
	
	
	//Não implementada: método que retorna o horário atual
	public String calculaHorario() {
		return " ";
	}
	
	//Não implementada: método que retorna a data atual
	public String calculaData() {
		return " ";
	}
}

package acessojfrn;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.lang.String;


//Essa classe � respons�vel por guardar e manipular todos os acessos ao pr�dio da Justi�a Federal.
public class ControleAcesso {
	private LinkedList<String> listaFuncionarios; //Lista dos funcion�rios existentes
	private LinkedList<Acesso> listaFuncionariosNoPredio; //Lista dos funcion�rios que est�o dentro do pr�dio  
	private LinkedList<Acesso> listaVisitantesNoPredio;//Lista dos visitantes que est�o dentro do pr�dio
	private LinkedList<Acesso> historicoAcessos; //Lista com hist�rico do registro de acessos
	
	
	//Esse m�todo verifica se algu�m que est� tentando entrar no pr�dio � funcin�rio ou visitante.
	public boolean verificaSeEhFuncionario(String nome){
		if(this.listaFuncionarios.contains(nome)){
			return true;
		}
		return false;
	}
	
	//Esse m�todo verifica se um dado nome de funcion�rio que est� tentando entrar no pr�dio j� est� la dentro. Essa � uma medida de seguran�a
	//para barrar pessoas que quisessem se passar por outro funcion�rio
	public boolean verificaSeFuncionarioEstaNoPredio(String nome){
		for(int i=0; i<listaFuncionariosNoPredio.size(); i++) {
			if(listaFuncionariosNoPredio.get(i).getNome() == nome) {
				return true;
			}
		}
		return false;
	}
	
	
	//Esse m�todo verifica se a pessoa est� apta a entrar no pr�dio ou n�o. Para ela receber um sim, pode ser um funcion�rio que ainda n�o est� no pr�dio
	// ou ent�o um visitante caso ainda n�o haja 50 dentro.
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
	
	
	//Esse m�todo adiciona a pessoa que entrou recentemente a lista de funcionarios ou de visitantes no pr�dio.
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
	
	//Esse m�todo remove uma pessoa que est� sainda das listas de funcion�rios ou visitantes dentro do pr�dio e coloca o acesso dela no hist�rico de registros.
	
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
						
		System.out.println("Essa pessoa n�o se econtra no pr�dio");
					
		return false;
	}
	
	//Esse m�todo calcula a quantidade de funcion�rios no Pr�dio
	public int qntdFuncionariosPredio() {
		return listaFuncionariosNoPredio.size();
	}
	
	//Esse m�todo calcula a quantidade de visitantes no Pr�dio
	public int qntdVisitantesPredio() {
		return listaVisitantesNoPredio.size();
	}
	
	//Esse m�todo calcula a quantidade de pessoas no Pr�dio
	public int qntdTotalPredio() {
		return listaFuncionariosNoPredio.size() + listaVisitantesNoPredio.size();
	}
	
	
	//N�o implementada: m�todo que retorna o hor�rio atual
	public String calculaHorario() {
		return " ";
	}
	
	//N�o implementada: m�todo que retorna a data atual
	public String calculaData() {
		return " ";
	}
}

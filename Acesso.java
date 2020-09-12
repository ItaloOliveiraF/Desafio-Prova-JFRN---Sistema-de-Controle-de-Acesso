package acessojfrn;
import java.lang.String;


//Essa classe gaurda especificações para criar objetos do tipo acesso.
public class Acesso {
	
	private String nome;
	private String registroHorarioEntrada;
	private String registroDataEntrada;
	private String registroHorarioSaida;
	private String registroDataSaida;;
	boolean ehFuncionario; 
	
	public Acesso(String nome, String registroHorarioEntrada, String registroDataEntrada, boolean ehFuncionario) {
		this.nome=nome;
		this.registroHorarioEntrada=registroHorarioEntrada;
		this.registroDataEntrada=registroDataEntrada;
		this.ehFuncionario=ehFuncionario;
		this.registroHorarioSaida = "";
		this.registroDataSaida = "";
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getRegistroHorarioEntrada() {
		return this.registroHorarioEntrada;
	}
	
	public String getRegistroDataEntrada() {
		return this.registroDataEntrada;
	}
	
	public String getRegistroHorarioSaida() {
		return this.registroHorarioSaida;
	}
	
	public String getRegistroDataSaida() {
		return this.registroDataSaida;
	}
	
	public void setRegistroHorarioSaida( String registroHorarioSaida) {
		this.registroHorarioSaida=registroHorarioSaida;
	}
	
	public void setRegistroDataSaida( String registroDataSaida) {
		this.registroDataSaida=registroDataSaida;
	}
}

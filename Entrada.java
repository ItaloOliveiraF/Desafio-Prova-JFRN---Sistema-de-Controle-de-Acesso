package acessojfrn;

//Essa classe � respons�vel por fazer a conex�o entre a "interface" do sistema e o controle de acessos para entrada e sa�da do sistema.
public class Entrada {
	
	ControleAcesso controle = new ControleAcesso();
	
	boolean Entrar(String nome) {
		boolean ehFuncionario = controle.verificaSeEhFuncionario(nome);
		boolean funcionarioNoPredio ;
		
		if (ehFuncionario) {
			funcionarioNoPredio = controle.verificaSeFuncionarioEstaNoPredio(nome);
		}
		else {
			funcionarioNoPredio = false;
		}
		
		boolean entradaLiberada = controle.liberaEntrada(nome, ehFuncionario, funcionarioNoPredio);
		
		if (entradaLiberada) {
			String registroHorarioEntrada = controle.calculaHorario();
			String registroDataEntrada = controle.calculaData();
			
			controle.RegistraAcessoEntrada(nome, registroHorarioEntrada, registroDataEntrada,ehFuncionario);
			return true;
		}
		else {
			return false;
		}
	}
	
	void Sair(String nome) {
		controle.RegistraAcessoSaida(nome);
	}

}

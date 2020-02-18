package br.com.alura.financas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import br.com.alura.financas.model.Categoria;
import br.com.alura.financas.model.Cliente;
import br.com.alura.financas.model.Conta;
import br.com.alura.financas.model.Endereco;
import br.com.alura.financas.model.Pais;
import br.com.alura.financas.model.TipoCategoria;
import br.com.alura.financas.model.TipoTransacao;
import br.com.alura.financas.model.Transacao;
import br.com.alura.financas.model.Uf;
import br.com.alura.financas.repository.CrudRepository;

public class Financas {

	private static CrudRepository<Categoria> crudRepositoryCategoria = new CrudRepository<>();
	private static CrudRepository<Cliente> crudRepositoryCliente = new CrudRepository<>();
	private static CrudRepository<Conta> crudRepositoryConta = new CrudRepository<>();
	private static CrudRepository<Endereco> crudRepositoryEndereco = new CrudRepository<>();
	private static CrudRepository<Transacao> crudRepositoryTransacao = new CrudRepository<>();

	public static void main(String[] args) {

		Categoria categoriaA = new Categoria(TipoCategoria.ACADEMIA);
		Categoria categoriaB = new Categoria(TipoCategoria.AGUA);
		Categoria categoriaC = new Categoria(TipoCategoria.CINEMA);
		Categoria categoriaD = new Categoria(TipoCategoria.COMPRAS);
		Categoria categoriaE = new Categoria(TipoCategoria.CONDOMINIO);
		Categoria categoriaF = new Categoria(TipoCategoria.INTERNET);
		Categoria categoriaG = new Categoria(TipoCategoria.LUZ);
		Categoria categoriaH = new Categoria(TipoCategoria.OUTROS);
		Categoria categoriaI = new Categoria(TipoCategoria.TELEFONE);
		Categoria categoriaJ = new Categoria(TipoCategoria.VIAGENS);
		Arrays.asList(categoriaA, categoriaB, categoriaC, categoriaD, categoriaE, categoriaF, categoriaG, categoriaH,
				categoriaI, categoriaJ).forEach(categoria -> crudRepositoryCategoria.persist(categoria));

		Endereco enderecoA = new Endereco("90030130", Pais.BRASIL, Uf.RS, "Porto Alegre", "Centro Historico",
				"Avenida Julio de Castilhos n 132", "lado par - Cjto: 1101");
		Endereco enderecoB = new Endereco("91250580", Pais.BRASIL, Uf.RS, "Porto Alegre", "Rubem Berta",
				"Rua Domenico Feoli n 76", "");
		Endereco enderecoC = new Endereco("91250001", Pais.BRASIL, Uf.RS, "Porto Alegre", "Jardim Leopoldina",
				"Rua Doutor Vargas Neto n 982", "lado impar");
		Endereco enderecoD = new Endereco("91250020", Pais.BRASIL, Uf.RS, "Porto Alegre", "Jardim Leopoldina",
				"Travessa Doutor Wilmar Jose da Costa Porto n 43", "");
		Endereco enderecoE = new Endereco("91250060", Pais.BRASIL, Uf.RS, "Porto Alegre", "Jardim Leopoldina",
				"Rua Marechal Francisco Antônio Bitencourt n 77", "");
		Endereco enderecoF = new Endereco("91220090", Pais.BRASIL, Uf.RS, "Porto Alegre", "Jardim Itu",
				"Praca Antonio Valentim Stoll n 108", "");
		Endereco enderecoG = new Endereco("91220120", Pais.BRASIL, Uf.RS, "Porto Alegre", "Jardim Itu", "Rua C n 11",
				"(R Adolfo Silva)");
		Endereco enderecoH = new Endereco("91130190", Pais.BRASIL, Uf.RS, "Porto Alegre", "Sarandi",
				"Avenida General Raphael Zippin n 609", "");
		Endereco enderecoI = new Endereco("91260090", Pais.BRASIL, Uf.RS, "Porto Alegre", "Morro Santana",
				"Rua Marieta Menna Barreto n 1608", "");
		Endereco enderecoJ = new Endereco("91260080", Pais.BRASIL, Uf.RS, "Porto Alegre", "Morro Santana",
				"Rua Eva Laci Camargo Martins n 299", "");
		Arrays.asList(enderecoA, enderecoB, enderecoC, enderecoD, enderecoE, enderecoF, enderecoG, enderecoH, enderecoI,
				enderecoJ).forEach(endereco -> crudRepositoryEndereco.persist(endereco));

		Cliente clienteA = new Cliente("Ana Cristina dos Santos", "Recepcionista", enderecoA, null);
		Cliente clienteB = new Cliente("Bruna Lennora", "Analista", enderecoB, null);
		Cliente clienteC = new Cliente("Carolina Santos do Nascimento", "Recepcionista", enderecoC, null);
		Cliente clienteD = new Cliente("Diana Dias", "Revendedora", enderecoD, null);
		Cliente clienteE = new Cliente("Ester Ferreira", "Publicitaria", enderecoE, null);
		Cliente clienteF = new Cliente("Fernanda Souza", "Empresaria", enderecoF, null);
		Cliente clienteG = new Cliente("Giana Maria", "Vendedora", enderecoG, null);
		Cliente clienteH = new Cliente("Helena Martins", "Medica", enderecoH, null);
		Cliente clienteI = new Cliente("Izaura Duwall", "Juiza", enderecoI, null);
		Cliente clienteJ = new Cliente("Julina Santos", "Diretora", enderecoJ, null);
		Arrays.asList(clienteA, clienteB, clienteC, clienteD, clienteE, clienteF, clienteG, clienteH, clienteI, clienteJ)
		.forEach(cliente -> crudRepositoryCliente.persist(cliente));
		
		Conta contaA = new Conta(clienteA, "11111-1", "BANCO BRADESCO", "1010", null);
		Conta contaB = new Conta(clienteB, "22222-2", "BANCO ITAU UNIBANCO", "2020", null);
		Conta contaC = new Conta(clienteC, "33333-3", "BANCO SANTANDER", "3030", null);
		Conta contaD = new Conta(clienteD, "44444-4", "CAIXA ECONOMICA FEDERAL", "4040", null);
		Conta contaE = new Conta(clienteE, "55555-5", "BANCO BRADESCO", "5050", null);
		Arrays
		.asList(contaA, contaB, contaC, contaD, contaE)
		.forEach(conta -> crudRepositoryConta.persist(conta));

		Transacao transacaoA = new Transacao(LocalDateTime.of(2019, Month.JANUARY, 10, 12, 14), new BigDecimal("1625.40"), "Compras no supermercado.", TipoTransacao.SAIDA, contaA, Arrays.asList(categoriaD));
		Transacao transacaoB = new Transacao(LocalDateTime.of(2018, Month.FEBRUARY, 23, 22, 11), new BigDecimal("3234.07"), "Viagem de fim de semana e outros.", TipoTransacao.SAIDA, contaA, Arrays.asList(categoriaH, categoriaJ));
		Transacao transacaoC = new Transacao(LocalDateTime.of(2019, Month.MARCH, 17, 10, 10), new BigDecimal("2121.23"), "Compras de fim de mes.", TipoTransacao.SAIDA, contaA, Arrays.asList(categoriaD));
		Transacao transacaoD = new Transacao(LocalDateTime.of(2016, Month.APRIL, 19, 7, 25), new BigDecimal("7895.63"), "Viagem de fim de ano.", TipoTransacao.SAIDA, contaB, Arrays.asList(categoriaJ));
		Transacao transacaoE = new Transacao(LocalDateTime.of(2011, Month.MAY, 02, 9, 54), new BigDecimal("29854.32"), "Viagem de fim de ano e pagamentos das faturas de agua, luz, academia, condominio, internet e telefone.", TipoTransacao.SAIDA, contaB, Arrays.asList(categoriaJ,categoriaB,categoriaG,categoriaA,categoriaE,categoriaF,categoriaI));
		Transacao transacaoF = new Transacao(LocalDateTime.of(2015, Month.JUNE, 07, 5, 04), new BigDecimal("120.42"), "Pagamento da academia.", TipoTransacao.SAIDA, contaC, Arrays.asList(categoriaD));
		Transacao transacaoG = new Transacao(LocalDateTime.of(2019, Month.JULY, 11, 14, 28), new BigDecimal("683.90"), "Pagamento da conta de luz.", TipoTransacao.SAIDA, contaC, Arrays.asList(categoriaG));
		Transacao transacaoH = new Transacao(LocalDateTime.of(2012, Month.AUGUST, 10, 19, 51), new BigDecimal("8794.56"), "Pagamento da conta de agua.", TipoTransacao.SAIDA, contaD, Arrays.asList(categoriaB));
		Transacao transacaoI = new Transacao(LocalDateTime.of(2017, Month.SEPTEMBER, 29, 8, 22), new BigDecimal("342.88"), "Compras no supermercado.", TipoTransacao.SAIDA, contaE, Arrays.asList(categoriaD));
		Transacao transacaoJ = new Transacao(LocalDateTime.of(2013, Month.OCTOBER, 01, 2, 34), new BigDecimal("93231.60"), "Recebimento do pagamento de clientes.", TipoTransacao.ENTRADA, contaE, Arrays.asList(categoriaH));
		Arrays
		.asList(transacaoA,transacaoB,transacaoC,transacaoD,transacaoE,transacaoF,transacaoG,transacaoH,transacaoI,transacaoJ)
		.forEach(transacao -> crudRepositoryTransacao.persist(transacao));
	}
}
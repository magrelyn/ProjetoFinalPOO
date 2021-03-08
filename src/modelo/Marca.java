package modelo;

public class Marca {

	private int idMar;
	private String nome;
	private String cnpj;
	private String slogan;

	public Marca(int idMar, String nome, String cnpj, String slogan) {
		super();
		this.setIdMar(idMar);
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.setSlogan(slogan);
	}

	public int getIdMar() {
		return idMar;
	}

	public void setIdMar(int idMar) {
		this.idMar = idMar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	@Override
	public String toString() {
		return idMar + ", " + nome + ", " + cnpj + ", " + slogan;
	}

}

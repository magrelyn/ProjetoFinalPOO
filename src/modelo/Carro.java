package modelo;

public class Carro {

	private int idCar;
	private String modelo;
	private int ano;
	private String cor;
	private double preco;
	// private int marca;
	private Marca marca;

	public Carro(int idCar, String modelo, int ano, String cor, double preco, Marca marca) {
		super();
		this.setIdCar(idCar);
		this.setModelo(modelo);
		this.setAno(ano);
		this.setCor(cor);
		this.setPreco(preco);
		this.setMarca(marca);
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return idCar + ", " + modelo + ", " + ano + ", " + cor + ", " + preco + ", " + marca;
	}

}

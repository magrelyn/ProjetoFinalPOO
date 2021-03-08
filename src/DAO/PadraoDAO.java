package DAO;
import java.util.List;

public interface PadraoDAO<T> {

	public int inserir(T obj);

	public int remover(int id);

	public int atualizar(T obj);

	public List<T> getAll();

	public T getById(int id);

}

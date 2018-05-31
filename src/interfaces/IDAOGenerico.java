package interfaces;

import java.io.Serializable;

public interface IDAOGenerico<Entidade> {
	
    public abstract void inserir(Entidade o);
    public abstract Entidade alterar(Entidade o);
    public abstract void remover(Entidade o);
    public abstract Entidade buscar(Entidade o);
   // public Entidade pesquisarNomeGenerico(String nome);
    public abstract Entidade buscarId(Serializable chave);
}
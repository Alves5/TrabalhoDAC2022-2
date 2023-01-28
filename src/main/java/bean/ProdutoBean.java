package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;
import static util.MessageUtil.addWarningMessage;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;
import dao.ProdutoDAO;
import entidade.Produto;

@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
	private List<Produto> lista;
	
	public String adicionar() {
		return produto.getId() == null ? save() : update();
	}
	
	public String save() {
		try {
			Produto produtoDoBanco = ProdutoDAO.sempreUltimo();
			if(produto.getNome().equals(produtoDoBanco.getNome()) && 
					produto.getPreco().equals(produtoDoBanco.getPreco()) && 
					produto.getCategoria().equals(produtoDoBanco.getCategoria()) && 
					produto.getQuantidade().equals(produtoDoBanco.getQuantidade())){
				addWarningMessage("Aviso", "Produto já existe.");
				reset();
			}else {
				produto.setDataCadastro(new Date()); ProdutoDAO.insert(produto);
				addInfoMessage("Sucesso", "Produto adicionado com sucesso.");
				produto = new Produto();
				PrimeFaces.current().ajax().update("dataTable");
			}
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao adicionar o produto.");
		}
		return null;
	}
	
	public String update() {
		try {
			Produto p = ProdutoDAO.buscarPorId(produto.getId());
			produto.setDataCadastro(p.getDataCadastro());
			produto.setDataEdicao(new Date());
			ProdutoDAO.update(produto);
			addInfoMessage("Sucesso", "Produto atualizado com sucesso.");
			lista = ProdutoDAO.list(); 
			reset();
			return "adicionarProduto";
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao atualizar produto.");
		}
		return null;
	}
	
	public String deletar() {
		try {
			ProdutoDAO.remove(produto);
			addInfoMessage("Deletado", "Produto deletado com sucesso."); 
			lista = ProdutoDAO.list();
			PrimeFaces.current().ajax().update("dataTable");
			reset();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao deletar produto.");
		}
		return null;	
	}
	
	public void reset() {
		produto.setId(null);
		produto.setNome(null);
		produto.setDescricao(null);
		produto.setPreco(null);
		produto.setQuantidade(null);
		produto.setCategoria(null);
        PrimeFaces.current().resetInputs("form:panel");
    }
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getLista() {
		if(lista == null) {
			lista = ProdutoDAO.list();
		}
		return lista;
	}
	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}
}

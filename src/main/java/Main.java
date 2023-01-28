import java.util.Date;

import dao.ProdutoDAO;
import entidade.Produto;

public class Main {

	public static void main(String[] args) {
		Produto produto = new Produto();
		Produto p = ProdutoDAO.buscarPorId(3);
		produto.setId(8);
		produto.setNome("Arroz");
		produto.setDescricao("Somente um teste");
		produto.setPreco(5.0);
		produto.setCategoria("Alimento");
		produto.setQuantidade(10);
		produto.setDataCadastro(p.getDataCadastro());
		produto.setDataEdicao(new Date());
		ProdutoDAO.update(produto);
	}

}

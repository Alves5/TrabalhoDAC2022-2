package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

import dao.JogoDAO;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;

	public String save() {
		try {
			jogo.setDataCadastro(new Date());
			jogo.setMaiorN(JogoDAO.maiorNumero(jogo.getV1(),jogo.getV2(),jogo.getV3(),jogo.getV4(),jogo.getV5()));
			JogoDAO.insert(jogo);
			addInfoMessage("Sucesso", "Números adicionados com sucesso.");
			jogo = new Jogo();
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao adicionar a jogada.");
		}
		return null;
	}
	
	public String update() {
		try {
			JogoDAO.update(jogo);
			addInfoMessage("Sucesso", "Números atualizados com sucesso.");
			lista = JogoDAO.list();
			return "listagem";
		} catch (Exception e) {
			addErrorMessage("Erro", "Erro ao atualizar números.");
		}
		return null;
	}
	
	public String deletar() {
		try {
			JogoDAO.remove(jogo);
			addInfoMessage("Deletada", "Números deletados com sucesso."); 
			lista = JogoDAO.list();
			PrimeFaces.current().ajax().update("dataTable");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			addErrorMessage("Erro", "Erro ao deletar números.");
		}
		return null;	
	}
	
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public List<Jogo> getLista() {
		if(lista  == null) {
			lista = JogoDAO.list();
		}
		return lista;
	}
	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}
}

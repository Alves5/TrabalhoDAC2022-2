package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Produto;
import util.JPAUtil;

public class ProdutoDAO {
	public static void insert(Produto p) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	public static void update(Produto p) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
	}
	public static void remove(Produto p) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		p = em.find(Produto.class, p.getId());
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}
	public static Produto buscarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Produto p1 = em.find(Produto.class, id);
		em.close();
		return p1;
	}
	public static List<Produto> list(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select p from Produto p");
		List<Produto> lista = q.getResultList();
		em.close();
		return lista;
	}
	public static Produto sempreUltimo() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query ultimo = em.createNativeQuery("SELECT MAX(id) FROM produto");
		Integer ul = (Integer) ultimo.getSingleResult();
		Produto maior = buscarPorId(ul);
		return maior;
	}
}

package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidade.Jogo;
import util.JPAUtil;

public class JogoDAO {
	public static void insert(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}
	public static void update(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}
	public static void remove(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		j = em.find(Jogo.class, j.getId());
		em.remove(j);
		em.getTransaction().commit();
		em.close();
	}
	public static Jogo buscarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Jogo j1 = em.find(Jogo.class, id);
		em.close();
		return j1;
	}
	public static List<Jogo> list(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j");
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	public static Integer maiorNumero(Integer v1, Integer v2, Integer v3, Integer v4, Integer v5) {
		Integer maior = 0;
		ArrayList<Integer> numero = new ArrayList<>();
		numero.add(v1);
		numero.add(v2);
		numero.add(v3);
		numero.add(v4);
		numero.add(v5);
		for (int n: numero ) {
			if(n >= maior) {
				 maior = n;
			}
		}
		return maior;
	}
}

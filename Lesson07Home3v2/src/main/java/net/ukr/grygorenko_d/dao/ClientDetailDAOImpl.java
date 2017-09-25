package net.ukr.grygorenko_d.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import net.ukr.grygorenko_d.entity.ClientDetail;

@Repository
public class ClientDetailDAOImpl implements ClientDetailDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ClientDetail getDetail(int clientId) {
		ClientDetail tempDetail = entityManager.find(ClientDetail.class, clientId);
		return tempDetail;
	}

	@Override
	public void saveDetail(ClientDetail clientDetail) {
		entityManager.merge(clientDetail);
	}

	@Override
	public void removeDetail(ClientDetail clientDetail) {
		entityManager.remove(clientDetail);
	}

}

package net.ukr.grygorenko_d.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.dao.ClientDAO;
import net.ukr.grygorenko_d.dao.ClientDetailDAO;
import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.ClientDetail;

@Service
public class ClientDetailServiceImpl implements ClientDetailService {
	@Autowired
	private ClientDetailDAO clientDetailDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional
	public ClientDetail getDetail(int clientId) {
		return clientDetailDAO.getDetail(clientId);
	}

	@Override
	@Transactional
	public void saveDetail(ClientDetail clientDetail) {
		clientDetailDAO.saveDetail(clientDetail);

	}

	@Override
	@Transactional
	public void removeDetailByClientId(int clientId) {
		Client tempClient = clientDAO.getClientById(clientId);
		ClientDetail clientDetail = tempClient.getClientDetail();
		clientDetailDAO.removeDetail(clientDetail);
		tempClient.setClientDetail(null);
	}

	@Override
	@Transactional
	public ClientDetail getClientDetailByClientId(int clientId) {
		Client tempClient = clientDAO.getClientById(clientId);
		return tempClient.getClientDetail();
	}

	@Override
	@Transactional
	public void saveDetailByClientId(int clientId, ClientDetail clientDetail) {
		if (clientId == 0) {
			clientDetailDAO.saveDetail(clientDetail);
		} else {
			Client tempClient = clientDAO.getClientById(clientId);
			tempClient.setClientDetail(clientDetail);
		}

	}

}

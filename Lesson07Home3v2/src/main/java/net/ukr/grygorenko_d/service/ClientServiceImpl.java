package net.ukr.grygorenko_d.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ukr.grygorenko_d.dao.ClientDAO;
import net.ukr.grygorenko_d.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional
	public void saveClient(Client client) {
		clientDAO.saveClient(client);
	}

	@Override
	@Transactional
	public Client getClient(int clientId) {
		Client client = clientDAO.getClientById(clientId);
		return client;
	}

	@Override
	@Transactional
	public List<Client> getClients() {
		return clientDAO.getClients();
	}

	@Override
	@Transactional
	public void deleteClient(int clientId) {
		Client tempClient = clientDAO.getClientById(clientId);
		clientDAO.deleteClient(tempClient);

	}

}

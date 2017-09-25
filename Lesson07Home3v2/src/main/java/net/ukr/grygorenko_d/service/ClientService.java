package net.ukr.grygorenko_d.service;

import java.util.List;

import net.ukr.grygorenko_d.entity.Client;

public interface ClientService {

	public void saveClient(Client client);

	public List<Client> getClients();

	public void deleteClient(int clientId);

	public Client getClient(int clientId);

}

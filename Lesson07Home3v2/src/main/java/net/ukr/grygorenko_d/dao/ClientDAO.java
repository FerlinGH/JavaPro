package net.ukr.grygorenko_d.dao;

import java.util.List;

import net.ukr.grygorenko_d.entity.Client;
import net.ukr.grygorenko_d.entity.Currencies;

public interface ClientDAO {

	public void saveClient(Client client);

	public List<Client> getClients();

	public Client getClientById(int clientId);

	public void deleteClient(Client tempClient);

	public boolean findAccount(int id, Currencies currency);

}

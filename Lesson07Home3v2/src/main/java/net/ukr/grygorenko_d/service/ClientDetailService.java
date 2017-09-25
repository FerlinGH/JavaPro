package net.ukr.grygorenko_d.service;

import net.ukr.grygorenko_d.entity.ClientDetail;

public interface ClientDetailService {

	public ClientDetail getDetail(int clientId);

	public void saveDetail(ClientDetail clientDetail);

	public void removeDetailByClientId(int clientId);

	public ClientDetail getClientDetailByClientId(int clientId);

	public void saveDetailByClientId(int clientId, ClientDetail clientDetail);

}

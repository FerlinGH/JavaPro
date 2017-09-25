package net.ukr.grygorenko_d.dao;

import net.ukr.grygorenko_d.entity.ClientDetail;

public interface ClientDetailDAO {

	public ClientDetail getDetail(int clientId);

	public void saveDetail(ClientDetail clientDetail);

	public void removeDetail(ClientDetail clientDetail);

}

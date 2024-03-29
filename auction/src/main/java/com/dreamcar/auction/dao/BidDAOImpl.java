package com.dreamcar.auction.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dreamcar.auction.entities.Auction;
import com.dreamcar.auction.entities.Bid;

@Repository
public class BidDAOImpl implements BidDAO {

	private EntityManager entityManager;
	
	@Autowired
	public BidDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void saveOrUpdateBid(Bid theBid) {
		
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save customer
		currentSession.saveOrUpdate(theBid);

	}

	@Override
	public List<Bid> findByUsername(String name) {
		
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create query
		Query<Bid> theQuery =
				currentSession.createQuery("from Bid Where username = '" + name + "'", Bid.class);
		
		// execute query and get result list
		List<Bid> bids = theQuery.getResultList();
		
		// return results
		return bids;
	}

}

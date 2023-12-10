package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.card.Card;
import repository.CardRepository;

import javax.persistence.EntityManager;

public class CardRepositoryImpl
        extends BaseEntityRepositoryImpl<Card, Integer>
        implements CardRepository {

    public CardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Card> getEntityClass() {
        return Card.class;
    }
}

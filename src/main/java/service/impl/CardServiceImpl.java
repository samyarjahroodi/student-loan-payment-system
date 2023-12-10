package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.card.Card;
import repository.CardRepository;
import service.CardService;

public class CardServiceImpl
        extends BaseEntityServiceImpl<Card, Integer, CardRepository>
        implements CardService {


    public CardServiceImpl(CardRepository repository) {
        super(repository);
    }
}
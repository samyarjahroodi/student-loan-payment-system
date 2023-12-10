package service;

import base.service.BaseEntityService;
import entity.Person;

public interface PersonService<T extends Person>
        extends BaseEntityService<T,Integer> {
}
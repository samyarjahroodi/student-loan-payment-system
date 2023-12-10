package repository;

import base.reposiotry.BaseEntityRepository;
import entity.Person;

public interface PersonRepository<T extends Person>
        extends BaseEntityRepository<T, Integer> {
}

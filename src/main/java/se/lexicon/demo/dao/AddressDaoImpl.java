package se.lexicon.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.demo.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    public Optional<Address> findById(int id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("Select a from Address a").getResultList();
    }

    @Override
    @Transactional
    public void remove(Address address) {
        findById(address.getId()).orElseThrow(() -> new IllegalArgumentException("Data nor found Exception"));
        entityManager.remove(address);
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return entityManager.merge(address);
    }
}

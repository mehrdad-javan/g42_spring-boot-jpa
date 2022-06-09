package se.lexicon.demo.dao;

import se.lexicon.demo.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

    Address save(Address address);

    Optional<Address> findById(int id);

    List<Address> findAll();

    void remove(Address address);

    Address update(Address address);


}

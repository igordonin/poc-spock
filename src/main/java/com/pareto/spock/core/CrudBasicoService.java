package com.pareto.spock.core;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;

public abstract class CrudBasicoService<Domain, ID extends Serializable> {

    private CrudRepository<Domain, ID> crudRepository;

    public CrudBasicoService(CrudRepository<Domain, ID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Domain save(Domain domain) {
        return this.crudRepository.save(domain);
    }

    public Iterable<Domain> save(Iterable<Domain> domains) {
        return crudRepository.saveAll(domains);
    }

    public Optional<Domain> get(ID id) {
        return crudRepository.findById(id);
    }

    public void delete(ID id) {
        crudRepository.deleteById(id);
    }

    public void delete(Domain domain) {
        crudRepository.delete(domain);
    }

    public void delete(Iterable<Domain> domains) {
        crudRepository.deleteAll(domains);
    }

    public Iterable<Domain> list() {
        return crudRepository.findAll();
    }

    public Iterable<Domain> findAll() {
        return crudRepository.findAll();
    }

    public Iterable<Domain> findAll(Iterable<ID> ids) {
        return crudRepository.findAllById(ids);
    }

    public long count() {
        return crudRepository.count();
    }

    public boolean exists(ID id) {
        return crudRepository.existsById(id);
    }
}
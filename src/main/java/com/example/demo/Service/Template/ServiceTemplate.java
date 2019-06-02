package com.example.demo.Service.Template;

import com.example.demo.DTO.EmailDto;
import com.example.demo.Model.Interface.Entity;
import com.example.demo.Repository.Template.RepositoryTemplate;

import java.util.List;

public class ServiceTemplate<T extends Entity> {

    private RepositoryTemplate<T> repository;

    public ServiceTemplate(RepositoryTemplate<T> repository) {
        this.repository = repository;
    }

    public List<T> getAll(){
        return repository.getAll();
    }

    public void add(T t){
        repository.add(t);
    }

    public Boolean remove(String id){
        return repository.remove(id);
    }

    public Boolean update(T t){
        return repository.update(t);
    }

    public T save(T t){
        return repository.save(t);
    }
}

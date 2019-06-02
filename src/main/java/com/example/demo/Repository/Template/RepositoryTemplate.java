package com.example.demo.Repository.Template;

import com.example.demo.Model.Interface.Entity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryTemplate<T extends Entity> {

        protected List<T> data;

        public List<T> getAll(){
            return data;
        }

        public void add(T t){
            data.add(t);
            data = data.stream()
                .distinct()
                .collect(Collectors.toList());
        }

        public Boolean remove(String id){
            return data.removeIf(el->el.getId().equals(id));
        }

        public Boolean update(T t){

            final Optional<T> optional = data.stream()
                .filter(info -> info.getId().equals(t.getId()))
                .findAny();

            optional.ifPresent(el -> data.set(data.indexOf(el), t));
            return optional.isPresent();
        }

        public T save(T t){
            data.add(t);
            return t;
        }

        public T get(String id){
            return getAll().stream()
                    .filter(key-> key.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }


    }

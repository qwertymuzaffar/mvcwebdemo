package com.example.mvcwebdemo.dao;

import com.example.mvcwebdemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository provides basic CRUD operations out of the box
}

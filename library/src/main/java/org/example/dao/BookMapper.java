package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.entity.Comment;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> getBooks ();

    List<Book> searchBooks(String name);

    Book getBookInformation(int id);

    int borrowBook(Borrow borrow);

    int returnBook(Borrow borrow);

    int reviewBook(Comment comment);



}

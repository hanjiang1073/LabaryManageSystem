package org.example.services.interfaces;

import org.example.entity.Book;

import java.util.List;

public interface BookService {
    // 获取图书列表
    List<Book> getBooks();
    //搜索图书
    List<Book> searchBooks(String name);

    //获取图书详情
    Book getBookInformation(int book_id);

    //借阅图书
    int borrowBook(int book_id,int user_id);

    //归还图书
    int returnBook(int book_id,int user_id);

    //图书评论
    int reviewBook(int book_id,int user_id,String comment);

}

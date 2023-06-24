package org.example;

import org.example.common.Result;
import org.example.entity.Book;
import org.example.services.interfaces.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Mytest {
    @Autowired
    BookService bookService;
    @Test
    void test(){
//        List<Book> bookList=bookService.searchBooks("三体1");
//        if (bookList.isEmpty()){
//            System.out.println("error");
//        }else {
//            System.out.println("success");
//        }
//        for(Book book: bookList)
//        {
//            System.out.println(book);
//        }
//
//        System.out.println(bookList.get(0).getBookContent());

        Book book = bookService.getBookInformation(1);
        System.out.println(book);

    }
    @Test
    void testinsert(){
        bookService.borrowBook(1,33);

    }
    @Test
    void testdel(){
        bookService.returnBook(1,33);

    }
    @Test
    void testrew(){
        bookService.reviewBook(2,33,"it is good!");

    }
}

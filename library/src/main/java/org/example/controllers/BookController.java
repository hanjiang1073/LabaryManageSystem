package org.example.controllers;

import org.example.common.Result;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.entity.Comment;
import org.example.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    public BookService bookService;

    @GetMapping("/getBooks")
    public Result getBooks() {
        List<Book> bookList = bookService.getBooks();
        if (bookList.isEmpty()) {
            return Result.error(bookList);
        } else {
            return Result.ok(bookList);
        }
    }

    @PostMapping("/searchBook")
    public Result searchBook(@RequestBody String name) {
        List<Book> bookList = bookService.searchBooks(name);
        if (bookList.isEmpty()) {
            return Result.error(bookList);
        } else {
            return Result.ok(bookList);
        }
    }

    @PostMapping("/getBookInformation")
    public Result getBookInformation(@RequestBody int bookId) {
        Book book = bookService.getBookInformation(bookId);
        if (book.getBookContent().isEmpty()) {
            return Result.error(book);
        } else {
            return Result.ok(book);
        }
    }

    @PostMapping("/borrowBook")
    public Integer borrowBook(@RequestBody Borrow borrow) {
        int bookId=borrow.getBookId();
        int userId=borrow.getUserId();
        return bookService.borrowBook(bookId, userId);
    }

    @PostMapping("/returnBook")
    public Integer returnBook(@RequestBody Borrow borrow ){
        int bookId=borrow.getBookId();
        int userId=borrow.getUserId();
        return bookService.returnBook(bookId,userId);
    }

    @PostMapping("/reviewBook")
    public Integer reviewBook(@RequestBody Comment comment){
        int bookId=comment.getBookId();
        int userId=comment.getUserId();
        String comment1=comment.getComment();
        return bookService.reviewBook(bookId,userId,comment1);
    }

}
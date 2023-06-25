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
    public Result searchBook(@RequestParam String name) {
        List<Book> bookList = bookService.searchBooks(name);
        if (bookList.isEmpty()) {
            return Result.error(bookList);
        } else {
            return Result.ok(bookList);
        }
    }

    @PostMapping("/getBookInformation")
    public Result getBookInformation(@RequestParam int bookId) {
        Book book = bookService.getBookInformation(bookId);
        if (book.getBookContent().isEmpty()) {
            return Result.error(book);
        } else {
            return Result.ok(book);
        }
    }

    @PostMapping("/borrowBook")
    public Result borrowBook(@RequestBody Borrow borrow) {
        int bookId=borrow.getBookId();
        int userId=borrow.getUserId();
        int i= bookService.borrowBook(bookId, userId);
        if(i==1){
            return Result.success();
        }
        else return Result.wrong();
    }

    @PostMapping("/returnBook")
    public Result returnBook(@RequestBody Borrow borrow ){
        int bookId=borrow.getBookId();
        int userId=borrow.getUserId();
        int i = bookService.returnBook(bookId,userId);
        if(i==1){
            return Result.success();
        }
        else return Result.wrong();
    }

    @PostMapping("/reviewBook")
    public Result reviewBook(@RequestBody Comment comment){
        int bookId=comment.getBookId();
        int userId=comment.getUserId();
        String comment1=comment.getComment();
        int i= bookService.reviewBook(bookId,userId,comment1);
        if(i==1){
            return Result.success();
        }
        else return Result.wrong();
    }

}
package org.example.services.impls;

import org.example.dao.BookMapper;
import org.example.entity.Book;
import org.example.entity.Borrow;
import org.example.entity.Comment;
import org.example.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> getBooks() {
        return bookMapper.getBooks();
    }

    @Override
    public List<Book> searchBooks(String name) {
        return bookMapper.searchBooks(name);
    }

    @Override
    public Book getBookInformation(int book_id) {

        return bookMapper.getBookInformation(book_id);
    }

    @Override
    public int borrowBook(int book_id, int user_id) {
        Date start_date = new Date(System.currentTimeMillis());
        Date end_date=new Date(System.currentTimeMillis()+24*60*60*1000*30L);
        Borrow borrow =new Borrow();
        borrow.setBookId(book_id);
        borrow.setUserId(user_id);
        borrow.setStartDate(start_date);
        borrow.setEndDate(end_date);
        return bookMapper.borrowBook(borrow);
    }

    @Override
    public int returnBook(int book_id,int user_id) {
        Borrow borrow =new Borrow();
        borrow.setBookId(book_id);
        borrow.setUserId(user_id);
        return bookMapper.returnBook(borrow);
    }

    @Override
    public int reviewBook(int book_id, int user_id, String comment) {
        Comment comment1 = new Comment();
        comment1.setBookId(book_id);
        comment1.setUserId(user_id);
        comment1.setComment(comment);
        return bookMapper.reviewBook(comment1);

    }
}

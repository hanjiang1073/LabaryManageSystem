package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jianghan
 */
@Data
@TableName("library_manager")
public class LibraryManager {
    private int id;

    private String opentime;

    private String closetime;

    private String content;

    private String duration;

    private int booksnum;


    public LibraryManager(int id, String opentime, String closetime, String content, String duration, int booksnum) {
        this.id = id;
        this.opentime = opentime;
        this.closetime = closetime;
        this.content = content;
        this.duration = duration;
        this.booksnum = booksnum;
    }

    public LibraryManager(String content, String duration, int booksnum) {
        this.content = content;
        this.duration = duration;
        this.booksnum = booksnum;
    }

    public LibraryManager(String opentime, String closetime, String content, String duration) {
        this.opentime = opentime;
        this.closetime = closetime;
        this.content = content;
        this.duration = duration;
    }

    public LibraryManager(String content, int booksnum) {
        this.content = content;
        this.booksnum = booksnum;
    }
}

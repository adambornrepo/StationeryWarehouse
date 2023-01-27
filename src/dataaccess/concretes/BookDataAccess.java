package dataaccess.concretes;

import dataaccess.abstracts.DataOperations;
import dataaccess.abstracts.Gateway;
import entities.concretes.Book;

import java.io.*;
import java.util.List;

public class BookDataAccess extends Gateway implements DataOperations {
    List<Book> bookList;

    public BookDataAccess(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public void read() {
        try {
            File file = new File(path + "BookData");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String books = null;
            while ((books = br.readLine()) != null) {
                String[] param = books.split(" ");
                Book book = new Book(Integer.parseInt(param[0]), param[1], param[2], param[3], Integer.parseInt(param[4]), Integer.parseInt(param[5]));
                bookList.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void write() {
        try {
            File file = new File(path + "BookData");
            FileWriter fr = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fr);
            for (Book book : bookList) {
                br.write(book.toString() + "\n");
                br.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


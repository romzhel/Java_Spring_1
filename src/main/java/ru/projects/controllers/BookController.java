package ru.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.projects.entities.Book;
import ru.projects.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/list")
    public String showAll(Model model) {
        model.addAttribute("booksList", bookService.getAllBooks());
        return "books-list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "add-book-form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String refreshList(Model model, Book book) {
        bookService.addBook(book);
        return "redirect:/books/list";
    }
}

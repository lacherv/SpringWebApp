package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Publisher publisher = new Publisher();
        publisher.setName("Foo");

        publisherRepository.save(publisher);

        //Maya Angelou
        Author maya = new Author("Maya", "Angelou");
        Book caged = new Book("I know why the caged bird sings", "1234", publisher);
        maya.getBooks().add(caged);
        caged.getAuthors().add(maya);

        authorRepository.save(maya);
        bookRepository.save(caged);

        //Chinua Achebe
        Author chi = new Author("Chinua", "Achebe");
        Book things = new Book("Things fall apart", "123456", publisher);
        chi.getBooks().add(things);

        authorRepository.save(chi);
        bookRepository.save(things);

    }


}

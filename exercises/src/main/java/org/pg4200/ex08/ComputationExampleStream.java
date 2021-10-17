package org.pg4200.ex08;

import org.pg4200.ex06.Author;
import org.pg4200.ex06.Book;

import java.util.List;
import java.util.stream.Collectors;

public class ComputationExampleStream implements ComputationExample {
    @Override
    public List<String> compute(List<Book> books) {

        return books.stream()
                .filter(book -> { return (book.getYear() >= 2010 && book.getYear() <= 2015); })
                .filter(book -> { return (book.getAuthors().size() >= 2); })
                .flatMap(book -> { return (book.getAuthors().stream()); })
                .filter(author -> { return (author.getName() != null && author.getSurname() != null); })
                .map(author -> { return (author.getName() + " " + author.getSurname()); })
                .distinct()
                .collect(Collectors.toList());
    }
}

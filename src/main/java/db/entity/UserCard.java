package db.entity;

import java.util.HashSet;
import java.util.Set;

public class UserCard extends Entity{
    private int id;
    private Set<Integer> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Integer> getBooks() {
        return books;
    }

    public void setBooks(Set<Integer> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", books=" + books +
                '}';
    }
}

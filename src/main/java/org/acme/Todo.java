package org.acme;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Todo extends PanacheEntity {
    public String title;
    public String url;
    @Column(name = "ordering")
    public int order;
    public boolean completed;

    public static void deleteCompleted() {
        delete("completed", true);
    }

    public static List<Todo> search(String title, int page) {
        return find("completed = false and title like ?1",
                "%"+title+"%").page(page, 3).list();
    }
}

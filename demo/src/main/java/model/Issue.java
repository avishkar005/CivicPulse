package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Issue {

    private final StringProperty title;
    private final StringProperty category;
    private final ObjectProperty<IssueStatus> status;
    private final ObjectProperty<LocalDate> date;

    public Issue(String title, String category, IssueStatus status, LocalDate date) {
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.status = new SimpleObjectProperty<>(status);
        this.date = new SimpleObjectProperty<>(date);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public IssueStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<IssueStatus> statusProperty() {
        return status;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}

package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Issue {

    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty category;
    private final StringProperty city;
    private final StringProperty userEmail;

    private final ObjectProperty<IssueStatus> status;
    private final ObjectProperty<LocalDate> date;

    public Issue(
            String title,
            String description,
            String category,
            String city,
            String userEmail,
            IssueStatus status,
            LocalDate date
    ) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.city = new SimpleStringProperty(city);
        this.userEmail = new SimpleStringProperty(userEmail);
        this.status = new SimpleObjectProperty<>(status);
        this.date = new SimpleObjectProperty<>(date);
    }

    /* =========================
       GETTERS
    ========================= */

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getUserEmail() {
        return userEmail.get();
    }

    public IssueStatus getStatus() {
        return status.get();
    }

    public LocalDate getDate() {
        return date.get();
    }

    /* =========================
       PROPERTIES (JavaFX)
    ========================= */

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty userEmailProperty() {
        return userEmail;
    }

    public ObjectProperty<IssueStatus> statusProperty() {
        return status;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}

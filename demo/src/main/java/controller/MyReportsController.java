package controller;

import java.time.format.DateTimeFormatter;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Issue;
import model.IssueStatus;
import service.IssueStore;

public class MyReportsController {

    @FXML private TableView<Issue> table;
    @FXML private TableColumn<Issue, String> titleCol;
    @FXML private TableColumn<Issue, String> categoryCol;
    @FXML private TableColumn<Issue, String> statusCol;
    @FXML private TableColumn<Issue, String> dateCol;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> categoryFilter;
    @FXML private ComboBox<IssueStatus> statusFilter;

    @FXML private Label totalCount;
    @FXML private Label pendingCount;
    @FXML private Label resolvedCount;

    private ObservableList<Issue> masterData;

    @FXML
    public void initialize() {

        // ✅ USE REAL STORE (not mock)
        masterData = IssueStore.getAll();

        titleCol.setCellValueFactory(c -> c.getValue().titleProperty());
        categoryCol.setCellValueFactory(c -> c.getValue().categoryProperty());

        statusCol.setCellValueFactory(c ->
                Bindings.createStringBinding(
                        () -> c.getValue().getStatus().name(),
                        c.getValue().statusProperty()
                )
        );

        dateCol.setCellValueFactory(cell ->
                Bindings.createStringBinding(
                        () -> cell.getValue().getDate()
                                .format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        cell.getValue().dateProperty()
                )
        );

        categoryFilter.setItems(FXCollections.observableArrayList(
                "Garbage", "Roads", "Electricity", "Water",
                "Public Safety", "Sanitation", "Other"
        ));

        statusFilter.setItems(
                FXCollections.observableArrayList(IssueStatus.values())
        );

        table.setItems(masterData);
        updateStats(masterData);

        searchField.textProperty().addListener((o,a,b) -> applyFilter());
        categoryFilter.valueProperty().addListener((o,a,b) -> applyFilter());
        statusFilter.valueProperty().addListener((o,a,b) -> applyFilter());
    }

    private void applyFilter() {

        ObservableList<Issue> filtered = FXCollections.observableArrayList();

        for (Issue issue : masterData) {

            boolean matchesSearch =
                    searchField.getText() == null ||
                    searchField.getText().isBlank() ||
                    issue.getTitle().toLowerCase()
                            .contains(searchField.getText().toLowerCase());

            boolean matchesCategory =
                    categoryFilter.getValue() == null ||
                    issue.getCategory().equals(categoryFilter.getValue());

            boolean matchesStatus =
                    statusFilter.getValue() == null ||
                    issue.getStatus() == statusFilter.getValue();

            if (matchesSearch && matchesCategory && matchesStatus) {
                filtered.add(issue);
            }
        }

        table.setItems(filtered);
        updateStats(filtered);
    }

    private void updateStats(ObservableList<Issue> list) {

        long pending = list.stream()
                .filter(i -> i.getStatus() == IssueStatus.PENDING)
                .count();

        long resolved = list.stream()
                .filter(i -> i.getStatus() == IssueStatus.RESOLVED)
                .count();

        totalCount.setText(String.valueOf(list.size()));
        pendingCount.setText(String.valueOf(pending));
        resolvedCount.setText(String.valueOf(resolved));
    }
}

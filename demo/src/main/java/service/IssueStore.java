package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Issue;

public class IssueStore {

    private static final ObservableList<Issue> issues =
            FXCollections.observableArrayList();

    public static ObservableList<Issue> getIssues() {
        return issues;
    }

    public static void addIssue(Issue issue) {
        issues.add(issue);
    }
}

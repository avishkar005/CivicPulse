package service;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Issue;
import model.IssueStatus;

public class MockDataService {

    private static final ObservableList<Issue> ISSUES =
            FXCollections.observableArrayList();

    static {
        ISSUES.add(new Issue(
                "Pothole near college",
                "Roads",
                IssueStatus.PENDING,
                LocalDate.now().minusDays(2)
        ));
        ISSUES.add(new Issue(
                "Garbage not collected",
                "Garbage",
                IssueStatus.RESOLVED,
                LocalDate.now().minusDays(5)
        ));
        ISSUES.add(new Issue(
                "Water leakage",
                "Water",
                IssueStatus.IN_PROGRESS,
                LocalDate.now().minusDays(1)
        ));
    }

    public static ObservableList<Issue> getMyIssues() {
        return ISSUES;
    }

    public static void addIssue(Issue issue) {
        ISSUES.add(issue);
    }

    public static boolean login(String email, String password) {
        return email.equals("user@civicpulse.com")
                && password.equals("1234");
    }
}

package service;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Issue;
import model.IssueStatus;

/**
 * TEMPORARY in-memory store for UI rendering.
 * Authentication is handled ONLY by backend (ApiService).
 */
public class MockDataService {

    private static final ObservableList<Issue> ISSUES =
            FXCollections.observableArrayList();

    static {
        ISSUES.add(new Issue(
                "Pothole near college",
                "Huge pothole causing traffic issues",
                "Roads",
                "Pune",
                "user@civicpulse.com",
                IssueStatus.PENDING,
                LocalDate.now().minusDays(2)
        ));

        ISSUES.add(new Issue(
                "Garbage not collected",
                "Garbage not collected for 3 days",
                "Garbage",
                "Pune",
                "user@civicpulse.com",
                IssueStatus.RESOLVED,
                LocalDate.now().minusDays(5)
        ));

        ISSUES.add(new Issue(
                "Water leakage",
                "Continuous water leakage near society gate",
                "Water",
                "Pune",
                "user@civicpulse.com",
                IssueStatus.IN_PROGRESS,
                LocalDate.now().minusDays(1)
        ));
    }

    /**
     * Used by My Reports screen for UI rendering
     */
    public static ObservableList<Issue> getMyIssues() {
        return ISSUES;
    }

    /**
     * Called after successful API issue submission
     */
    public static void addIssue(Issue issue) {
        ISSUES.add(issue);
    }
}

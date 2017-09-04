package com.fbosch.assignment.githubrepositories.ui;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fbosch.assignment.githubrepositories.R;
import com.fbosch.assignment.githubrepositories.injection.GithubServiceMock;
import com.fbosch.assignment.githubrepositories.injection.TestComponentRule;
import com.fbosch.assignment.githubrepositories.model.Repository;
import com.fbosch.assignment.githubrepositories.ui.repositorylist.RepositoryListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.fbosch.assignment.githubrepositories.ui.util.CustomViewMatcher.atPosition;
import static com.fbosch.assignment.githubrepositories.ui.util.CustomViewMatcher.matchesItemCount;

@RunWith(AndroidJUnit4.class)
public class RepositoryListActivityTest {

    private final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());

    private final ActivityTestRule<RepositoryListActivity> activityTestRule =
            new ActivityTestRule<>(RepositoryListActivity.class);

    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(activityTestRule);

    @Test
    public void repositoryListTest() {
        validateListItems(GithubServiceMock.getRepositoryList(15));
    }

    public static void validateListItems(List<Repository> repositories) {
        ViewInteraction view = onView(withId(R.id.repository_list));
        for (int i = 0; i < repositories.size(); i++) {
            Repository item = repositories.get(i);
            view.perform(scrollToPosition(i));
            view.check(matches(atPosition(i, withText(item.getName()), R.id.repository_name)))
                    .check(matches(atPosition(i, withText(item.getDescription()),
                            R.id.repository_description)))
                    .check(matches(atPosition(i, withText(item.getLanguage()),
                            R.id.repository_language)))
                    .check(matches(atPosition(i, withText(item.getStarsAsString()),
                            R.id.repository_stars)))
                    .check(matches(atPosition(i, withText(item.getStarsAsString()),
                            R.id.repository_stars)))
                    .check(matches(atPosition(i, withText(item.getForksAsString()),
                            R.id.repository_forks)))
                    .check(matches(atPosition(i, withText(item.getLastUpdate()),
                            R.id.repository_last_update)));
        }
        view.check(matches(matchesItemCount(repositories.size())));
    }

}

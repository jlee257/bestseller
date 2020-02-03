package com.example.bestseller.model;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class BookSource {

    private static final String NON_FICTIONS = "[{\"isbn\": \"9781984877499\", \"title\": \"A VERY STABLE GENIUS\", \"author\": \"Philip Rucker and Carol Leonnig\", \"description\": \"The Pulitzer Prize-winning journalists use firsthand accounts to chart patterns of behavior within the Trump administration.\", \"image\": \"https://s1.nyt.com/du/books/images/9781984877499.jpg\"}, {\"isbn\": \"9780062897909\", \"title\": \"PROFILES IN CORRUPTION\", \"author\": \"Peter Schweizer\", \"description\": \"The author of \\u201cClinton Cash\\u201d gives his evaluations of members of the Democratic Party.\", \"image\": \"https://s1.nyt.com/du/books/images/9780062897909.jpg\"}, {\"isbn\": \"9780399590504\", \"title\": \"EDUCATED\", \"author\": \"Tara Westover\", \"description\": \"The daughter of survivalists, who is kept out of school, educates herself enough to leave home for university.\", \"image\": \"https://s1.nyt.com/du/books/images/9780399590504.jpg\"}, {\"isbn\": \"9780812984965\", \"title\": \"JUST MERCY\", \"author\": \"Bryan Stevenson\", \"description\": \"A law professor and MacArthur grant recipient\\u2019s memoir of his decades of work to free innocent people condemned to death.\", \"image\": \"https://s1.nyt.com/du/books/images/9780812994520.jpg\"}, {\"isbn\": \"9780316478526\", \"title\": \"TALKING TO STRANGERS\", \"author\": \"Malcolm Gladwell\", \"description\": \"Famous examples of miscommunication serve as the backdrop to explain potential conflicts and misunderstandings.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316478526.jpg\"}, {\"isbn\": \"9781524763138\", \"title\": \"BECOMING\", \"author\": \"Michelle Obama\", \"description\": \"The former first lady describes her journey from the South Side of Chicago to the White House, and how she balanced work, family and her husband\\u2019s political ascent.\", \"image\": \"https://s1.nyt.com/du/books/images/9781524763138.jpg\"}, {\"isbn\": \"9781328662057\", \"title\": \"MAYBE YOU SHOULD TALK TO SOMEONE\", \"author\": \"Lori Gottlieb\", \"description\": \"A psychotherapist gains unexpected insights when she becomes another therapist\\u2019s patient.\", \"image\": \"https://s1.nyt.com/du/books/images/9781328662057.jpg\"}, {\"isbn\": \"9780143127741\", \"title\": \"THE BODY KEEPS THE SCORE\", \"author\": \"Bessel van der Kolk\", \"description\": \"How trauma affects the body and mind, and innovative treatments for recovery.\", \"image\": \"https://s1.nyt.com/du/books/images/9780670785933.jpg\"}, {\"isbn\": \"9780062316110\", \"title\": \"SAPIENS\", \"author\": \"Yuval Noah Harari\", \"description\": \"How Homo sapiens became Earth\\u2019s dominant species.\", \"image\": \"https://s1.nyt.com/du/books/images/9780062316097.jpg\"}, {\"isbn\": \"9781501106897\", \"title\": \"THE AGE OF ENTITLEMENT\", \"author\": \"Christopher Caldwell\", \"description\": \"An assessment of some potential social, cultural and economic causes of our current political fissure.\", \"image\": \"https://s1.nyt.com/du/books/images/9781501106897.jpg\"}, {\"isbn\": \"9780802147851\", \"title\": \"WHY WE CAN'T SLEEP\", \"author\": \"Ada Calhoun\", \"description\": \"The cultural and political contexts of the crises that Generation X women face.\", \"image\": \"https://s1.nyt.com/du/books/images/9780802147851.jpg\"}, {\"isbn\": \"9780316486637\", \"title\": \"CATCH AND KILL\", \"author\": \"Ronan Farrow\", \"description\": \"The Pulitzer Prize-winning reporter details some surveillance and intimidation tactics used to pressure journalists and elude consequences by certain wealthy and connected men.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316486637.jpg\"}, {\"isbn\": \"9780525655084\", \"title\": \"TIGHTROPE\", \"author\": \"Nicholas D Kristof and Sheryl WuDunn\", \"description\": \"The Pulitzer Prize-winning authors examine issues affecting working-class Americans.\", \"image\": \"https://s1.nyt.com/du/books/images/9780525655084.jpg\"}, {\"isbn\": \"9780593137581\", \"title\": \"RUNNING AGAINST THE DEVIL\", \"author\": \"Rick Wilson\", \"description\": \"The Republican strategist offers his insights on how to potentially defeat President Trump in the upcoming election.\", \"image\": \"https://s1.nyt.com/du/books/images/9780593137581.jpg\"}, {\"isbn\": \"9781501144325\", \"title\": \"WHY WE SLEEP\", \"author\": \"Matthew Walker\", \"description\": \"A neuroscientist uses recent scientific discoveries to explain the functions of sleep and dreams.\", \"image\": \"https://s1.nyt.com/du/books/images/9781501144318.jpg\"}]";

    public static Flowable<List<Book>> getBestNonFictions() {
        return Flowable.fromCallable(() -> {
            Gson gson = new Gson();
            return gson.<List<Book>>fromJson(NON_FICTIONS, new TypeToken<List<Book>>() {}.getType());
        }).subscribeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    public static void getBestNonFictions(Consumer<List<Book>> callback) {
        //noinspection ResultOfMethodCallIgnored
        getBestNonFictions().subscribe(callback::accept);
    }
}

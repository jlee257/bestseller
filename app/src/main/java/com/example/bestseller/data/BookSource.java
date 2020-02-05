package com.example.bestseller.data;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.bestseller.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class BookSource {

    private static final String FICTIONS = "[{\"isbn\": \"9781250209764\", \"title\": \"AMERICAN DIRT\", \"author\": \"Jeanine Cummins\", \"description\": \"A bookseller flees Mexico for the United States with her son while pursued by the head of a drug cartel.\", \"image\": \"https://s1.nyt.com/du/books/images/9781250209764.jpg\"}, {\"isbn\": \"9780735219090\", \"title\": \"WHERE THE CRAWDADS SING\", \"author\": \"Delia Owens\", \"description\": \"In a quiet town on the North Carolina coast in 1969, a young woman who survived alone in the marsh becomes a murder suspect.\", \"image\": \"https://s1.nyt.com/du/books/images/9780735219090.jpg\"}, {\"isbn\": \"9780316420327\", \"title\": \"LOST\", \"author\": \"James Patterson and James O Born\", \"description\": \"The new head of an F.B.I. task force takes on a crime syndicate run by a pair of Russian nationals.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316493987.jpg\"}, {\"isbn\": \"9781250301710\", \"title\": \"THE SILENT PATIENT\", \"author\": \"Alex Michaelides\", \"description\": \"Theo Faber looks into the mystery of a famous painter who stops speaking after shooting her husband.\", \"image\": \"https://s1.nyt.com/du/books/images/9781250301697.jpg\"}, {\"isbn\": \"9780385544184\", \"title\": \"THE GUARDIANS\", \"author\": \"John Grisham\", \"description\": \"Cullen Post, a lawyer and Episcopal minister, antagonizes some ruthless killers when he takes on a wrongful conviction case.\", \"image\": \"https://s1.nyt.com/du/books/images/9780385544184.jpg\"}, {\"isbn\": \"9780735224315\", \"title\": \"LITTLE FIRES EVERYWHERE\", \"author\": \"Celeste Ng\", \"description\": \"An artist upends a quiet town outside Cleveland.\", \"image\": \"https://s1.nyt.com/du/books/images/9780735224308.jpg\"}, {\"isbn\": \"9781984854797\", \"title\": \"DEAR EDWARD\", \"author\": \"Ann Napolitano\", \"description\": \"A 12-year-old boy tries to start over after becoming the sole survivor of a plane crash in which he lost his immediate family.\", \"image\": \"https://s1.nyt.com/du/books/images/9781984854780.jpg\"}, {\"isbn\": \"9780525541905\", \"title\": \"SUCH A FUN AGE\", \"author\": \"Kiley Reid\", \"description\": \"Tumult ensues when Alix Chamberlain\\u2019s babysitter is mistakenly accused of kidnapping her charge.\", \"image\": \"https://s1.nyt.com/du/books/images/9780525541905.jpg\"}, {\"isbn\": \"9781101986950\", \"title\": \"AGENCY\", \"author\": \"William Gibson\", \"description\": \"Ainsley Lowbeer can see alternate outcomes for Verity Jane and her digital assistant, who lived in the previous century.\", \"image\": \"https://s1.nyt.com/du/books/images/9781101986950.jpg\"}, {\"isbn\": \"9781982148249\", \"title\": \"THE OUTSIDER\", \"author\": \"Stephen King\", \"description\": \"A detective investigates a seemingly wholesome member of the community when an 11-year-old boy\\u2019s body is found.\", \"image\": \"https://s1.nyt.com/du/books/images/9781501180989.jpg\"}, {\"isbn\": \"9780062963697\", \"title\": \"THE DUTCH HOUSE\", \"author\": \"Ann Patchett\", \"description\": \"A sibling relationship is impacted when the family goes from poverty to wealth and back again over the course of many decades.\", \"image\": \"https://s1.nyt.com/du/books/images/9780062963673.jpg\"}, {\"isbn\": \"9780525540694\", \"title\": \"LONG BRIGHT RIVER\", \"author\": \"Liz Moore\", \"description\": \"Mickey risks her job with the Philadelphia police force by going after a murderer and searching for her missing sister.\", \"image\": \"https://s1.nyt.com/du/books/images/9780525540670.jpg\"}, {\"isbn\": \"9780399562501\", \"title\": \"THE GIVER OF STARS\", \"author\": \"Jojo Moyes\", \"description\": \"In Depression-era Kentucky, five women refuse to be cowed by men or convention as they deliver books.\", \"image\": \"https://s1.nyt.com/du/books/images/9780399562501.jpg\"}, {\"isbn\": \"9781984820150\", \"title\": \"A LONG PETAL OF THE SEA\", \"author\": \"Isabel Allende\", \"description\": \"A young pregnant widow and an Army doctor take a ship to Chile to escape the aftermath of the Spanish Civil War.\", \"image\": \"https://s1.nyt.com/du/books/images/9781984820150.jpg\"}, {\"isbn\": \"9780316055086\", \"title\": \"THE LAST WISH\", \"author\": \"Andrzej Sapkowski\", \"description\": \"Linked stories follow the exploits of Geralt of Rivia, a monster-slaying mercenary.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316438964.jpg\"}]";
    private static final String NON_FICTIONS = "[{\"isbn\": \"9781984877499\", \"title\": \"A VERY STABLE GENIUS\", \"author\": \"Philip Rucker and Carol Leonnig\", \"description\": \"The Pulitzer Prize-winning journalists use firsthand accounts to chart patterns of behavior within the Trump administration.\", \"image\": \"https://s1.nyt.com/du/books/images/9781984877499.jpg\"}, {\"isbn\": \"9780062897909\", \"title\": \"PROFILES IN CORRUPTION\", \"author\": \"Peter Schweizer\", \"description\": \"The author of \\u201cClinton Cash\\u201d gives his evaluations of members of the Democratic Party.\", \"image\": \"https://s1.nyt.com/du/books/images/9780062897909.jpg\"}, {\"isbn\": \"9780399590504\", \"title\": \"EDUCATED\", \"author\": \"Tara Westover\", \"description\": \"The daughter of survivalists, who is kept out of school, educates herself enough to leave home for university.\", \"image\": \"https://s1.nyt.com/du/books/images/9780399590504.jpg\"}, {\"isbn\": \"9780812984965\", \"title\": \"JUST MERCY\", \"author\": \"Bryan Stevenson\", \"description\": \"A law professor and MacArthur grant recipient\\u2019s memoir of his decades of work to free innocent people condemned to death.\", \"image\": \"https://s1.nyt.com/du/books/images/9780812994520.jpg\"}, {\"isbn\": \"9780316478526\", \"title\": \"TALKING TO STRANGERS\", \"author\": \"Malcolm Gladwell\", \"description\": \"Famous examples of miscommunication serve as the backdrop to explain potential conflicts and misunderstandings.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316478526.jpg\"}, {\"isbn\": \"9781524763138\", \"title\": \"BECOMING\", \"author\": \"Michelle Obama\", \"description\": \"The former first lady describes her journey from the South Side of Chicago to the White House, and how she balanced work, family and her husband\\u2019s political ascent.\", \"image\": \"https://s1.nyt.com/du/books/images/9781524763138.jpg\"}, {\"isbn\": \"9781328662057\", \"title\": \"MAYBE YOU SHOULD TALK TO SOMEONE\", \"author\": \"Lori Gottlieb\", \"description\": \"A psychotherapist gains unexpected insights when she becomes another therapist\\u2019s patient.\", \"image\": \"https://s1.nyt.com/du/books/images/9781328662057.jpg\"}, {\"isbn\": \"9780143127741\", \"title\": \"THE BODY KEEPS THE SCORE\", \"author\": \"Bessel van der Kolk\", \"description\": \"How trauma affects the body and mind, and innovative treatments for recovery.\", \"image\": \"https://s1.nyt.com/du/books/images/9780670785933.jpg\"}, {\"isbn\": \"9780062316110\", \"title\": \"SAPIENS\", \"author\": \"Yuval Noah Harari\", \"description\": \"How Homo sapiens became Earth\\u2019s dominant species.\", \"image\": \"https://s1.nyt.com/du/books/images/9780062316097.jpg\"}, {\"isbn\": \"9781501106897\", \"title\": \"THE AGE OF ENTITLEMENT\", \"author\": \"Christopher Caldwell\", \"description\": \"An assessment of some potential social, cultural and economic causes of our current political fissure.\", \"image\": \"https://s1.nyt.com/du/books/images/9781501106897.jpg\"}, {\"isbn\": \"9780802147851\", \"title\": \"WHY WE CAN'T SLEEP\", \"author\": \"Ada Calhoun\", \"description\": \"The cultural and political contexts of the crises that Generation X women face.\", \"image\": \"https://s1.nyt.com/du/books/images/9780802147851.jpg\"}, {\"isbn\": \"9780316486637\", \"title\": \"CATCH AND KILL\", \"author\": \"Ronan Farrow\", \"description\": \"The Pulitzer Prize-winning reporter details some surveillance and intimidation tactics used to pressure journalists and elude consequences by certain wealthy and connected men.\", \"image\": \"https://s1.nyt.com/du/books/images/9780316486637.jpg\"}, {\"isbn\": \"9780525655084\", \"title\": \"TIGHTROPE\", \"author\": \"Nicholas D Kristof and Sheryl WuDunn\", \"description\": \"The Pulitzer Prize-winning authors examine issues affecting working-class Americans.\", \"image\": \"https://s1.nyt.com/du/books/images/9780525655084.jpg\"}, {\"isbn\": \"9780593137581\", \"title\": \"RUNNING AGAINST THE DEVIL\", \"author\": \"Rick Wilson\", \"description\": \"The Republican strategist offers his insights on how to potentially defeat President Trump in the upcoming election.\", \"image\": \"https://s1.nyt.com/du/books/images/9780593137581.jpg\"}, {\"isbn\": \"9781501144325\", \"title\": \"WHY WE SLEEP\", \"author\": \"Matthew Walker\", \"description\": \"A neuroscientist uses recent scientific discoveries to explain the functions of sleep and dreams.\", \"image\": \"https://s1.nyt.com/du/books/images/9781501144318.jpg\"}]";


    public static Flowable<List<Book>> getBestFictions() {
        return Flowable.fromCallable(() -> {
            Gson gson = new Gson();
            return gson.<List<Book>>fromJson(FICTIONS, new TypeToken<List<Book>>() {
            }.getType());
        })
                .delay(3, TimeUnit.SECONDS)
                .startWith(new ArrayList<Book>())
                .subscribeOn(Schedulers.io())
                .doOnNext(d -> Log.i("BookSource", "book1=" + d.size()));
    }

    @SuppressLint("CheckResult")
    public static void getBestFictions(Consumer<List<Book>> callback) {
        //noinspection ResultOfMethodCallIgnored
        getBestFictions().filter(list -> !list.isEmpty()).subscribe(callback::accept);
    }


    public static Flowable<List<Book>> getBestNonFictions() {
        return Flowable.fromCallable(() -> {
            Gson gson = new Gson();
            return gson.<List<Book>>fromJson(NON_FICTIONS, new TypeToken<List<Book>>() {
            }.getType());
        })
                .delay(6, TimeUnit.SECONDS)
                .startWith(new ArrayList<Book>())
                .subscribeOn(Schedulers.io())
                .doOnNext(d -> Log.i("BookSource", "book2=" + d.size()));
    }

    @SuppressLint("CheckResult")
    public static void getBestNonFictions(Consumer<List<Book>> callback) {
        //noinspection ResultOfMethodCallIgnored
        getBestNonFictions().filter(list -> !list.isEmpty()).subscribe(callback::accept);
    }
}

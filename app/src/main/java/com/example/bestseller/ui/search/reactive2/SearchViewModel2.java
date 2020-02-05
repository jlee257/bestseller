package com.example.bestseller.ui.search.reactive2;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestseller.data.BookSource;
import com.example.bestseller.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

public class SearchViewModel2 extends ViewModel {

    private final BehaviorProcessor<String> queryProcessor = BehaviorProcessor.createDefault("");
    private final MutableLiveData<List<Book>> filteredBooksLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>(true);


    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public SearchViewModel2() {

        Flowable<List<Book>> fictionPublisher = BookSource.getBestFictions().share();



        Flowable<List<Book>> nonFictionPublisher = BookSource.getBestNonFictions().share();



        Flowable<List<Book>> bookPublisher = Flowable.combineLatest(
                fictionPublisher,
                nonFictionPublisher,
                (list1, list2) -> Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList())
        );



        Flowable<List<Book>> filteredPublisher = Flowable.combineLatest(
                bookPublisher,
                queryProcessor,
                (list, query) -> list.stream().filter(book -> book.contains(query)).collect(Collectors.toList())
        );



        Flowable<Boolean> loadingPublisher = Flowable.combineLatest(
                fictionPublisher,
                nonFictionPublisher,
                (list1, list2) -> list1.isEmpty() || list2.isEmpty()
        );



        filteredPublisher.subscribe(filteredBooksLiveData::postValue);
        loadingPublisher.subscribe(loadingLiveData::postValue);
    }


    void submitQuery(String query) {
        queryProcessor.onNext(query);
    }

    LiveData<List<Book>> getBooks() {
        return filteredBooksLiveData;
    }

    LiveData<Boolean> isLoading() {
        return loadingLiveData;
    }
}

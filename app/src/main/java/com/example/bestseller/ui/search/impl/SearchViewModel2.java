package com.example.bestseller.ui.search.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.bestseller.data.BookSource;
import com.example.bestseller.model.Book;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

public class SearchViewModel2 extends ViewModel {

    private final BehaviorProcessor<String> queryProcessor = BehaviorProcessor.createDefault("");
    private final BehaviorProcessor<List<Book>> filteredProcessor = BehaviorProcessor.create();
    private final BehaviorProcessor<Boolean> loadingProcessor = BehaviorProcessor.createDefault(true);


    public SearchViewModel2() {

        final Flowable<List<Book>> filteredPublisher = Flowable.combineLatest(
                Flowable.combineLatest(
                        BookSource.getBestFictions(),
                        BookSource.getBestNonFictions(),
                        (list1, list2) -> {
                            loadingProcessor.onNext(list1.isEmpty() || list2.isEmpty());
                            return combineList(list1, list2);
                        }),
                queryProcessor.debounce(300, TimeUnit.MILLISECONDS),
                this::getFilteredBooks
        );

        filteredPublisher.subscribe(filteredProcessor);
    }

    private List<Book> combineList(List<Book> list1, List<Book> list2) {
        return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
    }

    private List<Book> getFilteredBooks(List<Book> books, String query) {
        return books.stream().filter(book -> book.contains(query)).collect(Collectors.toList());
    }


    void submitQuery(String query) {
        queryProcessor.onNext(query);
    }

    LiveData<List<Book>> getBooks() {
        return LiveDataReactiveStreams.fromPublisher(filteredProcessor);
    }

    LiveData<Boolean> isLoading() {
        return LiveDataReactiveStreams.fromPublisher(loadingProcessor);
    }
}

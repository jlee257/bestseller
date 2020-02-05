package com.example.bestseller.ui.search.reactive3;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestseller.data.BookSource;
import com.example.bestseller.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

public class SearchViewModel3 extends ViewModel {

    private final BehaviorProcessor<String> queryProcessor = BehaviorProcessor.createDefault("");
    private final MutableLiveData<List<Book>> filteredBooksLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>(true);


    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public SearchViewModel3() {

        Flowable.combineLatest(
                Flowable.combineLatest(
                        BookSource.getBestFictions(),
                        BookSource.getBestNonFictions(),
                        (list1, list2) -> {
                            loadingLiveData.postValue(list1.isEmpty() || list2.isEmpty());
                            return Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
                        }),
                queryProcessor,
                (books, query) -> books.stream().filter(book -> book.contains(query))
                        .collect(Collectors.toList()))
                .subscribe(filteredBooksLiveData::postValue);
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

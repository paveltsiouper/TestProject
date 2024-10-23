package com.unittest.viewmodel;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountriesViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<Country>> observer;

    private CountriesViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CountriesViewModel();
    }

    @Test
    public void testSearchFunctionality() {
        MutableLiveData<List<Country>> liveData = new MutableLiveData<>();
        when(viewModel.getCountries()).thenReturn(liveData);

        viewModel.getCountries().observeForever(observer);
        viewModel.search("USA");

        verify(observer).onChanged(anyList());
    }

    @Test
    public void testRefreshFunctionality() {
        MutableLiveData<List<Country>> liveData = new MutableLiveData<>();
        when(viewModel.getCountries()).thenReturn(liveData);

        viewModel.getCountries().observeForever(observer);
        viewModel.refresh();

        verify(observer).onChanged(anyList());
    }
}

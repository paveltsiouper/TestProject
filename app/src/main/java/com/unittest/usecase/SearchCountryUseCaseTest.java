package com.unittest.usecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SearchCountryUseCaseTest {
    private final String input;
    private final String expectedOutput;

    public SearchCountryUseCaseTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"USA", "United States"},
                {"CAN", "Canada"},
                {"MEX", "Mexico"},
                {"GBR", "United Kingdom"},

        });
    }

    @Test
    public void testSearchCountry() {
        SearchCountryUseCase useCase = new SearchCountryUseCase();
        assertEquals(expectedOutput, useCase.search(input));
    }

    @Test
    public void testSearchCountryWithInvalidCode() {
        SearchCountryUseCase useCase = new SearchCountryUseCase();
        String result = useCase.search("INVALID_JOPA");
        assertEquals("Country not found", result);
    }
}

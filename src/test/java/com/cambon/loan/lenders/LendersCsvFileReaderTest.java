package com.cambon.loan.lenders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class LendersCsvFileReaderTest {

	private static final String NON_EXISTENT_FILE = "/some/unknown/file.csv";
	private static final String MARKET_FILE = "MarketTestData.csv";
	private static final int EXPECTED_NUMBER_OF_LENDERS = 7;
	private LendersCsvFileReader lendersCsvFileReader = new LendersCsvFileReader();

	@Test
	void lendersFileReaderThrowsExceptionWhenFileNotFound() {

		Assertions.assertThrows(IOException.class, () -> {
			lendersCsvFileReader.readLendersFromFile(NON_EXISTENT_FILE);
		});
	}

	@Test
	void allLendersAreReadFromFile() throws URISyntaxException, IOException {

		List<Lender> lenders = lendersCsvFileReader.readLendersFromFile(
				Paths.get(LendersCsvFileReaderTest.class.getClassLoader().getResource(MARKET_FILE).toURI()));
		Assertions.assertTrue(lenders.size() == EXPECTED_NUMBER_OF_LENDERS);
	}

	@Test
	void lendersSortedByRate() throws URISyntaxException, IOException {

		List<Lender> lenders = lendersCsvFileReader.readLendersFromFile(Paths.get(LendersCsvFileReaderTest.class.getClassLoader().getResource(MARKET_FILE).toURI()));
		boolean sortedByAscendingRate =
				IntStream.range(0, lenders.size() - 1)
						.allMatch(i -> lenders.get(i).getRate() <= lenders.get(i + 1).getRate());
		Assertions.assertTrue(sortedByAscendingRate);

	}

}

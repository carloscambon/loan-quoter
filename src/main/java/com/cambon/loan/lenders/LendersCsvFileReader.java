package com.cambon.loan.lenders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class LendersCsvFileReader implements LendersFileReader {

	private static Function<String, Lender> mapToLender = (line) -> {
		String[] p = line.split(",");
		return new Lender(p[0], Double.valueOf(p[1]), Integer.valueOf(p[2]));
	};

	private static Comparator<Lender> byAscendingRate = (e1, e2) -> Double.compare(
			e1.getRate(), e2.getRate());

	@Override
	public List<Lender> readLendersFromFile(String filePath) throws IOException {

		return readLendersFromFile(Paths.get(filePath));
	}

	@Override
	public List<Lender> readLendersFromFile(Path path) throws IOException {

		return Files.lines(path)
				.skip(1)
				.map(mapToLender)
				.sorted(byAscendingRate)
				.collect(toList());
	}

	@Override
	public boolean existsFile(String filePath) {

		return new File(filePath).exists();
	}
}

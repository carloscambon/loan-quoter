package com.cambon.loan.lenders;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface LendersFileReader {

	List<Lender> readLendersFromFile(String filePath) throws IOException;

	List<Lender> readLendersFromFile(Path path) throws IOException;

	boolean existsFile(String filePath);
}

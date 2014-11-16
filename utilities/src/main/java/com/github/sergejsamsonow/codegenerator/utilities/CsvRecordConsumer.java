package com.github.sergejsamsonow.codegenerator.utilities;

import org.apache.commons.csv.CSVRecord;

public interface CsvRecordConsumer {

    /**
     * Load bean content from csv record.
     */
    public void populateFrom(CSVRecord record);
}

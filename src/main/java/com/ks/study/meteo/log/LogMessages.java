package com.ks.study.meteo.log;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LogMessages {


    public static final String REST_CLIENT_EXCEPTION_MESSAGE = "Client error was thrown: %s";
    public static final String REST_SERVER_EXCEPTION_MESSAGE = "Server error was thrown: %s";
    public static final String PDF_GENERATION_EXCEPTION_MESSAGE = "Problem during saving pdf file %s";
    public static final String PDF_GENERATION_SUCCESS_MESSAGE = "Pdf successfully saved";
    public static final String JSON_GENERATION_EXCEPTION_MESSAGE = "Problem during saving json file: %s";
    public static final String XML_GENERATION_EXCEPTION_MESSAGE = "Problem during saving xml file: %s";

}

package br.com.crechesystem.crechesystem.utils;

import br.com.crechesystem.crechesystem.service.TurmaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;

public final class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    public static String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                return null;
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }
}

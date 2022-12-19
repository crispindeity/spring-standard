package study.springstandard.ch2;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RequestHeader {

    @RequestMapping("/requestHeader")
    public void header(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }
    }

    @RequestMapping("/requestMessage")
    public void message(HttpServletRequest request) throws IOException {
        String requestLine = request.getMethod();
        requestLine += " " + request.getRequestURI();

        String queryString = request.getQueryString();
        requestLine += queryString == null ? "" : "?" + queryString;
        requestLine += " " + request.getProtocol();
        System.out.println(requestLine);

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }

        final int CONTENT_LENGTH = request.getContentLength();

        if (CONTENT_LENGTH > 0) {
            byte[] content = new byte[CONTENT_LENGTH];

            InputStream in = request.getInputStream();
            in.read(content, 0, CONTENT_LENGTH);

            System.out.println();
            System.out.println(new String(content, StandardCharsets.UTF_8));
        }
    }
}

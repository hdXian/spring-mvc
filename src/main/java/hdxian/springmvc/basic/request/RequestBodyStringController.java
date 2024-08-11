package hdxian.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * old usage with HttpServletRequest, HttpServletResponse
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body={}", messageBody);
        response.getWriter().write("request body string v1");
    }

    /**
     * able to use InputStream, Writer as handler arguments
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responsewriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("message body={}", messageBody);
        responsewriter.write("request body string v2");
    }

    /**
     * HttpEntity - get http request message body
     * or put data into http response message body
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("message body={}", messageBody);
        return new HttpEntity<>("request body string v3");
    }

    // usage of RequestEntity<>, ResponseEntity<>
    @PostMapping("/request-body-string-v3-2")
    public ResponseEntity<String> requestBodyStringV3_2(RequestEntity<String> requestEntity) throws IOException {
        HttpHeaders headers = requestEntity.getHeaders();
        log.info("http headers={}", headers);

        String messageBody = requestEntity.getBody();
        log.info("message body={}", messageBody);
        // usage: new ResponseEntity<>(T body, HttpHeaders, HttpStatus);
        return new ResponseEntity<>("request body string v3-2", HttpStatus.CREATED); // T body
    }

    /**
     * @RequestBody - get http request message body directly <br>
     * @ResponseBody - put string into http response message body directly
     * uses HttpMessageConverter -> StringHttpMessageConverter
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("message body={}", messageBody);
        return "request body string v4";
    }

}

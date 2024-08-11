package hdxian.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hdxian.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * example request
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
@RequestMapping("/request-body-json")
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // get message body as string by HttpServletRequest
    // parse string to json by using objectMapper
    @PostMapping("/v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("RequestBodyJsonController.requestBodyJsonV1");

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("request body json v1");
    }

    // @RequestBody data is required. (any string)
    // @RequestBody, @ResponseBody will use HttpMessageConverter -> StringHttpMessageConverter.
    @ResponseBody
    @PostMapping("/v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("RequestBodyJsonController.requestBodyJsonV2");

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "request body json v2";
    }

    // @RequestBody data is required.
    // can't omit @RequestBody. (the argument will be bound as query parameter (@ModelAttribute, @RequestParam))
    // JSON request -> MappingJackson2HttpMessageConverter -> helloData
    // return string -> StringHttpMessageConverter -> text/plain response
    // if content-type is application/json, json form message body is required.
    // it doesn't have to be bound with properties of HelloData.
    @ResponseBody
    @PostMapping("/v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("RequestBodyJsonController.requestBodyJsonV3");

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "request body json v3";
    }

    // also, it's able to use HttpEntity<T>
    @ResponseBody
    @PostMapping("/v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        log.info("RequestBodyJsonController.requestBodyJsonV4");

        HelloData helloData = data.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "request body json v4";
    }

    // it's able to return Object
    // @RequestBody: JSON request -> MappingJackson2HttpMessageConverter -> HelloData
    // @ResponseBody: return HelloData -> MappingJackson2HttpMessageConverter -> JSON response
    @ResponseBody
    @PostMapping("/v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("RequestBodyJsonController.requestBodyJsonV5");

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }

}

package hdxian.springmvc.basic.response;

import hdxian.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@ResponseBody
public class ResponseBodyController {

    /**
     * examples of put data into http message body directly
     */

    // using HttpServletResponse.getWriter()
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("response body string v1");
    }

    // return ResponseEntity<T>(T body, HttpStatus)
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("response body string v2", HttpStatus.OK);
    }

    // return String
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "response body string v3";
    }

    // return ResponseEntity<T>
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("gdh");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
//        return new ResponseEntity<>(HttpStatus.OK); // empty body
    }

    // return HelloData object
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("gdh2");
        helloData.setAge(22);
        return helloData;
    }

}

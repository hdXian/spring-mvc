package hdxian.springmvc.basic.request;

import hdxian.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // get request parameter by using HttpServlet <br>
    // returns void, getWriter().write("...") -> able to write text in message body directly
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("log username={}, age={}", username, age);

        response.getWriter().write("request param v1");
    }

    // use @RequestParam
    @ResponseBody // able to write text in message body directly
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int userAge) {
        log.info("log username={}, age={}", memberName, userAge);
        return "request param v2";
    }

    // If request method argument name is same as parameter name,
    // it's able to omit parameter name in @RequestParam
    // If it occurs an Exception, add -parameters option on java compiler.
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("log username={}, age={}", username, age);
        return "request param v3";
    }

    // and also able to omit @RequestParam annotation.
    // but "required" setting become "false"
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("log username={}, age={}", username, age);
        return "request param v4";
    }

    // @RequestParam required setting
    // primitive type "int" can't store null value. must use "Integer" or defaultValue setting.
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(value = "username", required = true) String username,
                                       @RequestParam(value = "age", required = false) Integer age)
    {
        log.info("log username={}, age={}", username, age);
        return "request param required";
    }

    // if there are no value at the parameter, default value is set.
    // so required option has no meaning when defaultValue option used.
    // defaultValue option also activated when parameter value is ""
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(value = "username", required = true, defaultValue = "guest") String username,
                                       @RequestParam(value = "age", required = false, defaultValue = "-1") Integer age)
    {
        log.info("log username={}, age={}", username, age);
        return "request param default";
    }

    // get request parameters as map
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("log username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "request param map";
    }

    // bind request parameters to properties of HelloData
    // spring parses simple type of arguments as @RequestParam (ex. String, int, Integer)
    // the other types (ex. HelloData) are parsed as @ModelAttribute
    // @ModelAttribute can be omitted.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("log username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "model attribute v1";
    }


}

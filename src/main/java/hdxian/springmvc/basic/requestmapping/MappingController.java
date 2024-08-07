package hdxian.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    // able to mapping multiple URLs
    @RequestMapping({"/hello-basic", "/hello-basic2"})
    public String helloBasic() {
        log.info("log {}", "hello-basic");
        return "hello-basic";
    }

    // it's different between /URL and /URL/ (since spring boot 3.0)
    @RequestMapping({"/hello-multiple", "/hello-multiple/"})
    public String helloMultiple() {
        log.info("log {}", "hello-multiple");
        return "hello-multiple";
    }

    // only allows GET method
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "mappingGetV1";
    }

    /**
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "mappingGetV2";
    }

    // able to remove ("userId") when identifier name and path variable name are same
    // (@PathVariable String userId)
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mappingPath userid={}", userId);
        return "mapping path variable";
    }

    // able to mapping multiple path variables
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String userId, @PathVariable("orderId") Long orderId) {
        log.info("mapping path userid={}, orderId={}", userId, orderId);
        return "mapping multiple path variable";
    }

    /**
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode=debug", "data=good"}
     */
    // only respond if there are mode=debug query parameter in URL
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "mappingParam";
    }

    /**
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "mappingHeader";
    }

    /**
     *  based on Content-Type header
     *  consumes="application/json"
     *  consumes="!application/json"
     *  consumes="application/*"
     *  consumes="*\/*"
     *  consumes = {"text/plain", "application/*"}
     */
//    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "mappingConsume";
    }


    /**
     * based on Accept header
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * produces = {"text/plain", "application/*"}
     * produces = "text/plain;charset=UTF-8"
     */
//    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "mappingProduces";
    }

}

package hdxian.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // auto injected by @Slf4j
//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        /*
        do not use log like this.
        this method isn't called on level "debug", but string concat operation always executed.
        so it may occur waste of resource.
        */
//        log.trace("trace log=" + name);

        return "OK";
    }
}

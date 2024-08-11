package hdxian.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        return null;
    }

}

package hdxian.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // basic template resource path is [classpath:/templates]

    /**
     * create ModelAndView object
     * add Object on model of ModelAndView
     * return ModelAndView object
     */
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("/response/hello"); // new ModelAndView(String viewName)
        mav.addObject("data", "response view v1");
        return mav;
    }

    /**
     * use Model type argument
     * add Object on Model
     * return logical View Name (String)
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "response view v2");
        return "response/hello";
    }

    /**
     * condition 1. @Controller on class level.
     * condition 2. no argument that write data directly on http body in method (ex. HttpServletResponse, Writer)
     * if satisfy both conditions, spring mvc resolves request URI as logicla view name.
     * not recommended. (low readability, low usability)
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "response view v3");
    }

}

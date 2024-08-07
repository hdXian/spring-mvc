package hdxian.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * user management API example (only mapping example)
     * get user list: GET /users
     * add new user: POST /users
     * get user by id: GET /users/{userId}
     * modify user: PATCH /users/{userId}
     * remove user: DELETE /users/{userId}
     */

    // @GetMapping("/mapping/users")
    @GetMapping
    public String users() {
        return "get /users";
    }

    // @PostMapping("/mapping/users")
    @PostMapping
    public String addUser() {
        return "post /users";
    }

    // @GetMapping("/mapping/users/{userId}")
    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "patch userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId=" + userId;
    }

}

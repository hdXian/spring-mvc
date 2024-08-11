package hdxian.springmvc.basic;

import lombok.Data;

@Data // Equals to apply @Getter, @Setter, @ToString, @RequiredArgsConstructor
public class HelloData {
    private String username;
    private int age;
}

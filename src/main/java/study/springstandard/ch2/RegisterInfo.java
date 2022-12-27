package study.springstandard.ch2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterInfo {

    private String id;
    private String pwd;
    private String name;
    private String email;
    private String birth;

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "id='" + id + '\'' +
                ", password='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}

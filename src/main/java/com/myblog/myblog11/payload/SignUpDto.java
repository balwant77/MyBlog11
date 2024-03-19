package com.myblog.myblog11.payload;

import com.myblog.myblog11.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {


    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String roleType;


}

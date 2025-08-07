package org.example.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CandidateBO {

    private String id;
    private String realName;
    private String identityNum;
    private String mobile;
    private Integer sex;
    private String email;
    private LocalDate birthday;
    private String jobId;

}

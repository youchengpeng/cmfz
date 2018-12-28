package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String phone;
    private String password;
    private String slat;
    private String sign;
    private String head_pic;
    private String name;
    private String dharma;
    private Integer sex;
    private String province;
    private String city;
    private String status;
    private Date reg_date;
    private int cou;
}

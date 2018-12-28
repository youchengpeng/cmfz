package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "/chapter")
public class Chapter {
    @Id
    private String id;
    @Excel(name = "章节名")
    private String title;
    @Excel(name = "大小")
    private Integer size;
    @Excel(name = "时长")
    private String duration;
    @Excel(name = "路径")
    private String url;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传日期", format = "YYYY年MM月dd日", width = 20)
    private Date upload_date;
    @Column(name = "alb_id")
    @ExcelIgnore
    private Integer albid;
    @Transient
    @ExcelIgnore
    private Album album;
}

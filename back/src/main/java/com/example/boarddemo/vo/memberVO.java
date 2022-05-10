package com.example.boarddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class memberVO {
    private int m_seq;
    private String m_id;
    private String m_pw;
    private String m_name;
    private String m_tell;
    private String m_email;
    private String m_date;
    private Boolean m_enabled;
    private String m_role;


}

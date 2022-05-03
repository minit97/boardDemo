package com.example.boarddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

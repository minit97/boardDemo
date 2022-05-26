package com.example.boarddemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private int b_seq;
    private String b_title;
    private String b_content;
    private String b_fileOriginName;
    private String b_fileName;
    private String b_indate;
    private String b_update;
    private int m_seq;
    private String m_name;
}

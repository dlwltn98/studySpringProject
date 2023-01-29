package com.dlwltn.cmm.mvc.domain;

import lombok.Data;

@Data
public class CmmUser {
    
    private String userId;             /*    사용자 정보 테이블,    사용자 아이디    */
    private String name;               /*    사용자 정보 테이블,    사용자 이름    */
    private String pwd;                /*    사용자 정보 테이블,    비밀번호    */
    private String email;              /*    사용자 정보 테이블,    이메일    */
    private String useAt;              /*    사용자 정보 테이블,    사용 여부    */
    private String registDt;           /*    사용자 정보 테이블,    등록 일시    */
    private String registUser;         /*    사용자 정보 테이블,    등록 유저    */
    private String updtDt;             /*    사용자 정보 테이블,    수정 일시    */
    private String updtUser;           /*    사용자 정보 테이블,    수정 유저    */
}
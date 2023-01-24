-- INSERT INTO TBL_MEMVER (컬럼1, 컬럼2, ...) values (컬럼1에 넣을 데이터, 컬럼2에 넣을 데이터 ...);
-- INSERT INTO TBL_MEMBER values (DEFAULT, 'admin', 'admin', '운영자', 'ROLE_ADMIN', DEFAULT, 'admin@naver.com'); -- admin 
INSERT INTO TBL_MEMBER values (DEFAULT, 'admin', '$2a$10$COvazywgZPXseeKaYhruh.pAYYfcSeGO5aSrHOsLZN0X8joNwW2dW', '운영자', 'ROLE_ADMIN', DEFAULT, 'admin@naver.com'); -- admin 
-- INSERT INTO TBL_MEMBER values (DEFAULT, 'user01', 'pass01', '둘리', DEFAULT, DEFAULT, 'user01@naver.com'); -- 더미 회원 
COMMIT;

  --   MEMBER_CODE INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '멤버 번호',
--     MEMBER_ID VARCHAR(50) NOT NULL UNIQUE COMMENT '멤버 아이디',
--     MEMBER_PASSWORD VARCHAR(50) NOT NULL COMMENT '멤버 비밀번호',
--     MEMBER_NAME VARCHAR(50) NOT NULL COMMENT '멤버 이름',
--     MEMBER_ROLE VARCHAR(10) DEFAULT 'USER' NOT NULL COMMENT '멤버 역할',
--     MEMBER_STATUS CHAR(1) DEFAULT 'Y' NOT NULL COMMENT '멤버 활성 상태',
--     MEMBER_EMAIL VARCHAR(100) NOT NULL COMMENT '멤버 이메일',
--     REG_DATE DATETIME DEFAULT NOW() NOT NULL COMMENT '가입일'

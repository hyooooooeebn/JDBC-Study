SELECT USER
FROM DUAL;

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	손범석	010-9999-9999
10	손범석	010-1100-1100
11	최수지	010-1100-1100
12	채지윤	010-1200-1200
*/

--○ CallableStatement 실습을 위한 프로시저 작성
-- 프로시저 명 : PRC_MEMBERINSERT
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- 주요 변수 선언
    VSID TBL_MEMBER.SID%TYPE;
    
BEGIN

    -- 기존 SID의 최대값 얻어오기
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;
    
    
    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VSID+1), VNAME, VTEL);
    
    -- 커밋
    COMMIT;    
    
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.

--○ 생성된 프로시저 테스트
EXEC PRC_MEMBERINSERT('서승균','010-3733-7202');
--==>> PL/SQL 프로시저가 성공적으로 완료되었습니다.

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	손범석	010-9999-9999
10	손범석	010-1100-1100
11	최수지	010-1100-1100
12	채지윤	010-1200-1200
13	서승균	010-3733-7202
*/


SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	김진희	010-1111-1111
2	이찬호	010-2222-2222
3	박혜진	010-3333-3333
4	윤유동	010-4444-4444
5	박혜진	010-5555-5555
6	정효진	010-6666-6666
7	손다정	010-7777-7777
8	박효빈	010-8888-8888
9	손범석	010-9999-9999
10	손범석	010-1100-1100
11	최수지	010-1100-1100
12	채지윤	010-1200-1200
13	서승균	010-3733-7202
14	장민지	010-1234-1234
15	장진하	010-4569-4569
16	정가연	010-7894-7894
*/


--○ CallableStatement 실습을 위한 프로시저 작성
-- ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN

    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
        
    --CLOSE VRESULT;    
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.




















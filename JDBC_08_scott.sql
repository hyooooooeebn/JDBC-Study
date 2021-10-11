SELECT USER
FROM DUAL;

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	������	010-1111-1111
2	����ȣ	010-2222-2222
3	������	010-3333-3333
4	������	010-4444-4444
5	������	010-5555-5555
6	��ȿ��	010-6666-6666
7	�մ���	010-7777-7777
8	��ȿ��	010-8888-8888
9	�չ���	010-9999-9999
10	�չ���	010-1100-1100
11	�ּ���	010-1100-1100
12	ä����	010-1200-1200
*/

--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
-- ���ν��� �� : PRC_MEMBERINSERT
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    -- �ֿ� ���� ����
    VSID TBL_MEMBER.SID%TYPE;
    
BEGIN

    -- ���� SID�� �ִ밪 ������
    SELECT NVL(MAX(SID),0) INTO VSID
    FROM TBL_MEMBER;
    
    
    -- ������ �Է�
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VSID+1), VNAME, VTEL);
    
    -- Ŀ��
    COMMIT;    
    
END;
--==>> Procedure PRC_MEMBERINSERT��(��) �����ϵǾ����ϴ�.

--�� ������ ���ν��� �׽�Ʈ
EXEC PRC_MEMBERINSERT('���±�','010-3733-7202');
--==>> PL/SQL ���ν����� ���������� �Ϸ�Ǿ����ϴ�.

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	������	010-1111-1111
2	����ȣ	010-2222-2222
3	������	010-3333-3333
4	������	010-4444-4444
5	������	010-5555-5555
6	��ȿ��	010-6666-6666
7	�մ���	010-7777-7777
8	��ȿ��	010-8888-8888
9	�չ���	010-9999-9999
10	�չ���	010-1100-1100
11	�ּ���	010-1100-1100
12	ä����	010-1200-1200
13	���±�	010-3733-7202
*/


SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>>
/*
1	������	010-1111-1111
2	����ȣ	010-2222-2222
3	������	010-3333-3333
4	������	010-4444-4444
5	������	010-5555-5555
6	��ȿ��	010-6666-6666
7	�մ���	010-7777-7777
8	��ȿ��	010-8888-8888
9	�չ���	010-9999-9999
10	�չ���	010-1100-1100
11	�ּ���	010-1100-1100
12	ä����	010-1200-1200
13	���±�	010-3733-7202
14	�����	010-1234-1234
15	������	010-4569-4569
16	������	010-7894-7894
*/


--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
-- �� ��SYS_REFCURSOR�� �ڷ����� �̿��Ͽ� Ŀ�� �ٷ��
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
--==>> Procedure PRC_MEMBERSELECT��(��) �����ϵǾ����ϴ�.




















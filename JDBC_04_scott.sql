SELECT USER
FROM DUAL;
--==>> SCOTT

TRUNCATE TABLE TBL_SCORE;

DROP SEQUENCE SCORESEQ;


--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;

--�� ������ �غ�
-- 1. ������ �Է� ������ ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '�̴ٿ�', 90, 80, 70)
;
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

COMMIT;

-- 2. ����Ʈ ��� ������ ����(����, ���, ���� ����)
SELECT SID, NAME, KOR, ENG, MAT
    , (KOR + ENG + MAT) AS TOT
    , (KOR + ENG + MAT) / 3 AS AVG
    , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC;

--> ���ٱ���
SELECT SID, NAME, KOR, ENG, MAT , (KOR + ENG + MAT) AS TOT , (KOR + ENG + MAT) / 3 AS AVG , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;
--==>> 1	�̴ٿ�	90	80	70	240	80	1

-- 3. �ο� �� ��ȸ ������ ����
SELECT COUNT(*)  AS COUNT FROM TBL_SCORE
;

-- 4. �̸� �˻� ������ ����
SELECT *
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
        , (KOR + ENG + MAT) AS TOT
        , (KOR + ENG + MAT) / 3 AS AVG
        , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
    FROM TBL_SCORE
)
WHERE NAME = '�̴ٿ�';
--==>> �� �� ����
SELECT * FROM( SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = '�̴ٿ�'
;
--==>> 1	�̴ٿ�	90	80	70	240	80	1

-- 5. ��ȣ �˻� ������ ����

SELECT *
FROM
(
    SELECT SID, NAME, KOR, ENG, MAT
        , (KOR + ENG + MAT) AS TOT
        , (KOR + ENG + MAT) / 3 AS AVG
        , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
    FROM TBL_SCORE
)
WHERE SID = 1;
--> �� �� ����
SELECT * FROM( SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT, (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID = 1
;
--==>> 1	�̴ٿ�	90	80	70	240	80	1

-- 6. ������ ���� ������ ����
UPDATE TBL_SCORE
SET NAME = '������', KOR = 10, ENG = 20, MAT=30
WHERE SID =1;
--> �� �� ����
UPDATE TBL_SCORE SET NAME = '������', KOR = 10, ENG = 20, MAT=30 WHERE SID =1
;
--==>> 1 �� ��(��) ������Ʈ�Ǿ����ϴ�.

SELECT *
FROM TBL_SCORE;
--==>> 1	������	10	20	30

COMMIT;
--==>> Ŀ�� �Ϸ�.

-- 7. ������ ���� ������ ����
DELETE
FROM TBL_SCORE
WHERE SID = 1;
--> �� �� ���� 
DELETE FROM TBL_SCORE WHERE SID = 1
;
--==>> 1 �� ��(��) �����Ǿ����ϴ�.

ROLLBACK;
--==>> �ѹ� �Ϸ�.




    SELECT SID, NAME, KOR, ENG, MAT
        , (KOR + ENG + MAT) AS TOT
        , (KOR + ENG + MAT) / 3 AS AVG
        , RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK
    FROM TBL_SCORE
    WHERE SID = 1;














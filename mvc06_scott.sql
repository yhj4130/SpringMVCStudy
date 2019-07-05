SELECT USER
FROM DUAL;
--==>> SCOTT

DROP TABLE TBL_MEMBERLIST;
--==>> Table TBL_MEMBERLIST��(��) �����Ǿ����ϴ�.

CREATE TABLE TBL_MEMBERLIST
( ID    VARCHAR2(30)
, PW    VARCHAR2(20)
, NAME  VARCHAR2(50)
, TEL   VARCHAR2(50)
, EMAIL VARCHAR2(100)
, CONSTRAINT MEMBERLIST_ID_PF PRIMARY KEY(ID)
);
--==>> Table TBL_MEMBERLIST��(��) �����Ǿ����ϴ�.

--�� ������ �Է�
INSERT INTO TBL_MEMBERLIST(ID, PW, NAME, TEL, EMAIL) VALUES('admin', CRYPTPACK.ENCRYPT('java006$','java006$'), '�ѽ¿�', '010-4143-4045', 'han0401@naver.com');
--> �� �� ����
INSERT INTO TBL_MEMBERLIST(ID, PW, NAME, TEL, EMAIL) VALUES('admin', CRYPTPACK.ENCRYPT('java006$','java006$'), '�ѽ¿�', '010-4143-4045', 'han0401@naver.com')
;
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.


--�� Ȯ��
SELECT ID, NAME, TEL, EMAIL
FROM TBL_MEMBERLIST;


--�� Ŀ��
COMMIT;
--==>> Ŀ�� �Ϸ�.
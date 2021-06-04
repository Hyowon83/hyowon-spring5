--19장 사용자추가(CreateWorkSpace)시 오라클데스크탑(고전)X 대신 
-- 웹프로그램을 사용 (http://127.0.0.1:9000/apex/f?p=4950)
-- ,SQL플러스X, 

--15장 PK생성시 자동으로 2가 생성 NOT NULL(빈값방지), UNIQUE(NO중복)
-- 제약조건(constraint) 이 자동생성, Index(테이블)도 자동생성(검색시중요)
-- ERD로 게시판테이블-[댓글|첨부파일]Foreign KEY(외래키)부자관계생성

--14장 트랜잭션 DB단에서 사용하지 않고, 
-- 스프링단에서 트랜잭션을 사용 @Transactional 인터페이스를 사용
-- commit과rollback;(DML문:insert,update,delete)
-- rollback는 제일 마지막 커밋된 상태로 되돌립니다.

--12장 테이블 구조생성(create;),변경(alter;),삭제(drop;)
-- ERD 관계형 다이어그램으로 물리적 테이블 생성(포워드엔지니어링)
DROP TABLE tbl_member_del;
CREATE TABLE TBL_MEMBER_DEL
(
USER_ID VARCHAR(50) PRIMARY KEY
,USER_PW VARCHAR(255)
,USER_NAME VARCHAR(255)
,EMAIL VARCHAR(255)
,POINT NUMBER(11)
,ENABLED NUMBER(1)
,LEVELS VARCHAR(255)
,REG_DATE TIMESTAMP
,UPDATE_DATE TIMESTAMP
);
--ALTER 테이블로 필드명 변경(아래)
DESC tbl_member_del;
ALTER TABLE tbl_member_del RENAME COLUMN email TO user_email;
--DEPT테이블의 deptno 숫자2자리때문에 에러 -> 4자리 크기를 변경
DESC dept;--단, 작은자리 큰자리로 변경 문제없음.
ALTER TABLE dept MODIFY(deptno NUMBER(4));

--11장 서브쿼리
-- 단일행서브쿼리 필드값을 비교할때, 비교하는 값인 단일한(필드값)
-- 다중행서브쿼리 테이블값을 select쿼리로 생성(레코드값)

--10장 테이블 조인 2개의 테이블을 연결(조인)해서 결과를 구함
--댓글 갯수 구할 떄.
--카티시안프러덕트 조인(합집합-게시물10개,댓글100개 조인하면 110개~최대1000개)
--(인너)조인(교집합)을 제일 많이 사용.(인너조인만 실습,INNER JOIN 인너는 생략)
SELECT dept.dname, emp.* FROM emp, dept WHERE emp.deptno = dept.deptno
AND emp.ename = 'SCOTT' ; --오라클방식
SELECT d.dname, e.* FROM emp e JOIN dept d ON e.deptno = d.deptno
WHERE e.ename = 'SCOTT'; --표준쿼리방식
--조인과 그룹을 이용해서 댓글카운터를 출력하는 게시판 리스트 만들기.
SELECT bod.bno,title,count(*) as reply_count
,writer,bod.reg_date,view_count
FROM tbl_board BOD JOIN tbl_reply REP
ON BOD.bno = rep.bno 
GROUP BY bod.bno, title, writer,bod.reg_date,view_count
ORDER BY bod.bno;

--9장 패스(레포트용함수)

--8장 함수(count, upper, lower, to_char, round(반올림), concat...),그룹함수
--having은 group by의 조건문을 적습니다.
--평균 연봉이 2000이상인 부서의 번호와 평균급여 구하기(아래)
SELECT deptno, round(AVG(sal)) FROM emp
--WHERE AVG(sal) >= 2000; --검색조건(error)
GROUP BY deptno
HAVING AVG(sal) >= 2000; --그룹조건
--where가 아닌 having을 쓰는 이유: where뒤에 AVG를 쓰면 오류남.

--avg는 평균을 내는 함수니까 그룹을 정해야하니
--groupby를 설정해야되고
--groupby를 설정할 때 함수를 조건으로 걸려면 having을 사용해야하니(최서영)

--부서별 연봉의 합계를 구해서 급여가 제일 많이 지출되는 부서(아래)
--자바코딩에서는 소문자로 통일. 단 여기서는 눈에 잘 띄라고 대문자 사용.
SELECT * FROM(
SELECT deptno, SUM(sal) AS Dept_Sal FROM emp GROUP BY deptno) ORDER BY dept_sal DESC;
SELECT deptno, SUM(sal) FROM emp GROUP BY deptno ORDER BY SUM(sal) DESC; --서브쿼리 없이 사용하는 법
--라운드함수(반올림) 소수점기준. round(10.05,2) 소수점 2번째 반올림.
-- -1 1의자리 -2 10의자리 -3 백의자리....
SELECT ename, round(sal,-3) FROM emp; --레코드가 여러개
SELECT SUM(sal) FROM emp; --단일 레코드(그룹함수)
SELECT AVG(sal) FROM emp; --평균을 구해서 단일 레코드로.(그룹함수)
SELECT round(AVG(sal)) FROM emp; --평균 자리지정이 없다면 첫번째 소수점 반올림
SELECT MAX(sal), MIN(sal), MAX(sal)-MIN(sal) AS "대표와사원의연봉차" FROM emp;
SELECT COUNT(*) FROM emp WHERE sal >= (SELECT round(AVG(sal)) FROM emp); --평균연봉보다 많이 받는 사원 error.
--AVG함수를 where조건에 사용하지 못 할 때. *서브쿼리* 이용

--DDL문(Create; alter; drop;), DCL문(commit; rollback;)
--DML문(Data Manufacture Language) insert,update,delete
--insert문:테이블에 새로운 레코드(row) 추가
CREATE TABLE dept02 AS SELECT * FROM dept WHERE 1=0;
-- 위 쿼리를 실행하면 dept 테이블과 구조와 내용이 동일한 테이블이 생성 됨.
-- where 조건이 붙으면, 구조는 같으나 내용은 빈 테이블이 생김.
INSERT INTO dept02 (
--필드명
deptno, dname, loc
) VALUES(
--바인딩값
10, '개발부서', '천안'
);
INSERT INTO dept02 VALUES(20,'디자인부','경기도');
--DCL명령문 커밋필수
COMMIT; --데이터베이스쿼리 직접입력한 결과는 반드시 커밋을 해줘야지만 실제 저장이 됩니다.
-- 커밋을 하지 않으면, 여기만 보이고, 다른곳은 안보임.
SELECT * FROM dept02;
--DELETE는 레코드 1줄을 지우는 명령
DELETE FROM dept02; --이렇게 사용하면 안됨.
DELETE FROM dept02 WHERE deptno >= 0; --where 반드시 포함. 안그러면 모두 삭제.
--DROP TABLE 테이블명 은 테이블 자체를 물리적으로 없애는 명령
DROP TABLE dept02; --드롭 테이블 커밋없이 바로 적용됨.
CREATE TABLE emp01 AS SELECT * FROM emp; --테이블 복제 명령.
SELECT * FROM emp01;
--UPDATE 테이블명 SET 필드명 = '바꿀값' where(필수값) empno = '특정ID'
UPDATE emp01 SET ename = '장효원' WHERE empno = 7839;
ROLLBACK; --롤백은 마지막 커밋 바로전까지 되돌립니다.
UPDATE emp01 SET sal = sal*1.1;--모든직원의 연봉을 10%인상
UPDATE emp01 SET hiredate = sysdate;
--머지는 표준쿼리(ANSI쿼리)가 아니라 서 건너 뜀.

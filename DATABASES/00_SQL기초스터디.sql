insert into tbl_board (title,contents,writer) values ('테스트게시글','내용','admin');
select * from tbl_board;
select * from tbl_board order by bno desc;
select bno from tbl_board;
update tbl_board set contents='내용수정' where bno=18;
update tbl_board set title='타이틀수정' where bno=17;
update tbl_board set title='타이틀수정',contents='내용수정' where bno=16;
delete from tbl_board where bno=19;
select * from tbl_board where bno between 1 and 10;
select * from tbl_board where bno between 11 and 20;
select rownum, a.* from tbl_board a;
select rownum, a.* from tbl_board a where rownum between 1 and 10;
select rownum, a.* from tbl_board a where rownum between 11 and 20;

-- 한 줄 주석
/* 범위 주석 */
SELECT ROWNUM, B.* FROM TBL_BOARD B
WHERE ROWNUM <= 10;

SELECT ROWNUM, B.* FROM TBL_BOARD B
WHERE ROWNUM <= 20;

SELECT TBL_BOARD.* FROM TBL_BOARD ORDER BY BNO DESC;
SELECT /*+ INDEX_DESC(TBL_BOARD, PK_TBL_BOARD)*/TBL_BOARD.* FROM TBL_BOARD;


-- 게시판 페이징
SELECT N.* FROM (
    -- 인라인 뷰
    SELECT ROWNUM RN, B.* FROM TBL_BOARD B
    WHERE ROWNUM <= 20
) N
WHERE RN >10; -- WHERE RN BETWEEN 11 AND 20;

package com.ssssong.choonsik.board.dao;

import com.ssssong.choonsik.board.dto.BoardDTO;
import com.ssssong.choonsik.common.paging.SelectCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardMapperTest {


    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void 맵퍼_의존성_주입_테스트() {

        assertNotNull(boardMapper);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 보드맵퍼_게시글등록() {

        // given
        BoardDTO board = new BoardDTO();
        board.setBoardTitle("제목");
        board.setMemberId("admin");
        board.setBoardImageUrl("2f0316c99f1d4e5ab95cba0406982285.png");

        // when
        int result = boardMapper.registPost(board);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드맵퍼_게시글검색() {

        // given
        long boardCode = 3;

        // when
        BoardDTO board = boardMapper.selectPostByCode(boardCode);

        // then
        assertNotNull(board);
    }

    @Test
    void 보드맵퍼_게시글수정() {

        // given
        BoardDTO board = new BoardDTO();
        board.setBoardCode(3L);
        board.setBoardTitle("수정된 제목");
        board.setMemberId("admin");
        board.setBoardImageUrl("93e0b4506d314330baa525936cff36df.png");

        // when
        int result = boardMapper.updatePost(board);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드맵퍼_게시글삭제() {

        // given
        Long boardCode = 6L;

        // when
        int result = boardMapper.deletePost(boardCode);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드맵퍼_게시글전체갯수조회() {

        // given

        // when
        int result = boardMapper.selectBoardTotal();

        // then
        assertNotNull(result);
    }

    @Test
    void 보드맵퍼_게시글리스트페이징처리조회() {

        // given
        SelectCriteria criteria = new SelectCriteria();
        criteria.setStartRow(1);
        criteria.setLimit(6);

        // when
        List<BoardDTO> boardDTOList = boardMapper.selectBoardListWithPaging(criteria);

        // then
        assertNotNull(boardDTOList);
    }
}
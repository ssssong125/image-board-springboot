package com.ssssong.choonsik.board.service;

import com.ssssong.choonsik.board.dao.BoardMapper;
import com.ssssong.choonsik.board.dto.BoardDTO;
import com.ssssong.choonsik.common.paging.SelectCriteria;
import com.ssssong.util.FileUploadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardServiceTest {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceTest(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Test
    void 보드서비스_게시글등록() throws IOException {

        // given
        BoardDTO board = new BoardDTO();
        board.setBoardTitle("테스트글");
        board.setMemberId("admin");
        MockMultipartFile image = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("C:\\Users\\ASUS\\Downloads\\춘식이\\다운로드.jpg"));
        board.setBoardImage(image);

        // when
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;

        int result = 0;

        replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, board.getBoardImage());

        board.setBoardImageUrl(replaceFileName);

        result = boardMapper.registPost(board);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드서비스_게시글수정() throws IOException {

        // given
        BoardDTO board = new BoardDTO();
        board.setBoardCode(10L);
        board.setBoardTitle("수정된 테스트글");
        board.setMemberId("admin");
        MockMultipartFile image = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("C:\\Users\\ASUS\\Downloads\\춘식이\\E9s0ao1VEAM3uYi.jpg"));
        board.setBoardImage(image);

        // when
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;

        int result = 0;

        replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, board.getBoardImage());

        board.setBoardImageUrl(replaceFileName);

        result = boardMapper.updatePost(board);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드서비스_게시글삭제() {

        // given
        Long boardCode = 10L;

        // when
        int result = boardMapper.deletePost(boardCode);

        // then
        assertEquals(1, result);
    }

    @Test
    void 보드서비스_전체게시글_갯수_조회() {

        // given

        // when
        int result = boardMapper.selectBoardTotal();

        // then
        assertNotNull(result);
    }

    @Test
    void 보드서비스_페이징처리게시글리스트조회() {

        // given
        SelectCriteria criteria = new SelectCriteria();
        criteria.setStartRow(1);
        criteria.setLimit(6);

        // when
        List<BoardDTO> boardDTOList = boardMapper.selectBoardListWithPaging(criteria);

        // then
        assertNotNull(boardDTOList);
    }

    @Test
    void 보드서비스_게시글상세조회() {

        // given
        Long boardCode = 9L;

        // when
        BoardDTO boardDTO = boardMapper.selectPostByCode(boardCode);

        // then
        assertNotNull(boardDTO);
    }
}
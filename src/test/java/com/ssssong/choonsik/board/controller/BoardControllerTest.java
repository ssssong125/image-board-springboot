package com.ssssong.choonsik.board.controller;

import com.ssssong.choonsik.board.dto.BoardDTO;
import com.ssssong.choonsik.board.service.BoardService;
import com.ssssong.util.FileUploadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardControllerTest {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final BoardService boardService;

    @Autowired
    public BoardControllerTest(BoardService boardService) {

        this.boardService = boardService;
    }

    @Test
    void 보드컨트롤러_게시글등록() throws IOException {

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

        boolean result;

        replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, board.getBoardImage());

        board.setBoardImageUrl(replaceFileName);

        result = boardService.registPost(board);

        // then
        assertEquals(true, result);
    }

    @Test
    void 보드컨트롤러_게시글수정() throws IOException {

        // given
        BoardDTO board = new BoardDTO();
        board.setBoardCode(11L);
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

        String result;

        replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, board.getBoardImage());

        board.setBoardImageUrl(replaceFileName);

        result = boardService.updatePost(board);

        // then
        assertEquals("상품 업데이트 성공", result);
    }

    @Test
    void 보드컨트롤러_게시글삭제() {

        // given
        Long boardCode = 11L;

        // when
        boolean result = boardService.deletePost(boardCode);

        // then
        assertEquals(true, result);
    }

    @Test
    void 보드컨트롤러_페이징게시글리스트조회() {

        // given

        // when
        int result = boardService.selectBoardTotal();

        // then
        assertNotNull(result);
    }

    @Test
    void 보드컨트롤러_게시글상세조회() {

        // given
        Long boardCode = 9L;

        // when
        BoardDTO boardDTO = boardService.selectBoardDetail(boardCode);

        // then
        assertNotNull(boardDTO);
    }
}
package watch.movie.domain.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import watch.movie.base.StatusCode;
import watch.movie.domain.notice.dto.NoticeDto;
import watch.movie.domain.notice.dto.cond.NoticeSearchCond;
import watch.movie.domain.notice.service.NoticeService;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public List<NoticeDto> allNotice(@ModelAttribute NoticeSearchCond cond, Pageable pageable) {
        log.info("NoticeController.allNotice -> {}", "전체 공지사항 조회");

        return noticeService.findAll(cond, pageable);
    }

    @PostMapping("/notice")
    public StatusCode saveNotice(@RequestBody NoticeDto notice) {
        log.info("NoticeController.saveNotice -> {}", "공지사항 등록");

        noticeService.save(notice);
        return StatusCode.SUCCESS;
    }

    @GetMapping("/notice/{id}")
    public NoticeDto getNotice(@PathVariable("id") Long id) {
        log.info("NoticeController.getNotice -> {}", "공지사항 조회 : " + id);

        try {
            return noticeService.findById(id);
        } catch (NoSuchObjectException e) {
            return new NoticeDto(false);
        }
    }

    @PutMapping("/notice/{id}")
    public StatusCode updateNotice(@PathVariable("id") Long id, @RequestBody NoticeDto notice) {
        log.info("NoticeController.updateNotice -> {}", "공지사항 수정 : " + id);

        try {
            noticeService.updateNotice(id, notice);
        } catch (NoSuchObjectException e) {
            return StatusCode.BOARD_NOT_FOUND;
        }

        return StatusCode.SUCCESS;
    }

}

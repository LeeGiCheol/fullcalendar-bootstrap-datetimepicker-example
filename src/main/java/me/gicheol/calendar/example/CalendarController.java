package me.gicheol.calendar.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;


    /**
     * 화면 표출
     * @return View
     */
    @GetMapping
    public String view() {
        return "view";
    }


    /**
     * Calendar 전체 조회
     * @return Calendar All List
     */
    @PostMapping
    public ResponseEntity<List<CalendarForm>> calendarList() {
        List<CalendarForm> calendarList = calendarService.calendarList();
        return ResponseEntity.ok(calendarList);
    }


    /**
     * 더미 데이터 1개 생성
     * @return dummy data
     */
    @GetMapping("/create/dummy")
    public ResponseEntity<CalendarForm> create() {
        Calendar calendar = new Calendar();
        calendar.setTitle("TITLE");
        calendar.setStart(LocalDateTime.now().toString());
        CalendarForm save = calendarService.create(calendar);

        return ResponseEntity.ok(save);
    }


    /**
     * Calendar 추가, 수정 (종료 날짜 수정/전체 날짜 수정)
     * @param calendarForm Calendar 수정본
     * @return save calendar
     */
    @PostMapping({"/create", "/update/resize", "/update/drop"})
    public ResponseEntity<CalendarForm> calendarCreateOrUpdate(@RequestBody CalendarForm calendarForm) {
        CalendarForm save = calendarService.createOrUpdate(calendarForm);
        return ResponseEntity.ok(save);
    }


    /**
     * Calendar 삭제
     * @param calendarForm 삭제 할 Calendar
     * @return Delete 성공 메시지 / NoContent
     */
    @PostMapping("/delete")
    public ResponseEntity<?> calendarDelete(@RequestBody CalendarForm calendarForm) {
        int deleteCount = calendarService.calendarDelete(calendarForm);

        return deleteCount == 1 ?
                ResponseEntity.ok("DELETE") :
                ResponseEntity.noContent().build();
    }

}

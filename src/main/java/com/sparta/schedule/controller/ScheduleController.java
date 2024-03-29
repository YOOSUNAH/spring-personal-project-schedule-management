package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO schedule (title, contents, manager, date, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getTitle());
            preparedStatement.setString(2, schedule.getContents());
            preparedStatement.setString(3, schedule.getManager());
            preparedStatement.setString(4, schedule.getDate());
            preparedStatement.setString(5, schedule.getPassword());
            return preparedStatement;
        }, keyHolder);
        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules() {
        // DB 조회
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String manager = rs.getString("manager");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, title, contents, manager, date);
            }
        });
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getOneSchedules(@PathVariable Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";  // 해당하는 결과 한개만
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<ScheduleResponseDto>() { // 해당하는 결과 한개만
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String manager = rs.getString("manager");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, title, contents, manager, date);
            }
        });
    }

    @PutMapping("/schedules/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findById(id);

        if (schedule != null) {
            if (schedule.getPassword() != null) {
                if (schedule.getPassword().equals(requestDto.getPassword())) {
                     //일정 수정
                    String sql = "UPDATE schedule SET title = ?, contents = ?, manager = ?, date = ?, password = ? WHERE id = ?";
                    jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getDate(), requestDto.getPassword(), id);

                    return id;
                } else {
                    throw new IllegalArgumentException("비밀번호가 일차하지 않습니다.");
                }
            } else {
                throw new IllegalArgumentException("비밀번호가 없습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 날에는 일정이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/schedules/{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findById(id);
        if (schedule != null) {
            // 해당 일정 삭제하기
            String sql = "DELETE FROM schedule WHERE id = ? ";
            jdbcTemplate.update(sql, id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 날에는 일정이 존재하지 않습니다.");
        }
    }

    private Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContents(resultSet.getString("contents"));
                schedule.setManager(resultSet.getString("manager"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setDate(resultSet.getString("date"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
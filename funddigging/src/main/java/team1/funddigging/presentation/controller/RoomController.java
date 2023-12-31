package team1.funddigging.presentation.controller;


import team1.funddigging.application.dto.RoomDto;
import team1.funddigging.application.service.RoomService;
import team1.funddigging.domain.entity.Room;
//import team1.funddigging.presentation.request.ChangeFundingInfoRequest;
import team1.funddigging.presentation.request.AddRoomRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team1.funddigging.presentation.response.RoomInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    @PostMapping("/{user_id}/{category_id}")
    public ResponseEntity<Long> save(@RequestBody AddRoomRequest request, @PathVariable Long user_id, @PathVariable Long category_id) {
        Long savedId = roomService.addRoom(RoomDto.toAdd(request), user_id, category_id);
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/{room_id}")
    public ResponseEntity<RoomInfoResponse> getOneFunding(@PathVariable Long room_id) {

        Room room = roomService.getOneRoom(room_id);
        return ResponseEntity.ok(RoomInfoResponse.fromOneRoom(room));
    }

    @GetMapping("/room-list")
    public ResponseEntity<List<RoomInfoResponse>> getAllRoomList() {
        List<RoomDto> roomDtoList = roomService.getAllRoomList();
        List<RoomInfoResponse> response = roomDtoList.stream()
                .map(RoomInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}

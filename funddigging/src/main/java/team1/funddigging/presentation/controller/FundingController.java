package team1.funddigging.presentation.controller;

import team1.funddigging.application.dto.FundingDto;
import team1.funddigging.application.service.FundingService;
import team1.funddigging.domain.entity.Funding;
import team1.funddigging.presentation.request.AddFundingRequest;
//import team1.funddigging.presentation.request.ChangeFundingInfoRequest;
import team1.funddigging.presentation.response.FundingInfoResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room/{room_id}")
@RequiredArgsConstructor
public class FundingController {

    @Autowired
    private final FundingService fundingService;

    @PostMapping("/funding/{user_id}")
    public ResponseEntity<Long> save(@RequestBody AddFundingRequest request, @PathVariable Long room_id, @PathVariable Long user_id) {
        Long savedId = fundingService.addFunding(FundingDto.toAdd(request), room_id, user_id);
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/funding/{funding_id}")
    public ResponseEntity<FundingInfoResponse> getOneFunding(@PathVariable Long funding_id) {

        Funding funding = fundingService.getOneFunding(funding_id);
        return ResponseEntity.ok(FundingInfoResponse.fromOneFunding(funding));
    }



    @GetMapping("/funding-list")
    public ResponseEntity<List<FundingInfoResponse>> getAllFundingList() {
        List<FundingDto> roomDtoList = fundingService.getAllFundingList();
        List<FundingInfoResponse> response = roomDtoList.stream()
                .map(FundingInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}

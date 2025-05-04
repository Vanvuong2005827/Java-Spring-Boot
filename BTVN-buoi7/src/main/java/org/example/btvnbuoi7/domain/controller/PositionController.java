package org.example.btvnbuoi7.domain.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.base.RestApiV1;
import org.example.btvnbuoi7.domain.models.dtos.request.PositionRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.PositionResponse;
import org.example.btvnbuoi7.domain.services.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
@RequestMapping("/position")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionController {
    @Autowired
    PositionServiceImpl positionService;

    //--------------------------------getAllPosition-----------------------------------------
    //For instance: /?page=0&size=5&sort=name,desc
    @GetMapping("/")
    public ResponseEntity<List<PositionResponse>> getAll(
            @PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return new ResponseEntity<>(positionService.getAll(pageable), HttpStatus.OK);
    }

    //--------------------------------getPositionById-----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getById(@PathVariable long id) {
        return new ResponseEntity<>(positionService.getById(id),HttpStatus.OK);
    }

    //--------------------------------createPosition-----------------------------------------
    @PostMapping("/")
    public ResponseEntity<PositionResponse> createPosition(@Valid @RequestBody PositionRequest positionRequest) {
        return new ResponseEntity<>(positionService.create(positionRequest),HttpStatus.CREATED);
    }

    //--------------------------------updatePosition-----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<PositionResponse> updatePosition(@PathVariable long id, @Valid @RequestBody PositionRequest positionRequest) {
        return new ResponseEntity<>(positionService.update(id, positionRequest),HttpStatus.OK);
    }

    //--------------------------------deletePositionById-----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable long id) {
        positionService.delete(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

}

package org.example.btvnbuoi7.domain.services;

import org.example.btvnbuoi7.domain.models.dtos.request.PositionRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.PositionResponse;
import org.example.btvnbuoi7.domain.models.entities.Position;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPositionService {
    List<PositionResponse> getAll(Pageable pageable);
    PositionResponse getById(@Exists(entity = Position.class) Long id);
    PositionResponse create(PositionRequest positionRequest);
    PositionResponse update(@Exists(entity = Position.class) Long id, PositionRequest positionRequest);
    void delete(@Exists(entity = Position.class) Long id);
}

package org.example.btvnbuoi7.domain.models.dtos.mapper;


import org.example.btvnbuoi7.domain.models.dtos.request.PositionRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.PositionResponse;
import org.example.btvnbuoi7.domain.models.entities.Position;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position RequestToPosition(PositionRequest positionRequest);
    PositionResponse PositionToResponse(Position position);
    List<PositionResponse> PositionToResponseList(List<Position> positions);
    void updatePosition(PositionRequest positionRequest, @MappingTarget Position position);
}

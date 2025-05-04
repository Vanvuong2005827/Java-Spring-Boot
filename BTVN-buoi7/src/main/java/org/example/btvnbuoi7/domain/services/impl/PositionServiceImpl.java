package org.example.btvnbuoi7.domain.services.impl;

import org.example.btvnbuoi7.domain.exception.extendedExceptions.ResourceNotFoundException;
import org.example.btvnbuoi7.domain.models.dtos.mapper.PositionMapper;
import org.example.btvnbuoi7.domain.models.dtos.request.PositionRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.PositionResponse;
import org.example.btvnbuoi7.domain.models.entities.Position;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.example.btvnbuoi7.domain.repository.PositionRepository;
import org.example.btvnbuoi7.domain.services.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Service
@Validated
public class PositionServiceImpl implements IPositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    PositionMapper positionMapper;

    public List<PositionResponse> getAll(Pageable pageable) {
        List<Position> positions = positionRepository.findAll(pageable).getContent();
        if (positions.isEmpty()) {
            throw new ResourceNotFoundException("No positions found");
        }
        return positionMapper.PositionToResponseList(positions);
    }

    @Override
    public PositionResponse getById(@Exists(entity = Position.class) Long id) {
        Position position = positionRepository.getById(id);
        return positionMapper.PositionToResponse(position);
    }

    @Override
    public PositionResponse create(PositionRequest positionRequest) {
        Position position = positionMapper.RequestToPosition(positionRequest);
        if (positionRepository.existsByNameAndIdNot(positionRequest.getName(), 0)) {
            throw new DataIntegrityViolationException("Position with name " + position.getName() + " already exists");
        }
        position = positionRepository.save(position);
        return positionMapper.PositionToResponse(position);
    }

    @Override
    public PositionResponse update(@Exists(entity = Position.class) Long id, PositionRequest positionRequest) {
        Position position = positionRepository.getById(id);
        if (positionRepository.existsByNameAndIdNot(positionRequest.getName(), id)) {
            throw new DataIntegrityViolationException("Position with name " + position.getName() + " already exists");
        }
        positionMapper.updatePosition(positionRequest, position);
        position = positionRepository.save(position);
        return positionMapper.PositionToResponse(position);
    }

    @Override
    public void delete(@Exists(entity = Position.class) Long id) {
        positionRepository.deleteById(id);
    }
}

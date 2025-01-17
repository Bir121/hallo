package com.sunknowledge.dme.rcm.service.impl;

import com.sunknowledge.dme.rcm.domain.ItemLocation;
import com.sunknowledge.dme.rcm.repository.ItemLocationRepository;
import com.sunknowledge.dme.rcm.service.ItemLocationService;
import com.sunknowledge.dme.rcm.service.dto.ItemLocationDTO;
import com.sunknowledge.dme.rcm.service.mapper.ItemLocationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ItemLocation}.
 */
@Service
@Transactional
public class ItemLocationServiceImpl implements ItemLocationService {

    private final Logger log = LoggerFactory.getLogger(ItemLocationServiceImpl.class);

    private final ItemLocationRepository itemLocationRepository;

    private final ItemLocationMapper itemLocationMapper;

    public ItemLocationServiceImpl(ItemLocationRepository itemLocationRepository, ItemLocationMapper itemLocationMapper) {
        this.itemLocationRepository = itemLocationRepository;
        this.itemLocationMapper = itemLocationMapper;
    }

    @Override
    public ItemLocationDTO save(ItemLocationDTO itemLocationDTO) {
        log.debug("Request to save ItemLocation : {}", itemLocationDTO);
        ItemLocation itemLocation = itemLocationMapper.toEntity(itemLocationDTO);
        itemLocation = itemLocationRepository.save(itemLocation);
        return itemLocationMapper.toDto(itemLocation);
    }

    @Override
    public ItemLocationDTO update(ItemLocationDTO itemLocationDTO) {
        log.debug("Request to update ItemLocation : {}", itemLocationDTO);
        ItemLocation itemLocation = itemLocationMapper.toEntity(itemLocationDTO);
        itemLocation = itemLocationRepository.save(itemLocation);
        return itemLocationMapper.toDto(itemLocation);
    }

    @Override
    public Optional<ItemLocationDTO> partialUpdate(ItemLocationDTO itemLocationDTO) {
        log.debug("Request to partially update ItemLocation : {}", itemLocationDTO);

        return itemLocationRepository
            .findById(itemLocationDTO.getItemLocationId())
            .map(existingItemLocation -> {
                itemLocationMapper.partialUpdate(existingItemLocation, itemLocationDTO);

                return existingItemLocation;
            })
            .map(itemLocationRepository::save)
            .map(itemLocationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ItemLocations");
        return itemLocationRepository.findAll(pageable).map(itemLocationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItemLocationDTO> findOne(Long id) {
        log.debug("Request to get ItemLocation : {}", id);
        return itemLocationRepository.findById(id).map(itemLocationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemLocation : {}", id);
        itemLocationRepository.deleteById(id);
    }
}

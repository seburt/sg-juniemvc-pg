package guru.springframework.juniemvc.services;

import guru.springframework.juniemvc.entities.Apparel;
import guru.springframework.juniemvc.entities.ApparelOrder;
import guru.springframework.juniemvc.entities.ApparelOrderLine;
import guru.springframework.juniemvc.mappers.ApparelOrderLineMapper;
import guru.springframework.juniemvc.mappers.ApparelOrderMapper;
import guru.springframework.juniemvc.models.ApparelOrderDto;
import guru.springframework.juniemvc.models.ApparelOrderLineDto;
import guru.springframework.juniemvc.repositories.ApparelOrderRepository;
import guru.springframework.juniemvc.repositories.ApparelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of ApparelOrderService that uses ApparelOrderRepository for persistence
 */
@Service
public class ApparelOrderServiceImpl implements ApparelOrderService {

    private final ApparelOrderRepository apparelOrderRepository;
    private final ApparelRepository apparelRepository;
    private final ApparelOrderMapper apparelOrderMapper;
    private final ApparelOrderLineMapper apparelOrderLineMapper;

    public ApparelOrderServiceImpl(ApparelOrderRepository apparelOrderRepository,
                               ApparelRepository apparelRepository,
                               ApparelOrderMapper apparelOrderMapper,
                               ApparelOrderLineMapper apparelOrderLineMapper) {
        this.apparelOrderRepository = apparelOrderRepository;
        this.apparelRepository = apparelRepository;
        this.apparelOrderMapper = apparelOrderMapper;
        this.apparelOrderLineMapper = apparelOrderLineMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApparelOrderDto> getAllApparelOrders() {
        return apparelOrderRepository.findAll().stream()
                .map(apparelOrderMapper::apparelOrderToApparelOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApparelOrderDto> getApparelOrderById(Integer id) {
        return apparelOrderRepository.findById(id)
                .map(apparelOrderMapper::apparelOrderToApparelOrderDto);
    }

    @Override
    @Transactional
    public ApparelOrderDto saveApparelOrder(ApparelOrderDto apparelOrderDto) {
        ApparelOrder apparelOrder = apparelOrderMapper.apparelOrderDtoToApparelOrder(apparelOrderDto);
        
        // Process apparel order lines
        if (apparelOrderDto.getApparelOrderLines() != null) {
            apparelOrderDto.getApparelOrderLines().forEach(lineDto -> {
                // Create a new apparel order line
                ApparelOrderLine line = apparelOrderLineMapper.apparelOrderLineDtoToApparelOrderLine(lineDto);
                
                // Find and set the apparel reference
                if (lineDto.getApparelId() != null) {
                    Optional<Apparel> apparelOptional = apparelRepository.findById(lineDto.getApparelId());
                    apparelOptional.ifPresent(line::setApparel);
                }
                
                // Add the line to the order
                apparelOrder.addApparelOrderLine(line);
            });
        }
        
        ApparelOrder savedApparelOrder = apparelOrderRepository.save(apparelOrder);
        return apparelOrderMapper.apparelOrderToApparelOrderDto(savedApparelOrder);
    }

    @Override
    @Transactional
    public void deleteApparelOrderById(Integer id) {
        apparelOrderRepository.deleteById(id);
    }
}
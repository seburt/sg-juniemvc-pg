package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.ApparelOrder;
import guru.springframework.juniemvc.entities.ApparelOrderLine;
import guru.springframework.juniemvc.entities.ApparelOrderShipment;
import guru.springframework.juniemvc.models.ApparelOrderDto;
import guru.springframework.juniemvc.models.ApparelOrderLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for ApparelOrder entity and ApparelOrderDto
 */
@Mapper(uses = {ApparelOrderLineMapper.class, CustomerMapper.class, ApparelOrderShipmentMapper.class})
public interface ApparelOrderMapper {

    ApparelOrderDto apparelOrderToApparelOrderDto(ApparelOrder apparelOrder);

    @Mapping(target = "apparelOrderLines", ignore = true)
    @Mapping(target = "shipments", ignore = true)
    ApparelOrder apparelOrderDtoToApparelOrder(ApparelOrderDto apparelOrderDto);

    /**
     * Add apparel order lines to apparel order
     * @param apparelOrder the apparel order
     * @param apparelOrderDto the apparel order DTO
     * @param apparelOrderLineMapper the apparel order line mapper
     * @return the updated apparel order
     */
    default ApparelOrder addApparelOrderLines(ApparelOrder apparelOrder, ApparelOrderDto apparelOrderDto, ApparelOrderLineMapper apparelOrderLineMapper) {
        if (apparelOrderDto.getApparelOrderLines() != null && !apparelOrderDto.getApparelOrderLines().isEmpty()) {
            apparelOrderDto.getApparelOrderLines().forEach(lineDto -> {
                ApparelOrderLine line = apparelOrderLineMapper.apparelOrderLineDtoToApparelOrderLine(lineDto);
                apparelOrder.addApparelOrderLine(line);
            });
        }
        return apparelOrder;
    }

    /**
     * Add shipments to apparel order
     * @param apparelOrder the apparel order
     * @param apparelOrderDto the apparel order DTO
     * @param apparelOrderShipmentMapper the apparel order shipment mapper
     * @return the updated apparel order
     */
    default ApparelOrder addShipments(ApparelOrder apparelOrder, ApparelOrderDto apparelOrderDto, ApparelOrderShipmentMapper apparelOrderShipmentMapper) {
        if (apparelOrderDto.getShipments() != null && !apparelOrderDto.getShipments().isEmpty()) {
            apparelOrderDto.getShipments().forEach(shipmentDto -> {
                ApparelOrderShipment shipment = apparelOrderShipmentMapper.apparelOrderShipmentDtoToApparelOrderShipment(shipmentDto);
                apparelOrder.addShipment(shipment);
            });
        }
        return apparelOrder;
    }
}

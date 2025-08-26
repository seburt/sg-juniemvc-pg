package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.ApparelOrderShipment;
import guru.springframework.juniemvc.models.ApparelOrderShipmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for ApparelOrderShipment entity and ApparelOrderShipmentDto
 */
@Mapper
public interface ApparelOrderShipmentMapper {

    ApparelOrderShipmentDto apparelOrderShipmentToApparelOrderShipmentDto(ApparelOrderShipment apparelOrderShipment);

    @Mapping(target = "apparelOrder", ignore = true)
    ApparelOrderShipment apparelOrderShipmentDtoToApparelOrderShipment(ApparelOrderShipmentDto apparelOrderShipmentDto);
}
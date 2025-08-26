package guru.springframework.juniemvc.mappers;

import guru.springframework.juniemvc.entities.ApparelOrderLine;
import guru.springframework.juniemvc.models.ApparelOrderLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for ApparelOrderLine entity and ApparelOrderLineDto
 */
@Mapper
public interface ApparelOrderLineMapper {
    
    @Mapping(target = "apparelId", source = "apparel.id")
    @Mapping(target = "apparelName", source = "apparel.apparelName")
    @Mapping(target = "apparelStyle", source = "apparel.apparelStyle")
    @Mapping(target = "upc", source = "apparel.upc")
    ApparelOrderLineDto apparelOrderLineToApparelOrderLineDto(ApparelOrderLine apparelOrderLine);
    
    @Mapping(target = "apparel", ignore = true)
    @Mapping(target = "apparelOrder", ignore = true)
    ApparelOrderLine apparelOrderLineDtoToApparelOrderLine(ApparelOrderLineDto apparelOrderLineDto);
}
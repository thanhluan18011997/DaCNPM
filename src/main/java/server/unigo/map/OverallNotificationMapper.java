package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.model.OverallNotifications;

@Mapper
public interface OverallNotificationMapper {
    OverallNotifications mapDTOtoEntity(OverallNotificationsDTO overallNotificationsDTO);
    OverallNotificationsDTO mapEntityToDTo(OverallNotifications overallNotifications);
}

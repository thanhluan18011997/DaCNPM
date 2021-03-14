package server.unigo.Map;

import org.mapstruct.Mapper;
import server.unigo.dto.NotificationsDTO;
import server.unigo.dto.OverallNotificationsDTO;
import server.unigo.model.Notifications;
import server.unigo.model.OverallNotifications;

@Mapper
public interface OverallNotificationMapper {
    OverallNotifications mapDTOtoEntity(OverallNotificationsDTO overallNotificationsDTO);
    OverallNotificationsDTO mapEntityToDTo(OverallNotifications overallNotifications);
}

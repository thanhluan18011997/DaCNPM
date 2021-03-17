package server.unigo.map;

import org.mapstruct.Mapper;
import server.unigo.dto.NotificationsDTO;
import server.unigo.model.Notifications;

@Mapper
public interface NotificationMapper {
    Notifications mapDTOtoEntity(NotificationsDTO   notificationsDTO);
    NotificationsDTO mapEntityToDTo(Notifications notifications);
}

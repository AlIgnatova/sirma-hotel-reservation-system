import models.RoomType;
import utils.RoomTypeLoader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<RoomType> roomTypes = RoomTypeLoader.loadRoomTypes("com.sirma.hotel-reservation-system/data/roomTypes.json");

        for (RoomType roomType : roomTypes) {
            roomType.printRoomTypeDetails();
            System.out.println();
        }
    }
}

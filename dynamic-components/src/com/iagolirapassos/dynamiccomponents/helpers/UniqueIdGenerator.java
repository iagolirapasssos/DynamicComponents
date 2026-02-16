package io.dynamiccomponents.helpers;

import java.util.UUID;

public class UniqueIdGenerator {
    public static int generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        int uniqueId = (int) mostSignificantBits;
        return uniqueId;
    }
}

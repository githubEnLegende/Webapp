package org.oxyl.controller;

public enum SecureOrder {

    ID("id"),
    FIRST_NAME("first_name"),
    ARRIVAL("arrival"),
    FORMATION_OVER("formation_over"),
    PROMOTION_ID("promotion_id");

    private final String name;

    SecureOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String inEnum(String order) {
        for (SecureOrder secureOrder : SecureOrder.values()) {
            if (secureOrder.getName().equals(order)) {
                return secureOrder.getName();
            }
        }
        return SecureOrder.ID.getName();
    }
}

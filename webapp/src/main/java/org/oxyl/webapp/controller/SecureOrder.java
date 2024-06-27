package org.oxyl.webapp.controller;

public enum SecureOrder {

    ID("id"),
    FIRST_NAME("firstName"),
    ARRIVAL("arrival"),
    FORMATION_OVER("formationOver"),
    PROMOTION_ID("promotion");

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

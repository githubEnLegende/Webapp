package org.oxyl.bindings.dto.logindto;

import java.util.List;

public record UserDataDTO(String username, List<String> authorities) { }

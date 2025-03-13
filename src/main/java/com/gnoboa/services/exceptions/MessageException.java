package com.gnoboa.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageException {

    USER_NOT_FOUND("Usuario no encontrado"), PRODUCT_NOT_FOUND("Producto no encontrado"), PRESENTATION_DETAIL_NOT_FOUND("Detalle de presentación no encontrado"), INSUFFICIENT_CREDIT("Crédito insuficiente"), ORDER_NOT_FOUND("Orden no encontrada"), TOKEN_NOT_FOUND("Token no encontrado"), ORDER_BILLED("Orden ya facturada"), ORDER_DELETED("Orden eliminada");
    private final String message;
}
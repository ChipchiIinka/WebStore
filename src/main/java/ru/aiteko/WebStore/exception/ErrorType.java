package ru.aiteko.WebStore.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    COMMON_ERROR("Ошибка бизнесс логики", "Повторите запрос позже"),
    AUTH_ERROR("Ошибка авторизации", "Повторите авторизироваться заново"),
    NOT_FOUND("Не удалось найти ресурс", "По вашему запросу ресурс не найден"),
    CLIENT_ERROR("Ошибка в запросе", "Проверьте параметры и повторите запрос");

    private final String title;
    private final String text;
}

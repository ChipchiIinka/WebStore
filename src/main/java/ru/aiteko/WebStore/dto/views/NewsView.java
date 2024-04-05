package ru.aiteko.WebStore.dto.views;

public class NewsView {

    //Короткая информация о акции или новости
    public interface ShortInfo {}

    //Полная информация о акции или новости
    public interface FullInfo extends NewsView.ShortInfo {}
}

package ru.aiteko.WebStore.entity.views;

public class NewsView {
    //Короткая информация о акции или новости
    public interface ShortInfo {}

    //Полняа информация о акции или новости
    public interface FullInfo extends NewsView.ShortInfo {}
}

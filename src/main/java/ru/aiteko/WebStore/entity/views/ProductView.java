package ru.aiteko.WebStore.entity.views;

public class ProductView {
    //Короткая информация о продукте
    public interface ShortInfo {}
    //Среднее количество информации о продукте(Например при открытии описания продукта ещё в каталоге)
    public interface AverageInfo extends ShortInfo {}
    //Полная информация о продукте
    public interface FullInfo extends AverageInfo {}
}

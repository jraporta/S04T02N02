package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.model.Fruit;

import java.util.List;

public interface FruitService {
    Fruit addFruit(Fruit fruit);

    List<Fruit> getAllFruits();

    Fruit getFruitById(Long id);

    void deleteFruitById(Long id);

    Fruit updateFruit(Fruit fruit);
}

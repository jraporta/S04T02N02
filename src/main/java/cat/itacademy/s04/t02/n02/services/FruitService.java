package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exception.DataIntegrityViolationException;
import cat.itacademy.s04.t02.n02.exception.EntityNotFoundException;
import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Fruit addFruit(Fruit fruit){
        Optional<Fruit> fruitOptional = fruitRepository.findByName(fruit.getName());
        if (fruitOptional.isPresent()) throw new DataIntegrityViolationException("Fruit already exists");
        return fruitRepository.save(fruit);
    }

    public List<Fruit> getAllFruits(){
        return fruitRepository.findAll();
    }

    public Fruit getFruitById(Long id){
        Optional<Fruit> fruitOptional = fruitRepository.findById(id);
        return fruitOptional.orElseThrow(() -> new EntityNotFoundException("No fruit found with id "+ id));
    }

    public void deleteFruitById(Long id){
        Optional<Fruit> fruit = fruitRepository.findById(id);
        fruit.orElseThrow(() -> new EntityNotFoundException("No fruit found with id "+ id));
        fruitRepository.deleteById(id);
    }

    public Fruit updateFruit(Fruit fruit){
        Optional<Fruit> fruitOptional = fruitRepository.findByName(fruit.getName());
        Fruit oldFruit = fruitOptional.orElseThrow(
                () -> new EntityNotFoundException("No fruit found with name "+ fruit.getName()));
        fruit.setId(oldFruit.getId());
        return fruitRepository.save(fruit);
    }


}

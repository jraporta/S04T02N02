package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.exception.EntityAlreadyExistsException;
import cat.itacademy.s04.t02.n02.exception.EntityNotFoundException;
import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public Fruit addFruit(Fruit fruit){
        if (fruitRepository.existsByName(fruit.getName())) {
            throw new EntityAlreadyExistsException("Fruit already exists");
        }
        return fruitRepository.save(fruit);
    }

    @Override
    public List<Fruit> getAllFruits(){
        return fruitRepository.findAll();
    }

    @Override
    public Fruit getFruitById(Long id){
        return fruitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No fruit found with id "+ id));
    }

    @Override
    public void deleteFruitById(Long id){
        fruitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No fruit found with id "+ id));
        fruitRepository.deleteById(id);
    }

    @Override
    public Fruit updateFruit(Fruit fruit){
        Fruit oldFruit = fruitRepository.findByName(fruit.getName()).orElseThrow(
                () -> new EntityNotFoundException("No fruit found with name "+ fruit.getName()));
        fruit.setId(oldFruit.getId());
        return fruitRepository.save(fruit);
    }


}

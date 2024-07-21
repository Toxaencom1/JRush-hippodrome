import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    /* Шаблон отступов
        // Arrange (Подготовка)

        // Act (Выполнение)

        // Assert
    */

    Hippodrome hippodrome;
    List<Horse> horses;

    @Test
    @DisplayName("Тест получения списка лошадей")
    void getHorses() {
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse("Thunder", 22.5, 0.0));
        horseList.add(new Horse("Storm", 24.3, 0.5));
        horseList.add(new Horse("Lightning", 25.6, 1.0));
        horseList.add(new Horse("Shadow", 23.1, 1.5));
        horseList.add(new Horse("Spirit", 26.7, 2.0));
        horseList.add(new Horse("Blaze", 27.2, 2.5));
        horseList.add(new Horse("Majestic", 21.8, 3.0));
        horseList.add(new Horse("Thunderbolt", 28.4, 3.5));
        horseList.add(new Horse("Comet", 24.9, 4.0));
        horseList.add(new Horse("Ace", 25.0, 4.5));
        horseList.add(new Horse("Midnight", 23.7, 5.0));
        horseList.add(new Horse("Star", 27.9, 5.5));
        horseList.add(new Horse("Dancer", 22.2, 6.0));
        horseList.add(new Horse("Glimmer", 25.3, 6.5));
        horseList.add(new Horse("Ranger", 26.0, 7.0));
        horseList.add(new Horse("Echo", 21.5, 7.5));
        horseList.add(new Horse("Mystic", 23.6, 8.0));
        horseList.add(new Horse("Sable", 27.5, 8.5));
        horseList.add(new Horse("Apollo", 24.1, 9.0));
        horseList.add(new Horse("Luna", 26.4, 9.5));
        horseList.add(new Horse("Zephyr", 25.7, 10.0));
        horseList.add(new Horse("Tempest", 23.4, 10.5));
        horseList.add(new Horse("Flash", 27.8, 11.0));
        horseList.add(new Horse("Whisper", 22.9, 11.5));
        horseList.add(new Horse("Noble", 24.6, 12.0));
        horseList.add(new Horse("Valor", 26.1, 12.5));
        horseList.add(new Horse("Ember", 21.3, 13.0));
        horseList.add(new Horse("Blizzard", 25.8, 13.5));
        horseList.add(new Horse("Eclipse", 23.0, 14.0));
        horseList.add(new Horse("Phoenix", 28.0, 14.5));

        hippodrome = new Hippodrome(horseList);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertEquals(horseList, returnedHorses);
    }

    @Test
    @DisplayName("Тест на выброс IllegalArgumentException при пустом списке")
    void emptyHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Тест на выброс IllegalArgumentException при null значении")
    void nullHorses() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Тест на вызов метода move() у всех лошадей")
    void move() {
        List<Horse> mockedHorses =  new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockedHorses.add(Mockito.mock(Horse.class));
        }

        hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();

        for (Horse horse : mockedHorses) {
            verify(horse,times(1)).move();
        }
    }

    @Test
    @DisplayName("Тест, что метод возвращает лошадь с самым большим значением distance.")
    void getWinner() {
        horses = new ArrayList<>();
        horses.add(new Horse("Thunder", 22.5, 0.0));
        horses.add(new Horse("Storm", 24.3, 0.5));
        horses.add(new Horse("Lightning", 25.6, 1.0));
        hippodrome = new Hippodrome(horses);

        double expected = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .orElse(horses.get(2)).getDistance();
        double winner = hippodrome.getWinner().getDistance();

        assertEquals(expected,winner);
    }
}
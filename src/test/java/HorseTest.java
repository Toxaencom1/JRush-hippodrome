import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;


class HorseTest {

        /* Шаблон отступов
        // @BeforeEach
        // Act (Выполнение)

        // Assert
    */

    Horse horse;

    @BeforeEach
    void setUp() {
        horse = new Horse("horse",1.0,1.0);
    }

    @Test
    @DisplayName("Тест на null первого аргумента")
    void testNullName(){
        IllegalArgumentException iAException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null,1.0,1.0));
        assertEquals("Name cannot be null.", iAException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "   ","      " })
    @DisplayName("Тест, что при передаче в конструктор первым параметром пустой строки или строки содержащей " +
            "только пробельные символы (пробел, табуляция и т.д.)")
    void testEmptyAndOtherIncorrectName(String arguments){
        IllegalArgumentException iAException = assertThrows(IllegalArgumentException.class,
                () -> new Horse(arguments,1.0,1.0));
        assertEquals("Name cannot be blank.", iAException.getMessage());
    }

    @Test
    @DisplayName("Тест на отрицательную скорость")
    void testNegativeSpeed(){
        IllegalArgumentException iAException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("horse",-1.0,1.0));
        assertEquals("Speed cannot be negative.", iAException.getMessage());
    }

    @Test
    @DisplayName("Тест на отрицательную дистанцию")
    void testNegativeDistance(){
        IllegalArgumentException iAException = assertThrows(IllegalArgumentException.class,
                () -> new Horse("horse",1.0,-1.0));
        assertEquals("Distance cannot be negative.", iAException.getMessage());
    }

    @Test
    @DisplayName("Тест на получение имени")
    void getName() {
        String actualName = horse.getName();

        assertEquals("horse", actualName);
    }

    @Test
    @DisplayName("Тест на получение скорости")
    void getSpeed() {
        double actualSpeed = horse.getSpeed();

        assertEquals(1.0,actualSpeed,0.001);
    }

    @Test
    @DisplayName("Тест на получение дистанции")
    void getDistance() {
        double actualDistance = horse.getDistance();

        assertEquals(1.0,actualDistance,0.001);
    }

    @Test
    @DisplayName("Тест на получение дистанции если в конструкторе только 2 параметра")
    void getZeroDistance() {
        // Arrange (Подготовка)
        Horse horseTwoParameters = new Horse("horse",1.0);

        double actualDistance = horseTwoParameters.getDistance();

        assertEquals(0,actualDistance,0.001);
    }

    @Test
    @DisplayName("Тест, что вызывается метод move() с параметрами getRandomDouble 0.2 и 0.9")
    void move() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {

            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
            assertEquals(1.5, horse.getDistance(),0.001);
        }
    }
}
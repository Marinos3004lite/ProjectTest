import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthTest {

    Health health = new Health();
    int hp = health.hp;
    int testHp = 300;
    boolean dead = health.dead;
    //int number = 3;

    @Test
    void takeDamage() {
        System.out.println("@Test Health: " + hp + " = " + testHp);
        Assertions.assertEquals(hp, testHp);
    }

    @Test
    void giveDamage() {
    }

    @Test
    void isDead()
    {
        System.out.println("@Test isDead: " + dead);
        Assertions.assertEquals(false, dead);
    }
}
package edu.hm.hafner.assignment;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 *  Tests the class {@link UniqueIntegers}.
 *
 * @author Nicole Romero
 */
class UniqueIntegersTest {
    /** Verifies that the array is empty in the beginning. */
    @Test
    void shouldCreateEmptyIntegersLong() {
        //Given
        UniqueIntegers uniqueIntegers = new UniqueIntegers();
        //When
        boolean actual = uniqueIntegers.isEmpty();
        //Then
        assertThat(actual).as("Array is not empty at the beginning!").isTrue();
    }


    @Test
    void shouldCreateEmptyIntegers() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers();

        assertThat(uniqueIntegers.isEmpty()).isTrue();
        assertThat(uniqueIntegers.size()).isEqualTo(0);
        assertThat(uniqueIntegers.getValues()).isEmpty();
    }

    @Test
    void shouldCreateUniqueIntegersWithPredefinedValues() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2);

        assertThat(uniqueIntegers.isEmpty()).isFalse();
        assertThat(uniqueIntegers.size()).isEqualTo(2);
        assertThat(uniqueIntegers.getValues()).containsExactly(1, 2);
    }

    @Test
    void shouldCreateUniqueIntegersWithMorePredefinedValues() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5);

        assertThat(uniqueIntegers.isEmpty()).isFalse();
        assertThat(uniqueIntegers.size()).isEqualTo(5);
        assertThat(uniqueIntegers.getValues()).containsExactly(1, 2, 3, 4, 5);
        assertThat(new Integers(5, 4, 3, 2, 1).getValues()).containsExactly(5, 4, 3, 2, 1);
    }

    @Test
    void shouldCreateUniqueIntegersWith7PredefinedValues() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5, 6, 7);

        assertThat(uniqueIntegers.isEmpty()).isFalse();
        assertThat(uniqueIntegers.size()).isEqualTo(7);
        assertThat(uniqueIntegers.getValues()).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void shouldAppendAfter() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5, 6, 7);
        assertThat(uniqueIntegers.append(new UniqueIntegers(8, 9, 10)))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    void shouldAppendAfterAdditional() {
        assertThat(new UniqueIntegers(3).append(new UniqueIntegers(7)))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers(3, 7));
        assertThat(new UniqueIntegers().append(new UniqueIntegers(9)))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers(9));
        assertThat(new UniqueIntegers(5, 6).append(new UniqueIntegers()))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers(5, 6));
        assertThat(new UniqueIntegers().append(new UniqueIntegers()))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers());
        assertThat(new UniqueIntegers(1, 2).append(new UniqueIntegers(2)))
                .usingRecursiveComparison()
                .isEqualTo(new UniqueIntegers(1, 2));

    }

    @Test
    void shouldAppendElement() {
        assertThat(new UniqueIntegers().append().getValues()).containsExactly();
        assertThat(new UniqueIntegers(1).append(2).getValues()).containsExactly(1, 2);
        assertThat(new UniqueIntegers(1, 2).append(2).getValues()).containsExactly(1, 2);
    }

    @Test
    void shouldRemoveValue() {
        assertThat(removeValue()).isEmpty();
        assertThat(new UniqueIntegers().remove().getValues()).containsExactly();
        assertThat(new UniqueIntegers(1).remove().getValues()).containsExactly(1);
        assertThat(new UniqueIntegers(1).remove(2).getValues()).containsExactly(1);
        assertThat(new UniqueIntegers(1, 2).remove(2).getValues()).containsExactly(1);
        assertThat(new UniqueIntegers(1, 2, 3, 4, 5).remove(3).getValues()).containsExactly(1, 2, 4, 5);
        assertThat(new UniqueIntegers(1, 3, 5, 7, 8, 9).remove(3, 5).getValues()).containsExactly(1, 7, 8, 9);
    }

    @Test
    void shouldRemoveImmutable() {
        UniqueIntegers from = new UniqueIntegers(1);
        assertThat(from.remove(2).getValues()).containsExactly(1);
        assertThat(from.getValues()).containsExactly(1);

        UniqueIntegers from2 = new UniqueIntegers(1, 2);
        assertThat(from2.remove(2).getValues()).containsExactly(1);
        assertThat(from2.getValues()).containsExactly(1, 2);

        UniqueIntegers from3 = new UniqueIntegers();
        assertThat(from3.remove().getValues()).containsExactly();
        assertThat(from3.getValues()).containsExactly();

    }

    @Test
    void shouldCheckForEquality() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5);

        assertThat(uniqueIntegers.isEqualTo(uniqueIntegers)).isTrue();

        assertThat(uniqueIntegers.isEqualTo(new UniqueIntegers(1, 2, 3, 4, 5))).isTrue();
        assertThat(uniqueIntegers.isEqualTo(new UniqueIntegers(1, 3, 2, 4, 5))).isTrue();
        assertThat(uniqueIntegers.isEqualTo(new UniqueIntegers(1, 2, 3, 4))).isFalse();
        assertThat(uniqueIntegers.isEqualTo(new UniqueIntegers(2, 3, 4, 5))).isFalse();
        assertThat(uniqueIntegers.isEqualTo(new UniqueIntegers(2, 3, 4, 5, 6, 7))).isFalse();

        assertThat(new UniqueIntegers(1, 2, 3, 4, 5).isEqualTo(new UniqueIntegers(5, 4, 3, 2, 1))).isTrue();
        assertThat(new UniqueIntegers(1, 2, 3, 4, 5).isEqualTo(new UniqueIntegers(1, 2, 3, 4, 3, 2, 1))).isFalse();
        assertThat(new UniqueIntegers(1, 2, 3, 4, 5).isEqualTo(new UniqueIntegers(1))).isFalse();

        assertThat(new UniqueIntegers().isEqualTo(new UniqueIntegers())).isTrue();
    }

    @Test
    void shouldContainThisValue() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5, 6, 7);

        assertThat(uniqueIntegers.contains(1)).isTrue();
        assertThat(uniqueIntegers.contains(3)).isTrue();
        assertThat(uniqueIntegers.contains(5)).isTrue();
        assertThat(uniqueIntegers.contains(9)).isFalse();
        assertThat(uniqueIntegers.contains(0)).isFalse();

        assertThat(new UniqueIntegers(1, 2).contains(1)).isTrue();
        assertThat(new UniqueIntegers(1).contains(1)).isTrue();
        assertThat(new UniqueIntegers().contains(1)).isFalse();

    }

    @Test
    void shouldContainUniqueIntegers() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5, 6, 7);

        assertThat(uniqueIntegers.contains(new UniqueIntegers())).isTrue();

        assertThat(uniqueIntegers.contains(new UniqueIntegers(1))).isTrue();
        assertThat(uniqueIntegers.contains(new UniqueIntegers(1, 2))).isTrue();
        assertThat(uniqueIntegers.contains(new UniqueIntegers(1, 5))).isTrue();
        assertThat(uniqueIntegers.contains(new UniqueIntegers(1, 2, 3, 4, 5, 6, 7))).isTrue();

        assertThat(uniqueIntegers.contains(new UniqueIntegers(1, 10))).isFalse();
        assertThat(uniqueIntegers.contains(new UniqueIntegers(0))).isFalse();
        assertThat(uniqueIntegers.contains(new UniqueIntegers(9))).isFalse();

        assertThat(new UniqueIntegers().contains(new UniqueIntegers())).isTrue();
        assertThat(new UniqueIntegers(1).contains(new UniqueIntegers())).isTrue();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(1))).isTrue();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 2))).isTrue();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 2, 3))).isFalse();
        assertThat(new UniqueIntegers().contains(new UniqueIntegers(1))).isFalse();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(3))).isFalse();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 3))).isFalse();
        assertThat(new UniqueIntegers(1, 2).contains(new UniqueIntegers(1, 3))).isFalse();
    }

    @Test
    void shouldIntersectIntegers() {
        UniqueIntegers uniqueIntegers = new UniqueIntegers(1, 2, 3, 4, 5);

        assertThat(uniqueIntegers.intersects(new UniqueIntegers(1))).isTrue();
        assertThat(uniqueIntegers.intersects(new UniqueIntegers(1, 2))).isTrue();
        assertThat(uniqueIntegers.intersects(new UniqueIntegers(1, 5))).isTrue();

        assertThat(uniqueIntegers.intersects(new UniqueIntegers(1, 6))).isTrue();
        assertThat(uniqueIntegers.intersects(new UniqueIntegers(0, 5))).isTrue();

        assertThat(uniqueIntegers.intersects(new UniqueIntegers(6))).isFalse();
        assertThat(uniqueIntegers.intersects(new UniqueIntegers(0))).isFalse();
        assertThat(new UniqueIntegers().intersects(new UniqueIntegers(0))).isFalse();
        assertThat(new UniqueIntegers(0).intersects(new UniqueIntegers())).isFalse();

    }

    private int[] removeValue(final int... values) {
        return new UniqueIntegers(values).remove().getValues();
    }

}

package edu.hm.hafner.assignment;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the class {@link Integers}.
 *
 * @author Ullrich Hafner
 */
class IntegersTest {
    /** Verifies that the array is empty in the beginning. */
    @Test
    void shouldCreateEmptyIntegersLong() {
        // Given
        Integers integers = new Integers();

        // When
        boolean actual = integers.isEmpty();

        // Then
        assertThat(actual).as("Zu Beginn ist Array nicht leer!").isTrue();
    }

    @Test
    void shouldCreateEmptyIntegers() {
        Integers integers = new Integers();

        assertThat(integers.isEmpty()).isTrue();
        assertThat(integers.size()).isEqualTo(0);
        assertThat(integers.getValues()).isEmpty();
    }

    @Test
    void shouldCreateIntegersWithPredefinedValues() {
        Integers integers = new Integers(1);

        assertThat(integers.isEmpty()).isFalse();
        assertThat(integers.size()).isEqualTo(1);
        assertThat(integers.getValues()).containsExactly(1);
    }

    @Test
    void shouldCreateIntegersWithMorePredefinedValues() {
        Integers integers = new Integers(1, 2);

        assertThat(integers.isEmpty()).isFalse();
        assertThat(integers.size()).isEqualTo(2);
        assertThat(integers.getValues()).containsExactly(1, 2);
        assertThat(new Integers(2, 1).getValues()).containsExactly(2, 1);
    }

    @Test
    void shouldCreateIntegersWithZeroes() {
        Integers integers = new Integers(0, 0, 0);

        assertThat(integers.isEmpty()).isFalse();
        assertThat(integers.size()).isEqualTo(3);
        assertThat(integers.getValues()).containsExactly(0, 0, 0);
    }

    @Test
    void shouldCopyValues() {
        int[] values = {1, 2, 3};
        Integers integers = new Integers(values);
        Arrays.fill(values, -1);
        assertThat(integers.getValues()).containsExactly(1, 2, 3);
    }

    @Test
    void shouldReturnCopy() {
        Integers integers = new Integers(1, 2, 3);
        int[] values = integers.getValues();
        assertThat(values).containsExactly(1, 2, 3);
        Arrays.fill(values, -1);
        assertThat(integers.getValues()).containsExactly(1, 2, 3);
    }

    @Test
    void shouldCreateLargeArray() {
        int[] values = new int[1_000_000];
        values[0] = -1;
        values[1_000_000 - 1] = -1;

        Integers integers = new Integers(values);
        assertThat(integers.getValues()[0]).isEqualTo(-1);
        assertThat(integers.getValues()[1]).isEqualTo(0);
        assertThat(integers.getValues()[1_000_000 - 2]).isEqualTo(0);
        assertThat(integers.getValues()[1_000_000 - 1]).isEqualTo(-1);
    }

    @Test
    void shouldCreateIntegersWith10PredefinedValues() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.isEmpty()).isFalse();
        assertThat(integers.size()).isEqualTo(10);
    }

    @Test
    void shouldCheckForEquality() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.isEqualTo(integers)).isTrue();

        assertThat(integers.isEqualTo(new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9))).isFalse();
        assertThat(integers.isEqualTo(new Integers(2, 3, 4, 5, 6, 7, 8, 9, 10))).isFalse();
        assertThat(integers.isEqualTo(new Integers(1, 2, 4, 3, 5, 6, 7, 8, 9, 10))).isFalse();

        assertThat(new Integers(1, 2, 3).isEqualTo(new Integers(3, 2, 1))).isFalse();
        assertThat(new Integers(1, 2, 3).isEqualTo(new Integers(1, 2, 3, 1, 2, 3))).isFalse();
        assertThat(new Integers(1, 1).isEqualTo(new Integers(1))).isFalse();
        assertThat(new Integers(1).isEqualTo(new Integers(1, 1))).isFalse();

        assertThat(new Integers().isEqualTo(new Integers())).isTrue();
    }

    @Test
    void shouldAppendAfter() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.appendAfter(new Integers(11, 12, 13)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    }

    @Test
    void shouldAppendAfterAdditional() {
        assertThat(new Integers(1).appendAfter(new Integers(10)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(1, 10));
        assertThat(new Integers().appendAfter(new Integers(10)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(10));
        assertThat(new Integers(1, 2).appendAfter(new Integers()))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(1, 2));
        assertThat(new Integers().appendAfter(new Integers()))
                .usingRecursiveComparison()
                .isEqualTo(new Integers());
    }

    @Test
    void shouldInsertBefore() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.insertBefore(new Integers(-1, 0)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    void shouldInsertBeforeAdditional() {
        assertThat(new Integers(1).insertBefore(new Integers(10)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(10, 1));
        assertThat(new Integers().insertBefore(new Integers(10)))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(10));
        assertThat(new Integers(1, 2).insertBefore(new Integers()))
                .usingRecursiveComparison()
                .isEqualTo(new Integers(1, 2));
        assertThat(new Integers().insertBefore(new Integers()))
                .usingRecursiveComparison()
                .isEqualTo(new Integers());
    }

    @Test
    void shouldContainValue() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.contains(1)).isTrue();
        assertThat(integers.contains(10)).isTrue();
        assertThat(integers.contains(0)).isFalse();
        assertThat(integers.contains(11)).isFalse();

        assertThat(new Integers(1, 1).contains(1)).isTrue();
        assertThat(new Integers(1).contains(1)).isTrue();
        assertThat(new Integers().contains(1)).isFalse();
    }

    @Test
    void shouldContainIntegers() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.contains(new Integers())).isTrue();

        assertThat(integers.contains(new Integers(1))).isTrue();
        assertThat(integers.contains(new Integers(1, 2))).isTrue();
        assertThat(integers.contains(new Integers(1, 10))).isTrue();

        assertThat(integers.contains(new Integers(1, 11))).isFalse();
        assertThat(integers.contains(new Integers(0))).isFalse();

        assertThat(new Integers().contains(new Integers())).isTrue();
        assertThat(new Integers(1).contains(new Integers())).isTrue();
        assertThat(new Integers().contains(new Integers(1))).isFalse();
    }

    @Test
    void shouldIntersectIntegers() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.intersects(new Integers(1))).isTrue();
        assertThat(integers.intersects(new Integers(1, 2))).isTrue();
        assertThat(integers.intersects(new Integers(1, 10))).isTrue();

        assertThat(integers.intersects(new Integers(1, 11))).isTrue();
        assertThat(integers.intersects(new Integers(0, 10))).isTrue();

        assertThat(integers.intersects(new Integers(11))).isFalse();
        assertThat(integers.intersects(new Integers(0))).isFalse();
        assertThat(new Integers().intersects(new Integers(0))).isFalse();
        assertThat(new Integers(0).intersects(new Integers())).isFalse();
    }

    @Test
    void shouldSplit() {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.split(0))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(new Integers[] {new Integers(),
                        new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)});
        assertThat(integers.split(1))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(new Integers[] {new Integers(1),
                        new Integers(2, 3, 4, 5, 6, 7, 8, 9, 10)});
        assertThat(integers.split(9))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(new Integers[] {new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9),
                        new Integers(10)});
        assertThat(integers.split(10))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(new Integers[] {new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                        new Integers()});
    }

    @ParameterizedTest(name = "[{index}] Split at {0} for array [0,...,10]")
    @ValueSource(ints = {-2, -1, 0})
    void shouldSplitToEmptyLeft(final int position) {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.split(position))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(
                        new Integers[] {new Integers(),
                                new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)});
    }

    @ParameterizedTest(name = "≪{index}≫ Split at {0} for array [0,...,10]")
    @ValueSource(ints = {10, 11, 12})
    void shouldSplitToEmptyRight(final int position) {
        Integers integers = new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(integers.split(position))
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(
                        new Integers[] {
                                new Integers(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                                new Integers()});
    }

    @Test
    void shouldRemoveDuplicates() {
        assertThat(removeDuplicatesFrom()).isEmpty();
        assertThat(new Integers(1).removeDuplicates().getValues()).containsExactly(1);
        assertThat(new Integers(1, 1).removeDuplicates().getValues()).containsExactly(1);
        assertThat(new Integers(1, 1, 2).removeDuplicates().getValues()).containsExactly(1, 2);
        assertThat(new Integers(1, 1, 2, 2).removeDuplicates().getValues()).containsExactly(1, 2);
        assertThat(new Integers(1, 1, 2, 2, 1).removeDuplicates().getValues()).containsExactly(1, 2);
        assertThat(new Integers(4, 5, 1, 1, 2, 2, 1, 2, 3, 2, 1, 5).removeDuplicates().getValues()).containsExactly(4, 5, 1, 2, 3);
    }

    @Test
    void shouldRemoveDuplicatesMagicNumber() {
        assertThat(removeDuplicatesFrom()).isEmpty();
        assertThat(new Integers(-1).removeDuplicates().getValues()).containsExactly(-1);
        assertThat(new Integers(0).removeDuplicates().getValues()).containsExactly(0);
        assertThat(new Integers(0, 0).removeDuplicates().getValues()).containsExactly(0);
        assertThat(new Integers(-1, -1).removeDuplicates().getValues()).containsExactly(-1);
    }

    @Test
    void shouldRemoveDuplicatesImmutable() {
        Integers start = new Integers(1, 1);
        assertThat(start.removeDuplicates().getValues()).containsExactly(1);
        assertThat(start.getValues()).containsExactly(1, 1);

        Integers start2 = new Integers(1, 1, 2, 2, 1);
        assertThat(start2.removeDuplicates().getValues()).containsExactly(1, 2);
        assertThat(start2.getValues()).containsExactly(1, 1, 2, 2, 1);
    }

    private int[] removeDuplicatesFrom(final int... values) {
        return new Integers(values).removeDuplicates().getValues();
    }
}

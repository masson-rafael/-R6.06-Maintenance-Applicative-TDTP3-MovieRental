package movierental;

import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CustomerTest {

    @Test
    void devrait_retourner_un_releve_vide_quand_aucune_location() {
        // Given
        Customer customer = new Customer("Bob");

        // When
        String statement = customer.statement();

        // Then
        String expected = String.join("\n",
                "Rental Record for Bob",
                "Amount owed is 0.0",
                "You earned 0 frequent renter points");

        assertThat(statement).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0} ({1} jour(s))")
    @MethodSource("cas_de_location_simple")
    @DisplayName("devrait_generer_un_releve_correct_pour_une_location_simple")
    void devrait_generer_un_releve_correct_pour_une_location_simple(String titre, int codePrix, int jours, double montantAttendu, int pointsAttendus) {
        // Given
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie(titre, codePrix), jours));

        // When
        String statement = customer.statement();

        // Then
        String expected = String.join("\n",
                "Rental Record for Bob",
                "\t" + titre + "\t" + montantAttendu,
                "Amount owed is " + montantAttendu,
                "You earned " + pointsAttendus + " frequent renter points");

        assertThat(statement).isEqualTo(expected);
    }

    @Test
    void devrait_generer_le_releve_complet_pour_un_panier_mixte() {
        // Given
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Golden Eye", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Short New", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Long New", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

        // When
        String statement = customer.statement();

        // Then
        String expected = String.join("\n",
                "Rental Record for Bob",
                "\tJaws\t2.0",
                "\tGolden Eye\t3.5",
                "\tShort New\t3.0",
                "\tLong New\t6.0",
                "\tBambi\t1.5",
                "\tToy Story\t3.0",
                "Amount owed is 19.0",
                "You earned 7 frequent renter points");

        assertThat(statement).isEqualTo(expected);
    }

    private static Stream<Arguments> cas_de_location_simple() {
        return Stream.of(
                Arguments.of("Jaws", Movie.REGULAR, 2, 2.0, 1),
                Arguments.of("Golden Eye", Movie.REGULAR, 3, 3.5, 1),
                Arguments.of("Short New", Movie.NEW_RELEASE, 1, 3.0, 1),
                Arguments.of("Long New", Movie.NEW_RELEASE, 2, 6.0, 2),
                Arguments.of("Bambi", Movie.CHILDRENS, 3, 1.5, 1),
                Arguments.of("Toy Story", Movie.CHILDRENS, 4, 3.0, 1)
        );
    }
}
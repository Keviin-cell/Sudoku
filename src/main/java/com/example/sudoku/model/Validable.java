package com.example.sudoku.model;

/**
 * Represents an entity that supports validation logic.
 * <p>
 * This interface defines a contract for checking the validity of an object
 * according to custom business rules or constraints.
 * It is typically implemented by domain objects that require rule-based validation,
 * such as Sudoku cells or board components.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     if (cell.isValid()) {
 *         // proceed with the value
 *     }
 * </pre>
 *
 * @author León
 * @version 1.0
 */
public interface Validable {

    /**
     * Checks whether the current object state is valid.
     *
     * @return {@code true} if the object meets all validation rules; {@code false} otherwise.
     */
    boolean isValid();
}
package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * This class represents the endpoint for managing user-related operations.
 * It provides methods for creating, updating, retrieving, and listing users.
 * @since 11Sep2023
 */
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Constructs a new UserEndpoint instance with the given specification.
     *
     * @param specification The RequestSpecification used for making HTTP requests.
     * @author Ihor Nahirnyi
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new user with the provided UserDto.
     *
     * @param userDto The UserDto representing the user to create.
     * @return The created UserDto.
     * @author Ihor Nahirnyi
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
            .extract().as(UserDto.class);
    }

    /**
     * Creates a new user with the provided UserDto and validates the HTTP status code.
     *
     * @param userDto The UserDto representing the user to create.
     * @param status  The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation.
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
            this.specification,
            USERS_END,
            userDto)
            .statusCode(status.getCode());
    }

    /**
     * Updates an existing user with the provided UserDto.
     *
     * @param id      The ID of the user to update.
     * @param userDto The UserDto representing the updated user data.
     * @return The updated UserDto.
     * @author Ihor Nahirnyi
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Updates an existing user with the provided UserDto and validates the HTTP status code.
     *
     * @param userDto The UserDto representing the updated user data.
     * @param id      The ID of the user to update.
     * @param status  The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation.
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
            this.specification,
            USERS_RESOURCE_END,
            userDto,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The UserDto representing the retrieved user.
     * @author Ihor Nahirnyi
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Retrieves a user by ID and validates the HTTP status code.
     *
     * @param id     The ID of the user to retrieve.
     * @param status The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation.
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
            this.specification,
            USERS_RESOURCE_END,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of UserDto objects representing all users.
     * @author Ihor Nahirnyi
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Retrieves a list of all users and validates the HTTP status code.
     *
     * @param status The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation.
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }
}

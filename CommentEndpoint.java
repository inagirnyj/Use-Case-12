package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * This class represents the endpoint for managing comment-related operations.
 * It provides methods for creating, updating, retrieving, and listing comments.
 * @since 11Sep2023
 */
public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
     * Constructs a new CommentEndpoint instance with the given specification.
     *
     * @param specification The RequestSpecification used for making HTTP requests
     * @author Ihor Nahirnyi
     */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new comment with the provided CommentDto.
     *
     * @param commentDto The CommentDto representing the comment to create.
     * @return The created CommentDto
     * @author Ihor Nahirnyi
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
            .extract().as(CommentDto.class);
    }

    /**
     * Creates a new comment with the provided CommentDto and validates the HTTP status code.
     *
     * @param commentDto The CommentDto representing the comment to create.
     * @param status     The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
            this.specification,
            COMMENTS_END,
            commentDto)
            .statusCode(status.getCode());
    }

    /**
     * Updates an existing comment with the provided CommentDto.
     *
     * @param id         The ID of the comment to update.
     * @param commentDto The CommentDto representing the updated comment data.
     * @return The updated CommentDto
     * @author Ihor Nahirnyi
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }

    /**
     * Updates an existing comment with the provided CommentDto and validates the HTTP status code.
     *
     * @param commentDto The CommentDto representing the updated comment data.
     * @param id         The ID of the comment to update.
     * @param status     The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
            this.specification,
            COMMENTS_RESOURCE_END,
            commentDto,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The CommentDto representing the retrieved comment
     * @author Ihor Nahirnyi
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }

    /**
     * Retrieves a comment by its ID and validates the HTTP status code.
     *
     * @param id     The ID of the comment to retrieve.
     * @param status The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
            this.specification,
            COMMENTS_RESOURCE_END,
            String.valueOf(id))
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a list of all comments.
     *
     * @return A list of CommentDto objects representing all comments
     * @author Ihor Nahirnyi
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * Retrieves a list of all comments and validates the HTTP status code.
     *
     * @param status The expected HTTP status code.
     * @return A ValidatableResponse containing the HTTP response for validation
     * @author Ihor Nahirnyi
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
